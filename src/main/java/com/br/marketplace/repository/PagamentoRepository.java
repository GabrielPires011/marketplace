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
}