package com.br.marketplace.model;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    public Pagamento() {}

    public Pagamento(CriarPagamentoDto dto) {
        this.valor = dto.valor();
        this.status = Status.PENDENTE;
        this.descricao = dto.descricao();
        this.cartao = new Cartao(dto.criarCartaoDto());
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

    public void concluir() {
        this.status = Status.CONCLUIDO;
    }

    public void recusar() {
        this.status = Status.RECUSADO;
    }

    public void cancelar() {
        this.status = Status.CANCELADO;
    }

    public Pagamento(UUID id, BigDecimal valor, Status status, String descricao, Cartao cartao, FormaPagamento formaPagamento) {
        this.id = id;
        this.valor = valor;
        this.status = status;
        this.descricao = descricao;
        this.cartao = cartao;
        this.formaPagamento = formaPagamento;
    }
}
