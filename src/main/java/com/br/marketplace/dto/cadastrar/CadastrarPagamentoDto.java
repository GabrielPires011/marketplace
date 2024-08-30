package com.br.marketplace.dto.cadastrar;

import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CadastrarPagamentoDto(@NotNull @Positive BigDecimal valor,
                                    @NotNull Status status,
                                    @NotNull String descricao,
                                    @NotNull FormaPagamento formaDePagamento,
                                    @NotNull UUID cartaoId) {
}