CREATE TABLE cielo_log
(
    id              BINARY(16) NOT NULL,
    data_operacao   datetime NOT NULL,
    mensagem_resposta  VARCHAR(255),
    status_resposta VARCHAR(50),
    codigo_resposta VARCHAR(20),
    mensagem_erro   TEXT,
    corpo_resposta  TEXT,
    pagamento_id    BINARY(16) NOT NULL,
    CONSTRAINT pk_cielolog PRIMARY KEY (id)
);

ALTER TABLE cielo_log
    ADD CONSTRAINT FK_CIELOLOG_ON_PAGAMENTO FOREIGN KEY (pagamento_id) REFERENCES pagamento (id);