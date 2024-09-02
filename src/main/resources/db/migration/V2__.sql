CREATE TABLE cartao
(
    id                  BINARY(16)   NOT NULL,
    encrypted_nome      VARCHAR(255) NULL,
    encrypted_numero    VARCHAR(255) NULL,
    encrypted_expiracao VARCHAR(255) NULL,
    encrypted_codigo    VARCHAR(255) NULL,
    encrypted_bandeira  VARCHAR(255) NULL,
    CONSTRAINT pk_cartao PRIMARY KEY (id)
);