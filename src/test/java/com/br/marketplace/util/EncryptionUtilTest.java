package com.br.marketplace.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;


class EncryptionUtilTest {

    @BeforeEach
    void configurar() {
        ReflectionTestUtils.setField(EncryptionUtil.class, "key", "1234567890123456".getBytes());
    }

    @Test
    void testarCriptografiaEDescriptografia() throws Exception {
        String textoOriginal = "TextoTeste1234";

        String textoCriptografado = EncryptionUtil.encrypt(textoOriginal);
        assertNotNull(textoCriptografado);
        assertNotEquals(textoOriginal, textoCriptografado);

        String textoDescriptografado = EncryptionUtil.decrypt(textoCriptografado);
        assertNotNull(textoDescriptografado);
        assertEquals(textoOriginal, textoDescriptografado);
    }
}
