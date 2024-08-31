package com.br.marketplace.model;

import com.br.marketplace.dto.salvar.SalvarUsuarioDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Usuario {

    @Id
    private UUID id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(SalvarUsuarioDto dto) {
        this.id = UUID.randomUUID();
        this.nome = dto.nome();
        this.email = dto.email();
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
