package com.br.marketplace.service;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.dto.DadosDetalhadosPagamentoDto;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private CieloPagamentoServico cieloPagamentoServico;

    public void criar(CriarPagamentoDto dto) {
        repository.save(new Pagamento(dto));
    }

    public List<DadosDetalhadosPagamentoDto> listarDadosDetalhadosPagamentoDto() {
        return repository.listarDadosDetalhados();
    }

    @Async
    public void processarPagamentosPendentes() {
        List<Pagamento> vendasPendentes = repository.buscarPagamentosPendentes();
        for (Pagamento pagamento : vendasPendentes) {

            var processarPagamento = cieloPagamentoServico.processarPagamento(pagamento);

            if (processarPagamento.equals("4") || processarPagamento.equals("6")) {
                pagamento.concluido();
            } else {
                pagamento.recusado();
            }

            repository.save(pagamento);
        }
    }
}
