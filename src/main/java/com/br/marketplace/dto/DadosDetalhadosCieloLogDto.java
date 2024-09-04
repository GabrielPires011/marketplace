package com.br.marketplace.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosDetalhadosCieloLogDto(UUID id,
                                         LocalDateTime dataOperacao,
                                         String mensagemResposta,
                                         String statusResposta,
                                         String codigoResposta,
                                         String mensagemErro,
                                         String corpoResposta,
                                         UUID idPagamento) {}