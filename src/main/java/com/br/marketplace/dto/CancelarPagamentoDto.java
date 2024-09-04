package com.br.marketplace.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CancelarPagamentoDto(@NotNull(message = "O id não pode ser nulo.") UUID id) {
}
