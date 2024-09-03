package com.br.marketplace.util;

import com.br.marketplace.exception.ValidacaoException;
import com.br.marketplace.model.enums.Bandeira;

import java.util.LinkedHashMap;
import java.util.Map;

public class CapturaBandeiraCartaoUtil {

    private static final Map<String, Bandeira> BANDEIRAS_MAP = new LinkedHashMap<>();

    static {
        BANDEIRAS_MAP.put("^4\\d{12}(\\d{3})?$", Bandeira.VISA);
        BANDEIRAS_MAP.put("^5[1-5]\\d{14}$", Bandeira.MASTERCARD);
        BANDEIRAS_MAP.put("^3[47]\\d{13}$", Bandeira.AMERICAN_EXPRESS);
        BANDEIRAS_MAP.put("^6(?:011|5\\d{2})\\d{12}$", Bandeira.DISCOVER);
        BANDEIRAS_MAP.put("^(?:2131|1800|35\\d{3})\\d{11}$", Bandeira.JCB);
        BANDEIRAS_MAP.put("^3(?:0[0-5]|[68]\\d)\\d{11}$", Bandeira.DINERS_CLUB);
        BANDEIRAS_MAP.put("^50\\d{14}$", Bandeira.AURA);
        BANDEIRAS_MAP.put("^(606282|3841)\\d{12,15}$", Bandeira.HIPERCARD);
        BANDEIRAS_MAP.put("^63(6297|6368)\\d{13}$", Bandeira.ELO);
    }

    public static Bandeira capturarBandeira(final String numeroCartao) {
        String numeroCartaoSanitizado = sanitizeNumeroCartao(numeroCartao);

        return BANDEIRAS_MAP.entrySet()
                .stream()
                .filter(entry -> numeroCartaoSanitizado.matches(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new ValidacaoException(
                        "Bandeira do cartão não encontrada."));
    }

    private static String sanitizeNumeroCartao(String numeroCartao) {
        return numeroCartao.replaceAll("\\s+", "").replaceAll("[^\\d]", "");
    }
}