# 📦 Gestión de Pedidos - Spring Boot (MongoDB + PostgreSQL R2DBC)

Aplicación web para gestión de pedidos usando **Spring Boot WebFlux**, **MongoDB** y **PostgreSQL con R2DBC**.

---

## ⚙️ Requisitos

- **Java 17 o superior**
  > Probado con Java 24.0.1
- **Maven 3.8+**
- **Docker + Docker Compose**
- **DBeaver** (opcional, para gestión de BD con JDBC)

---

## 🚀 Tecnologías utilizadas

| Tecnología      | Descripción                            |
|----------------|----------------------------------------|
| Spring Boot     | WebFlux + R2DBC + MongoDB             |
| MongoDB         | Base de datos reactiva para documentos |
| PostgreSQL      | Base de datos relacional con R2DBC     |
| Docker          | Contenedores para MongoDB y PostgreSQL |
| Lombok          | Reducción de boilerplate Java          |
| WebFlux         | Programación reactiva                  |

---

## 🐳 Docker Setup

Este proyecto usa contenedores Docker para MongoDB y PostgreSQL. La configuración se encuentra en `docker-compose.yml`.

### ▶️ Ejecutar servicios

```bash
docker-compose up -d

```

▶️ Ejecución del proyecto

```bash
./mvnw spring-boot:run

```
