package com.br.marketplace.dto.cadastrar;

import com.br.marketplace.model.enums.Bandeira;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record CadastrarCartaoDto(@NotBlank @Size(max = 100) String nome,
                                 @NotBlank @Size(min = 19,max = 19) Integer numero,
                                 @NotBlank @Size(min = 7,max = 7) Date expiracao,
                                 @NotBlank @Size(min = 3,max = 3) Integer codigo,
                                 @NotNull Bandeira bandeira) {
}
