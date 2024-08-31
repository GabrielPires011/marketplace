package com.br.marketplace.service;

import com.br.marketplace.model.Pagamento;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CieloPagamentoServico {
    private final RestTemplate restTemplate = new RestTemplate();

    public String processarPagamento(Pagamento pagamento) {
        Map<String, Object> paymentRequest = criarPaymentRequest(pagamento);

        String cieloApiUrl = "https://apisandbox.cieloecommerce.cielo.com.br/1/sales/";
        @SuppressWarnings("unchecked")
        Map<String, String> response = restTemplate.postForObject(cieloApiUrl, paymentRequest, Map.class);
        if (response == null || response.get("Status") == null) {
            throw new RuntimeException("Erro ao processar pagamento com a Cielo.");
        }

        return String.valueOf(response.get("Status"));
    }

    private Map<String, Object> criarPaymentRequest(Pagamento pagamento) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("MerchantOrderId", pagamento.getId());

        Map<String, Object> payment = new HashMap<>();
        payment.put("Type", pagamento.getFormaPagamento());
        payment.put("Amount", pagamento.getValor().multiply(BigDecimal.valueOf(100)).intValue());
        payment.put("Installments", 1);
        payment.put("SoftDescriptor", "Teste");

        Map<String, String> creditCard = new HashMap<>();

        pagamento.getCartao().decryptData(creditCard);

        payment.put(pagamento.getFormaPagamento().getTipo(), creditCard);
        requestBody.put("Payment", payment);

        return requestBody;
    }
}