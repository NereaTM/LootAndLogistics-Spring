# 🐉 Loot and Logistics

Juego de gestión de un gremio de transporte en un mundo de fantasía medieval.
Proyecto formativo para aprender arquitectura hexagonal con Spring Boot.

## Stack

Java 25 · Spring Boot 4.1 · Maven (multimódulo) · MapStruct · OpenAPI Generator · springdoc

## Módulos

| Módulo | Qué contiene |
|---|---|
| `domain` | Entidades y puertos. Java puro |
| `application` | Implementación de los casos de uso |
| `infrastructure` | Adaptadores de salida (persistencia en memoria) |
| `api-rest` | Adaptador de entrada REST (API-first desde `openapi.yml`) |
| `boot` | Arranque de Spring Boot y configuración |

## Arrancar

    ./mvnw clean verify
    ./mvnw -pl boot -am spring-boot:run

## Estado

🚧 V1 — Quest Board (en desarrollo)