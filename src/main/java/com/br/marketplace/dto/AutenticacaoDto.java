package com.br.marketplace.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDto(@Email String email,
                              @NotBlank String senha) {
}