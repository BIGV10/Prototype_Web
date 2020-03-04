# Prototype WEB
Используется Spring Boot, Vue.js, Axios, Postgres, JWT...

http://bigv.ddns.net:9000/api/equipments - доступен только по токену авторизации

http://bigv.ddns.net:9000/api/requests - доступен только по токену авторизации

http://bigv.ddns.net:9001

### Для запуска нужны
#### Docker с Postgres
Запуск `docker-compose -f docker-postgres.yml up`

Сам docker-postgres.yml:
```
version: '3'

volumes:
  prototype_postgres_data:
    driver: local

services:
  prototype_postgres:
    container_name: prototype_postgres
    image: postgres:12
    volumes:
      - prototype_postgres_data:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: prototype
      POSTGRES_USER: docker
      POSTGRES_PASSWORD: docker
    ports:
      - 5432:5432
```
#### <s>Docker с Keycloak
Запуск `docker-compose -f docker-keycloak.yml up`

Сам docker-keycloak.yml:</s>
```
version: '3'

volumes:
  prototype_postgres_keycloak:
    driver: local

services:
  postgres_keycloak:
    container_name: postgres_keycloak
    image: postgres:12
    volumes:
      - prototype_postgres_keycloak:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: keycloak
      POSTGRES_USER: keycloak
      POSTGRES_PASSWORD: password
    ports:
      - 8090:5432
  keycloak:
    image: jboss/keycloak
    environment:
      DB_VENDOR: POSTGRES
      DB_ADDR: postgres_keycloak
      DB_DATABASE: keycloak
      DB_SCHEMA: public
      DB_USER: keycloak
      DB_PASSWORD: password
      KEYCLOAK_USER: admin
      KEYCLOAK_PASSWORD: Pa55w0rd
    ports:
      - 8091:8080
    depends_on:
      - postgres_keycloak
```
### Запуск
Enviroment variables для backend в IDE:
```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/prototype;SPRING_DATASOURCE_USERNAME=docker;SPRING_DATASOURCE_PASSWORD=docker
```
Для запуска backend вне IDE:
```
cd backend
mvn clean install
java -jar target/backend-0.0.1-SNAPSHOT.jar \
--spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/prototype \
--spring.datasource.username=docker \
--spring.datasource.password=docker
```
Для запуска frontend вне IDE:
```
cd frontend
npm run serve
```