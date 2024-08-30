package com.br.marketplace.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    private UUID id;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String Descricao;
    @ManyToOne
    private Cartao cartao;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
}
