package com.br.marketplace.controller;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.dto.DadosDetalhadosPagamentoDto;
import com.br.marketplace.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<Void> criar(@Valid @RequestBody CriarPagamentoDto dto) {
        service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("lista")
    public ResponseEntity<List<DadosDetalhadosPagamentoDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarDadosDetalhadosPagamentoDto());
    }

    @PutMapping("cancela/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable UUID id) {
        service.cancelar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
