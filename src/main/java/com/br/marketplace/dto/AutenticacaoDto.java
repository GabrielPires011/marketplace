package com.br.marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDto(@NotBlank(message = "O e-mail não deve estar em branco.") @Email(message = "Email inválido!") String email,
                              @NotBlank(message = "A senha não deve estar em branco.") String senha) {
}