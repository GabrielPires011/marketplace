package com.br.marketplace.dto;

import com.br.marketplace.model.enums.Bandeira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CriarCartaoDto(
        @NotBlank(message = "O nome não pode estar em branco.")
        @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres.")
        String nome,

        @NotBlank(message = "O número não pode estar em branco.")
        @Size(min = 19, max = 19, message = "O número deve ter 19 dígitos.")
        Integer numero,

        @NotBlank(message = "A data de expiração não pode estar em branco.")
        @Size(min = 7, max = 7, message = "A data de expiração deve ter 7 caracteres.")
        Date expiracao,

        @NotBlank(message = "O código não pode estar em branco.")
        @Size(min = 3, max = 3, message = "O código deve ter 3 dígitos.")
        Integer codigo,

        @NotNull(message = "A bandeira não pode ser nula.")
        Bandeira bandeira) {
}

