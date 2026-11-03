# Spring Boot + Apache Kafka Order Demo

A runnable starter project demonstrating a REST API -> Kafka producer -> Kafka topic -> Kafka consumer flow.

## Requirements
- JDK 17
- Maven 3.8+
- Docker Desktop / Docker Engine
- Postman or curl

## Run
1. Start Docker.
2. From this folder run:
   ```bash
   docker compose up -d
   docker compose ps
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
   Or import `pom.xml` into IntelliJ IDEA and run `KafkaDemoApplication`.
4. Send a request:
   ```bash
   curl -i -X POST http://localhost:8080/api/orders \
     -H "Content-Type: application/json" \
     -d '{"orderId":"ORD101","productName":"Laptop","amount":55000}'
   ```
5. Check the Spring Boot console for the producer confirmation and consumer output.
6. Stop Kafka when finished:
   ```bash
   docker compose down
   ```

## Endpoint
- `POST /api/orders` — publishes an order-created event to Kafka.
- `GET /api/orders/health` — simple API health check.

## Important notes
- This is a learning/demo project, not production-hardened.
- The HTTP endpoint waits for the Kafka send result before returning `202 Accepted`.
- In-memory order data is not persisted to a database.
- For production, add authentication, schema/version management, retries and a dead-letter topic, idempotency, metrics, and database/outbox handling as required.
