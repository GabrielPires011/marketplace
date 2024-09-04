package com.br.marketplace.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ListarCieloLogDto(UUID id,
                                LocalDateTime dataOperacao,
                                String mensagemResposta,
                                String statusResposta,
                                String codigoResposta,
                                UUID idPagamento) {}