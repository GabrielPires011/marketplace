package com.br.marketplace.service;

import com.br.marketplace.dto.DadosDetalhadosCieloLogDto;
import com.br.marketplace.dto.ListarCieloLogDto;
import com.br.marketplace.model.CieloLog;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.repository.CieloLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CieloLogService {

    @Autowired
    private CieloLogRepository repository;

    @Transactional
    public void salvar(Map<String, Object> pagamentoResposta, ResponseEntity<Map> corpoResposta, Pagamento pagamento) {
        var cieloLog = new CieloLog(pagamentoResposta, corpoResposta, pagamento);
        repository.save(cieloLog);
    }

    @Transactional
    public void salvarErro(String mensagem, Pagamento pagamento, ResponseEntity<Map> corpoResposta) {
        var cieloLog = new CieloLog(mensagem, pagamento, corpoResposta);
        repository.save(cieloLog);
    }

    public List<ListarCieloLogDto> listarCieloLogDto() {
        return repository.listarCieloLogDto();
    }

    public DadosDetalhadosCieloLogDto buscarDadosDetalhadosCieloLogDto(UUID id) {
        return repository.buscarDadosDetalhadosCieloLogDto(id);
    }
}
