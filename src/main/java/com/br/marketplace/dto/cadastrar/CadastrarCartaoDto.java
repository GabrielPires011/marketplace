package com.br.marketplace.dto.cadastrar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarCartaoDto(@NotBlank @Size(max = 100) String nome,
                                 @NotBlank @Size(min = 19,max = 19) String numero,
                                 @NotBlank @Size(min = 7,max = 7) String expiracao,
                                 @NotBlank @Size(min = 3, max = 3) String codigo) {
}
