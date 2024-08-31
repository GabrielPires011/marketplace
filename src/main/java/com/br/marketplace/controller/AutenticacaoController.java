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

    @PostMapping("/autenticacao")
    public ResponseEntity<String> autenticacao(@Valid  AutenticacaoDto autenticacaoDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.autenticacao(autenticacaoDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
