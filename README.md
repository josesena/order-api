## Order API

Order API um sistema para processar pedidos utilizando **Spring Boot**, **MongoDB** e **Apache Kafka**

- Java 17
- SpringBoot 3.4.3
- Spring Data MongoDB
- Apache Kafka
- Docker e Docker Compose
- Lombok


### Requisitos

- Docker e Docker Compose
- Java 17
- Apache Kafka rodando localmente em um container

### Configuação e Execução
#### Subindo os containers do MongoDB e Kafka
 
```console
# Na raiz do projeto, execute
docker-compose up -d
```
#### Executando a Aplicação

```console
./mvnw spring-boot:run
```
#### Estrutua dos Tópicos Kafka

- order-create-topic: Recebe os pedidos criados
- order-processing-topic: Processa e envia os pedidos calculados

#### Seguindo os passos para enviar uma mensagem na console do Kafka

```console
# Entrar no terminal de um container
docker exec -it <nome_contatiner> bash 
# Criar um tópico Kafka chamado order-create-topic
kafka-topics --create --topic=order-create-topic --bootstrap-server=localhost:9092 --partitions=1
# Criar um tópico Kafka chamado order-processing-topic
kafka-topics --create --topic=order-processing-topic --bootstrap-server=localhost:9092 --partitions=1
# Enviar uma mansagem para o tópico Kafka
kafka-console-producer --bootstrap-server=locahost:9092 --topic=order-create-topic
# Mensagem
{"orderNumber": 12345, "products": [{"name": "Produto A", "price": 15.50, "quantity": 5}]}
# Ler a mensagem de um tópico
kafka-console-consumer --bootstrap-server=locahost:9092 --topic=order-processing-topic
```