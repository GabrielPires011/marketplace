CREATE TABLE usuario
(
    id    BINARY(16)   NOT NULL,
    nome  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    senha VARCHAR(255) NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
);