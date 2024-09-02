package com.br.marketplace.service;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public void criar(CriarPagamentoDto dto) {
        repository.save(new Pagamento(dto));
    }
}
