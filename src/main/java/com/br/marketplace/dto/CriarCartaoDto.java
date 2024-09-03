package com.br.marketplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarCartaoDto(
        @NotBlank(message = "O nome não pode estar em branco.")
        @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
        String nome,

        @NotBlank(message = "O número não pode estar em branco.")
        @Size(min = 19, max = 19, message = "O número deve ter 19 dígitos.")
        Long numero,

        @NotBlank(message = "A data de expiração não pode estar em branco.")
        @Size(min = 7, max = 7, message = "A data de expiração deve ter 7 caracteres.")
        String expiracao,

        @NotBlank(message = "O código não pode estar em branco.")
        @Size(min = 3, max = 3, message = "O código deve ter 3 dígitos.")
        Integer codigo) {
}

