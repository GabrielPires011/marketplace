package com.br.marketplace.service;

import com.br.marketplace.exception.ValidacaoException;
import com.br.marketplace.model.Pagamento;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CieloPagamentoServico {

    @Value("${cielo.merchant.key}")
    private String merchantKey;

    @Value("${cielo.merchant.id}")
    private String merchantId;

    public String processarPagamento(Pagamento pagamento) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> paymentRequest = criarPaymentRequest(pagamento);
        String cieloApiUrl = "https://apisandbox.cieloecommerce.cielo.com.br/1/sales/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("MerchantKey", merchantKey);
        headers.set("MerchantId", merchantId);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(paymentRequest, headers);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(cieloApiUrl, HttpMethod.POST, requestEntity, Map.class);
            Map<String, Object> response = responseEntity.getBody();

            if (response == null || response.get("Payment") == null) {
                throw new ValidacaoException("Erro ao processar pagamento com a Cielo: resposta inválida.");
            }

            Map<String, Object> paymentResponse = (Map<String, Object>) response.get("Payment");

            if (paymentResponse == null || paymentResponse.get("ReturnCode") == null) {
                throw new ValidacaoException("Código de retorno não encontrado na resposta da Cielo.");
            }

            return String.valueOf(paymentResponse.get("ReturnCode"));
        } catch (Exception e) {
            throw new ValidacaoException("Erro ao processar pagamento com a Cielo.");
        }
    }

    private Map<String, Object> criarPaymentRequest(Pagamento pagamento) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("MerchantOrderId", pagamento.getId());

        Map<String, Object> payment = new HashMap<>();
        payment.put("Type", pagamento.getFormaPagamento().getTipo());
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
