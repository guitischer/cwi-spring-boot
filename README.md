# Desafio Técnico - Sistema de Votação

## Sobre
### Objetivo
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. 
A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API 
REST: 
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo 
determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é 
identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.
Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces 
pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que 
não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

### Diagrama do Banco de Dados

![image](https://user-images.githubusercontent.com/15661024/183253448-1b14b3a0-27a3-4a4c-b4b9-9c27f4bb8aea.png)

## Tecnologias

- [Java 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [Spring Boot 2.7.2](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)

## Deploy da Aplicação

### Requisitos

- [JDK 18](https://www.oracle.com/java/technologies/javase/jdk18-archive-downloads.html)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/download.cgi)
- [Git](https://git-scm.com/)

### Deploy via Docker

1. Clone o repositório
   
    ```

    $ https://github.com/guitischer/desafio-cooperativismo.git
    ```
2. Acesse a pasta do repositório clonado
   
    ```

    $ cd desafio-cooperativismo
    ```
3. Faça o build da aplicação usando o Maven
   
    ```

    $ .\mvnw install -DskipTests
    ```
4. Faça o build da imagem usando o Docker
   
    ```

    $ docker build -t desafio-cooperativismo.jar .
    ```

5. Inicialize a API e o banco de dados PostgreSQL usando o Docker Compose
   
    ```

    $ docker-compose up -d
    ```


## Autor

* **Guilherme Tischer Lopes** - [LinkedIn](https://www.linkedin.com/in/guilherme-tischer/)


