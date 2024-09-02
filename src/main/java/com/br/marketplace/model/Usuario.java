package com.br.marketplace.model;

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

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
