INSERT INTO usuario (id, nome, email, senha)
VALUES (UNHEX(REPLACE(UUID(), '-', '')), 'Admin', 'admin@gmail.com', '$2a$10$YZWq2Hd9hHFFv7VgDu7dHeyatX3hTSvuR4');
