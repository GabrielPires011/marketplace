package com.br.marketplace.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    private UUID id;

    private String encryptedNome;
    private String encryptedNumero;
    private String encryptedExpiracao;
    private String encryptedCodigo;

    @Transient
    private String decryptedNome;
    @Transient
    private String decryptedNumero;
    @Transient
    private String decryptedExpiracao;
    @Transient
    private String decryptedCodigo;

    @PrePersist
    public void encryptData() throws Exception {
        this.encryptedNome = encrypt(this.decryptedNome);
        this.encryptedNumero = encrypt(this.decryptedNumero);
        this.encryptedExpiracao = encrypt(this.decryptedExpiracao);
        this.encryptedCodigo = encrypt(this.decryptedCodigo);
    }

    @PostLoad
    public void decryptData() throws Exception {
        this.decryptedNome = decrypt(this.encryptedNome);
        this.decryptedNumero = decrypt(this.encryptedNumero);
        this.decryptedExpiracao = decrypt(this.encryptedExpiracao);
        this.decryptedCodigo = decrypt(this.encryptedCodigo);
    }
}
