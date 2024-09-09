# API de Marketplace

Esta é uma API de marketplace desenvolvida com Java 17 e Spring 3.3.3, utilizando MySQL 9.0.1. A API permite operações básicas de marketplace e requer configuração adequada para o funcionamento.

## Início Rápido

### Configuração

1. Clone este repositório:
    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd <NOME_DO_PROJETO>
    ```

2. Certifique-se de ter o [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) e o [MySQL 9.0.1](https://dev.mysql.com/downloads/) instalados.

3. Configure as variáveis de ambiente necessárias diretamente no sistema. As variáveis são:

    - `DATASOURCE_URL` - URL do servidor de banco de dados MySQL
    - `DATASOURCE_USERNAME` - Nome de usuário para o banco de dados MySQL
    - `DATASOURCE_PASSWORD` - Senha para o banco de dados MySQL
    - `ALLOWED_ORIGINS` - Origem permitida para requisições CORS
    - `CIELO_MERCHANT_KEY` - Chave do comerciante para integração com a API da Cielo
    - `CIELO_MERCHANT_ID` - ID do comerciante para integração com a API da Cielo

   Exemplo de configuração:
    ```env
    DATASOURCE_URL=localhost/marketplace
    DATASOURCE_USERNAME=root
    DATASOURCE_PASSWORD=sua_senha
    ALLOWED_ORIGINS=http://localhost:4200
    CIELO_MERCHANT_KEY=sua_chave_cielo
    CIELO_MERCHANT_ID=seu_id_cielo
    ```

4. Compile e execute a aplicação:

    ```bash
    ./mvnw clean install
    ./mvnw spring-boot:run
    ```

5. Acesse a API em [http://localhost:8080](http://localhost:8080).

### Credenciais Iniciais

- **Usuário Admin:** admin@gmail.com
- **Senha:** 123456

### Contribuindo

Sinta-se à vontade para enviar pull requests e relatar problemas.

### Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
