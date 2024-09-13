package com.br.marketplace.http;

import com.br.marketplace.dto.AlterarAprovarPagamentoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("marketplace-pedido")
public interface PedidoClient {
    @RequestMapping(method =  RequestMethod.PUT, value = "/pedidos/pago")
    void atualizaPagamento(@RequestBody AlterarAprovarPagamentoDto dto);
}
