package com.br.marketplace.dto;

import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record ListarPagamentoDto(UUID id, BigDecimal valor, String descricao, FormaPagamento formaDePagamento, Status status) { }