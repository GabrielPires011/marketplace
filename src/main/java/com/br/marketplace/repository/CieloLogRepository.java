package com.br.marketplace.repository;

import com.br.marketplace.dto.DadosDetalhadosCieloLogDto;
import com.br.marketplace.dto.ListarCieloLogDto;
import com.br.marketplace.model.CieloLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CieloLogRepository extends JpaRepository<CieloLog, UUID> {

    @Query("select new com.br.marketplace.dto.ListarCieloLogDto(l.id, l.dataOperacao, l.mensagemResposta," +
            " l.statusResposta, l.codigoResposta, p.id)" +
            " from CieloLog l " +
            " inner join Pagamento p on p.id = l.pagamento.id")
    List<ListarCieloLogDto> listarDadosDetalhadosCieloLogDto();

    @Query("select new com.br.marketplace.dto.DadosDetalhadosCieloLogDto(l.id, l.dataOperacao, l.mensagemResposta," +
            " l.statusResposta, l.codigoResposta, l.mensagemErro, l.corpoResposta, p.id)" +
            " from CieloLog l " +
            " inner join Pagamento p on p.id = l.pagamento.id where l.id = :id")
    DadosDetalhadosCieloLogDto buscarDadosDetalhadosCieloLogDto(@Param("id") UUID id);
}