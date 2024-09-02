package com.br.marketplace.controller;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;

    @PostMapping
    public ResponseEntity<Void> criar(@Valid CriarPagamentoDto dto) {
        service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
