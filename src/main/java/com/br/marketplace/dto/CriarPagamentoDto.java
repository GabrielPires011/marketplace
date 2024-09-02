package com.br.marketplace.dto;

import com.br.marketplace.model.enums.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarPagamentoDto(
        @NotNull(message = "O valor não pode ser nulo.")
        @Positive(message = "O valor deve ser um número positivo.")
        BigDecimal valor,

        @NotNull(message = "A descrição não pode ser nula.")
        String descricao,

        @NotNull(message = "A forma de pagamento não pode ser nula.")
        FormaPagamento formaDePagamento,

        @Valid
        CriarCartaoDto criarCartaoDto,

        @NotNull(message = "O ID do usuário não pode ser nulo.")
        UUID idUsuario) {
}