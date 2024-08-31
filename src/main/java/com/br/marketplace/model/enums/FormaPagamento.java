package com.br.marketplace.model.enums;

public enum FormaPagamento {
    DEBITO("DebitCard"),
    CREDITO("CreditCard");

    private final String tipo;

    FormaPagamento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}