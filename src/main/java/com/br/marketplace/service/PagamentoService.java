package com.br.marketplace.service;

import com.br.marketplace.dto.AlterarAprovarPagamentoDto;
import com.br.marketplace.dto.CancelarPagamentoDto;
import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.dto.ListarPagamentoDto;
import com.br.marketplace.exception.ValidacaoException;
import com.br.marketplace.http.PedidoClient;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private CieloPagamentoService cieloPagamentoService;

    @Autowired
    private PedidoClient pedidoClient;

    @Transactional
    public void criar(CriarPagamentoDto dto) {
        repository.save(new Pagamento(dto));
    }

    public List<ListarPagamentoDto> listarPagamentoDto() {
        return repository.listarDadosDetalhados();
    }

    @Async
    @Transactional
    public void processarPagamentosPendentes() {
        List<Pagamento> vendasPendentes = repository.buscarPagamentosPendentes();
        for (Pagamento pagamento : vendasPendentes) {

            var processarPagamento = cieloPagamentoService.processarPagamento(pagamento);

            if (processarPagamento.equals("4") || processarPagamento.equals("6")) {
                pagamento.concluir();
                if (pagamento.getPedidoId() != null) {
                    pedidoClient.atualizaPagamento(new AlterarAprovarPagamentoDto(pagamento.getPedidoId()));
                }
            } else {
                pagamento.recusar();
            }

            repository.save(pagamento);
        }
    }

    @Transactional
    public void cancelar(CancelarPagamentoDto dto) {
        var pagamento = repository.findById(dto.id())
                .orElseThrow(() -> new ValidacaoException(
                        "Pagamento não encontrado."));

        pagamento.cancelar();
        repository.save(pagamento);
    }
}
