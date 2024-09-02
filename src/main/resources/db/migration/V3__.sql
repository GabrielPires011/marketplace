CREATE TABLE pagamento
(
    id              BINARY(16)   NOT NULL,
    valor           DECIMAL NULL,
    status          VARCHAR(255) NULL,
    descricao       VARCHAR(255) NULL,
    cartao_id       BINARY(16)   NULL,
    forma_pagamento VARCHAR(255) NULL,
    CONSTRAINT pk_pagamento PRIMARY KEY (id)
);

ALTER TABLE pagamento
    ADD CONSTRAINT FK_PAGAMENTOS_ON_CARTAO FOREIGN KEY (cartao_id) REFERENCES cartao (id);