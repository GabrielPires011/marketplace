package com.br.marketplace.model;

import com.br.marketplace.dto.cadastrar.CadastrarCartaoDto;
import com.br.marketplace.model.enums.Bandeira;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.UUID;

import static com.br.marketplace.util.EncryptionUtil.decrypt;
import static com.br.marketplace.util.EncryptionUtil.encrypt;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    private UUID id;

    private String encryptedNome;
    private String encryptedNumero;
    private String encryptedExpiracao;
    private String encryptedCodigo;
    private String encryptedBandeira;

    @Transient
    private String decryptedNome;
    @Transient
    private Integer decryptedNumero;
    @Transient
    private String decryptedExpiracao;
    @Transient
    private Integer decryptedCodigo;
    @Transient
    private Bandeira decryptedBandeira;

    public void decryptData() {
        this.decryptedNome = decrypt(this.encryptedNome);
        this.decryptedNumero = Integer.valueOf(decrypt(this.encryptedNumero));
        this.decryptedExpiracao = decrypt(this.encryptedExpiracao);
        this.decryptedCodigo = Integer.valueOf(decrypt(this.encryptedCodigo));
        this.decryptedBandeira = Bandeira.valueOf(decrypt(this.encryptedBandeira));
    }

    public Cartao (){}

    public Cartao(CadastrarCartaoDto dto) {
        this.id = UUID.randomUUID();
        this.encryptedNome = encrypt(dto.nome());
        this.encryptedNumero = encrypt(dto.numero());
        this.encryptedExpiracao = encrypt(dto.expiracao());
        this.encryptedCodigo = encrypt(dto.codigo());
        this.encryptedBandeira = encrypt(dto.bandeira());
    }


}
