package com.br.marketplace.controller;

import com.br.marketplace.dto.DadosDetalhadosCieloLogDto;
import com.br.marketplace.dto.ListarCieloLogDto;
import com.br.marketplace.service.CieloLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cielo-log")
public class CieloLogController {

    @Autowired
    private CieloLogService service;

    @Operation(summary = "Lista todos os logs da Cielo",
            description = "Retorna uma lista de logs da Cielo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Logs retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListarCieloLogDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<ListarCieloLogDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarCieloLogDto());
    }

    @Operation(summary = "Busca um log detalhado da Cielo por ID",
            description = "Retorna um log detalhado da Cielo com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Log detalhado encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosDetalhadosCieloLogDto.class))),
            @ApiResponse(responseCode = "404", description = "Log detalhado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhadosCieloLogDto> buscar(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarDadosDetalhadosCieloLogDto(id));
    }
}
