package com.br.marketplace.model;

import com.br.marketplace.dto.CriarCartaoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.UUID;

import static com.br.marketplace.util.CapturaBandeiraCartaoUtil.capturarBandeira;
import static com.br.marketplace.util.EncryptionUtil.decrypt;
import static com.br.marketplace.util.EncryptionUtil.encrypt;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    }

    public Cartao (){}

    public Cartao(CriarCartaoDto dto) {
        this.encryptedNome = encrypt(dto.nome());
        this.encryptedNumero = encrypt(dto.numero());
        this.encryptedExpiracao = encrypt(dto.expiracao());
        this.encryptedCodigo = encrypt(dto.codigo());
        this.encryptedBandeira = encrypt(capturarBandeira(String.valueOf(dto.numero())));
    }
}