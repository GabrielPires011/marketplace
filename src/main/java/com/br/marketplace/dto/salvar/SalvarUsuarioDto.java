package com.br.marketplace.dto.salvar;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SalvarUsuarioDto(@NotBlank String nome,
                               @Email String email) {
}
