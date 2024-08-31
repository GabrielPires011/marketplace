package com.br.marketplace.dto.salvar;

import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record SalvarPagamentoDto(@NotNull @Positive BigDecimal valor,
                                 @NotNull Status status,
                                 @NotNull String descricao,
                                 @NotNull FormaPagamento formaDePagamento,
                                 @Valid SalvarCartaoDto salvarCartaoDto,
                                 @NotNull UUID idUsuario) {
}