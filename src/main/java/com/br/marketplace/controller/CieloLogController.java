package com.br.marketplace.controller;

import com.br.marketplace.dto.DadosDetalhadosCieloLogDto;
import com.br.marketplace.dto.ListarCieloLogDto;
import com.br.marketplace.service.CieloLogService;
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

    @GetMapping("lista")
    public ResponseEntity<List<ListarCieloLogDto>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarDadosDetalhadosCieloLogDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhadosCieloLogDto> buscar(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarDadosDetalhadosCieloLogDto(id));
    }
}
