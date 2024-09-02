package com.br.marketplace.model;

import com.br.marketplace.dto.CriarCartaoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.UUID;

import static com.br.marketplace.util.EncryptionUtil.decrypt;
import static com.br.marketplace.util.EncryptionUtil.encrypt;

@Entity
public class Cartao {

    @Id
    private UUID id;
    private String encryptedNome;
    private String encryptedNumero;
    private String encryptedExpiracao;
    private String encryptedCodigo;
    private String encryptedBandeira;

    public void decryptData(Map<String, String> creditCard) {
        creditCard.put("CardNumber", decrypt(this.encryptedNumero));
        creditCard.put("Holder", decrypt(this.encryptedNome));
        creditCard.put("ExpirationDate", decrypt(this.encryptedExpiracao));
        creditCard.put("SecurityCode", decrypt(this.encryptedCodigo));
        creditCard.put("Brand", decrypt(this.encryptedBandeira));
    }

    public Cartao (){}

    public Cartao(CriarCartaoDto dto) {
        this.id = UUID.randomUUID();
        this.encryptedNome = encrypt(dto.nome());
        this.encryptedNumero = encrypt(dto.numero());
        this.encryptedExpiracao = encrypt(dto.expiracao());
        this.encryptedCodigo = encrypt(dto.codigo());
        this.encryptedBandeira = encrypt(dto.bandeira());
    }
}