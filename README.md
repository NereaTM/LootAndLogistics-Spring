# 🐉 Loot and Logistics

Juego de gestión de un gremio de transporte en un mundo de fantasía medieval.
Proyecto formativo para aprender arquitectura hexagonal con Spring Boot.

## Stack

## Stack

![Java](https://img.shields.io/badge/java_25-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring_boot_4.1-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgresql_18-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/flyway-%23CC0200.svg?style=for-the-badge&logo=flyway&logoColor=white)
![Hibernate](https://img.shields.io/badge/JPA_/_Hibernate-%2359666C.svg?style=for-the-badge&logo=hibernate&logoColor=white)
![MapStruct](https://img.shields.io/badge/mapstruct-%23555555.svg?style=for-the-badge)
![OpenAPI](https://img.shields.io/badge/openapi-%236BA539.svg?style=for-the-badge&logo=openapiinitiative&logoColor=white)
![Swagger](https://img.shields.io/badge/swagger-%2385EA2D.svg?style=for-the-badge&logo=swagger&logoColor=black)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![JUnit5](https://img.shields.io/badge/junit5-%2325A162.svg?style=for-the-badge&logo=junit5&logoColor=white)
![Testcontainers](https://img.shields.io/badge/testcontainers-%23291A3F.svg?style=for-the-badge&logo=testcontainers&logoColor=white)

## Requisitos

- Java 25
- Docker Desktop (para la BD y los tests de integración)

## Módulos

| Módulo | Qué contiene                                                            |
|---|-------------------------------------------------------------------------|
| `domain` | Entidades y puertos. Java puro                                          |
| `application` | Implementación de los casos de uso                                      |
| `infrastructure` | Adaptadores de salida: guarda los encargos en PostgreSQL (JPA + Flyway) |
| `api-rest` | Adaptador de entrada REST (API-first desde `openapi.yml`)               |
| `boot` | Arranque de Spring Boot y configuración                                 |

## Primera vez

    Copia `.env.example` como `.env` y rellena usuario y contraseña de la BD.

## Arrancar

    docker compose up -d #levanta PostgreSQL en Docker
    ./mvnw -pl boot -am spring-boot:run  #Aplicacion

## Endpoints

| Método | Ruta | Acción            |
|---|---|-------------------|
| POST | `/quests` | Crear encargo     |
| GET | `/quests/{id}` | Consultar encargo |
| GET | `/quests` | Listar todo       |

## Tests
    ./mvnw clean verify  #Test
