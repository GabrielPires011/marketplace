package com.br.marketplace.service;

import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.dto.DadosDetalhadosPagamentoDto;
import com.br.marketplace.exception.ValidacaoException;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.UUID;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private CieloPagamentoServico cieloPagamentoServico;

    @Transient
    public void criar(CriarPagamentoDto dto) {
        repository.save(new Pagamento(dto));
    }

    public List<DadosDetalhadosPagamentoDto> listarDadosDetalhadosPagamentoDto() {
        return repository.listarDadosDetalhados();
    }

    @Async
    @Transient
    public void processarPagamentosPendentes() {
        List<Pagamento> vendasPendentes = repository.buscarPagamentosPendentes();
        for (Pagamento pagamento : vendasPendentes) {

            var processarPagamento = cieloPagamentoServico.processarPagamento(pagamento);

            if (processarPagamento.equals("4") || processarPagamento.equals("6")) {
                pagamento.concluir();
            } else {
                pagamento.recusar();
            }

            repository.save(pagamento);
        }
    }

    @Transient
    public void cancelar(UUID id) {
        var pagamento = repository.findById(id)
                .orElseThrow(() -> new ValidacaoException(
                        "Pagamento não encontrada."));

        pagamento.cancelar();
        repository.save(pagamento);
    }
}
