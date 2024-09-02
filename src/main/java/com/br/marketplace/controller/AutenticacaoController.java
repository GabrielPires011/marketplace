package com.br.marketplace.controller;

import com.br.marketplace.dto.AutenticacaoDto;
import com.br.marketplace.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService service;

    @PostMapping
    public ResponseEntity<String> autenticacao(@Valid @RequestBody AutenticacaoDto autenticacaoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.autenticacao(autenticacaoDto));
    }
}
