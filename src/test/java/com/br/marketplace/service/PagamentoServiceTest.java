package com.br.marketplace.service;

import com.br.marketplace.dto.CancelarPagamentoDto;
import com.br.marketplace.dto.CriarCartaoDto;
import com.br.marketplace.dto.CriarPagamentoDto;
import com.br.marketplace.exception.ValidacaoException;
import com.br.marketplace.model.Pagamento;
import com.br.marketplace.model.enums.FormaPagamento;
import com.br.marketplace.model.enums.Status;
import com.br.marketplace.repository.PagamentoRepository;
import com.br.marketplace.util.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;
    @Mock
    private PagamentoRepository pagamentoRepository;
    @Mock
    private CieloLogService cieloLogService;
    private CriarPagamentoDto dto = new CriarPagamentoDto(BigDecimal.valueOf(50), "Teste", FormaPagamento.CREDITO, new CriarCartaoDto("Teste",
            "5400145586317057", "08/2026", "123"));;
    @Captor
    private ArgumentCaptor<Pagamento> pagamentoCaptor;

    @BeforeEach
    void configurar() {
        ReflectionTestUtils.setField(EncryptionUtil.class, "key", "1234567890123456".getBytes());
    }

    @Test
    void deveriaCriar() {
        pagamentoService.criar(dto);

        then(pagamentoRepository).should().save(pagamentoCaptor.capture());
        var pagamento = pagamentoCaptor.getValue();
        assertEquals(pagamento.getFormaPagamento(), dto.formaDePagamento());
        assertEquals(pagamento.getValor(), dto.valor());
        assertNotNull(pagamento.getCartao());
    }

    @Test
    void naoDeveriaCriarPorCausaDoNumeroInvalido() {
        dto = new CriarPagamentoDto(BigDecimal.valueOf(50), "Teste", FormaPagamento.CREDITO, new CriarCartaoDto("Teste",
                "1234567890123456", "08/2026", "123"));

        assertThrows(ValidacaoException.class, () -> pagamentoService.criar(dto));
    }

    @Test
    void deveriaCancelar() {
        var dtoCancelar = new CancelarPagamentoDto(UUID.randomUUID());
        var pagamento = new Pagamento(dto);

        given(pagamentoRepository.findById(any())).willReturn(Optional.of(pagamento));

        pagamentoService.cancelar(dtoCancelar);

        assertEquals(Status.CANCELADO, pagamento.getStatus());

        then(pagamentoRepository).should().save(pagamentoCaptor.capture());
        var pagamentoCapturado = pagamentoCaptor.getValue();
        assertEquals(Status.CANCELADO, pagamentoCapturado.getStatus());
    }


    @Test
    void naoDeveriaCancelarPorCausaQueNaoExiste() {
        var dto = new CancelarPagamentoDto(UUID.randomUUID());

        given(pagamentoRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(ValidacaoException.class, () -> pagamentoService.cancelar(dto));
    }
}