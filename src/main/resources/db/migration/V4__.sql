INSERT INTO usuario (id, nome, email, senha)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Admin', 'admin@gmail.com', '$2a$10$/0.O/kjLvIzkIIH9ibLH2.sfebV5uQsIsxI79P2JrjZrIcKYm4hDa');
