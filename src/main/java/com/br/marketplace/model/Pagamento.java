package com.br.marketplace.model;

import com.br.marketplace.dto.salvar.SalvarPagamentoDto;
import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;
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
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Cartao cartao;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public Pagamento() {}

    public Pagamento(SalvarPagamentoDto dto) {
        this.id = UUID.randomUUID();
        this.valor = dto.valor();
        this.status = dto.status();
        this.descricao = dto.descricao();
        this.cartao = new Cartao(dto.salvarCartaoDto());
        this.formaPagamento = dto.formaDePagamento();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public UUID getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
