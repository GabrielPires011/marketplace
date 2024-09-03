package com.br.marketplace.configuration;

import com.br.marketplace.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ConfiguracaoDeAgendamento {

    @Autowired
    private PagamentoService pagamentoService;

    @Scheduled(fixedRate = 60000)
    public void processarPagamentosPendentes() {
        pagamentoService.processarPagamentosPendentes();
    }
}
