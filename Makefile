# BD - Levanta la BD
up:
	docker compose up -d

# BD - Para y borra el contenedor de la BD
down:
	docker compose down

# Compilar
compile:
	mvnw.cmd clean compile

# Todos los tests
verify:
  mvnw.cmd verify

.PHONY: up down compile verify