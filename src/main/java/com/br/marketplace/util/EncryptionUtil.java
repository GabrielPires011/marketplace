package com.br.marketplace.util;

import com.br.marketplace.exception.ValidacaoException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class EncryptionUtil {

    private static final String ALGORITHM = "AES";
    private static byte[] key;

    @Value("${chave.encryption}")
    private String CHAVE_ENCRYPTION;

    @PostConstruct
    public void init() {
        key = CHAVE_ENCRYPTION.getBytes();
        if (key.length != 16) {
            throw new IllegalArgumentException("Chave de criptografia inválida. A chave deve ter 16 bytes de comprimento.");
        }
    }

    public static String encrypt(Object data) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new ValidacaoException("Algoritmo de criptografia não encontrado: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new ValidacaoException("Padding de criptografia não encontrado: " + e.getMessage());
        }

        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new ValidacaoException("Chave de criptografia inválida: " + e.getMessage());
        }

        try {
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.toString().getBytes()));
        } catch (IllegalBlockSizeException e) {
            throw new ValidacaoException("Tamanho do bloco inválido durante a criptografia: " + e.getMessage());
        } catch (BadPaddingException e) {
            throw new ValidacaoException("Padding inválido durante a criptografia: " + e.getMessage());
        }
    }

    public static String decrypt(String encryptedData) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new ValidacaoException("Algoritmo de criptografia não encontrado: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            throw new ValidacaoException("Padding de criptografia não encontrado: " + e.getMessage());
        }

        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new ValidacaoException("Chave de criptografia inválida: " + e.getMessage());
        }

        try {
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
        } catch (IllegalBlockSizeException e) {
            throw new ValidacaoException("Tamanho do bloco inválido durante a descriptografia: " + e.getMessage());
        } catch (BadPaddingException e) {
            throw new ValidacaoException("Padding inválido durante a descriptografia: " + e.getMessage());
        }
    }

}


