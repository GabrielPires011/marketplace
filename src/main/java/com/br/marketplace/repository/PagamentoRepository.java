package com.br.marketplace.repository;

import com.br.marketplace.dto.DadosDetalhadosPagamentoDto;
import com.br.marketplace.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {

    @Query("select new com.br.marketplace.dto.DadosDetalhadosPagamentoDto(p.id, p.valor, p.descricao, p.formaPagamento, p.status) from Pagamento p ")
    List<DadosDetalhadosPagamentoDto> listarDadosDetalhados();

    @Query("select new com.br.marketplace.model.Pagamento(p.id, p.valor, p.status, p.descricao, c, p.formaPagamento) from Pagamento p inner join Cartao c on c.id = p.cartao.id where p.status = 'PENDENTE'")
    List<Pagamento> buscarPagamentosPendentes();
}