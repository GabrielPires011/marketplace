package com.br.marketplace.controller;

import com.br.marketplace.dto.CancelarPagamentoDto;
import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.dto.ListarPagamentoDto;
import com.br.marketplace.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @Operation(summary = "Cria um novo pagamento",
            description = "Cria um novo pagamento com base no DTO fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody CriarPagamentoDto dto) {
        service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Lista todos os pagamentos",
            description = "Retorna uma lista de todos os pagamentos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListarPagamentoDto.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<ListarPagamentoDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarPagamentoDto());
    }

    @Operation(summary = "Cancela um pagamento existente",
            description = "Cancela um pagamento existente com base no DTO fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado")
    })
    @PutMapping("/cancela")
    public ResponseEntity<Void> cancelar(@Valid @RequestBody CancelarPagamentoDto dto) {
        service.cancelar(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
