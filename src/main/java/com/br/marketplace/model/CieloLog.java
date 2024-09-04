package com.br.marketplace.model;

import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
public class CieloLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime dataOperacao;
    private String mensagemResposta;
    private String statusResposta;
    private String codigoResposta;
    private String mensagemErro;
    private String corpoResposta;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pagamento pagamento;

    public CieloLog() {
    }

    public CieloLog(Map<String, Object> paymentResponse, ResponseEntity<Map> corpoResposta, Pagamento pagamento) {
        this.dataOperacao = LocalDateTime.now();
        this.mensagemResposta = paymentResponse.get("ReturnMessage").toString();
        this.statusResposta = corpoResposta.getStatusCode().toString();
        this.codigoResposta = paymentResponse.get("ReturnCode").toString();
        this.corpoResposta = corpoResposta.toString();
        this.pagamento = pagamento;
    }

    public CieloLog(String mensagemErro, Pagamento pagamento, ResponseEntity<Map> corpoResposta) {
        this.dataOperacao = LocalDateTime.now();
        this.mensagemErro = mensagemErro;
        this.statusResposta = corpoResposta.getStatusCode().toString();
        this.pagamento = pagamento;
        this.corpoResposta = corpoResposta.toString();
    }
}