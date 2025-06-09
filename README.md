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

## 🛠️ Configuración de la Base de Datos

La aplicación utiliza:

- **PostgreSQL** → para clientes y productos
- **MongoDB** → para las órdenes

### Usando Docker Compose

1. Iniciar los contenedores:
```bash
docker-compose up -d
```

Esto iniciará:
- PostgreSQL en el puerto 5432
- MongoDB en el puerto 27017

Las credenciales por defecto son:
- PostgreSQL:
  - Base de datos: ecommerce
  - Usuario: user
  - Contraseña: pass

## 🧱 Estructura de las Tablas

Las tablas se crean automáticamente al iniciar la aplicación gracias al archivo `schema.sql`:

```sql
CREATE TABLE IF NOT EXISTS clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

INSERT INTO clients (name, email) VALUES
('Juan Pérez', 'juan.perez@example.com'),
('María Gómez', 'maria.gomez@example.com'),
('Carlos López', 'carlos.lopez@example.com');

INSERT INTO products (name, price) VALUES
('Laptop', 999.99),
('Smartphone', 499.99),
('Tablet', 299.99);
```

> **Nota**: El script `schema.sql` se ejecuta automáticamente al iniciar la aplicación gracias a la configuración `spring.r2dbc.initialization-mode: always` en `application.yml`.

## 🔌 Endpoints Disponibles

### 📇 Clientes
- POST `/api/clients` - Crear un nuevo cliente
```json
{
    "name": "Juan Pérez",
    "email": "juan@example.com"
}
```

### 📦 Productos
- POST `/api/products` - Crear un nuevo producto
```json
{
    "name": "Producto 1",
    "price": 10.50
}
```

### 🧾 Órdenes

#### 📤 Request
POST `/api/orders`
```json
{
    "clientId": 1,
    "products": [
        {
            "productId": 1,
            "quantity": 2
        }
    ]
}
```
- `clientId`: ID del cliente (debe existir en clients)
- `products`: Lista con IDs de productos y cantidades

#### 📥 Response (Ejemplo exitoso)
```json
{
    "id": "6846287f102681e3481e7bf6",
    "customerId": 1,
    "items": [
        {
            "productId": 1,
            "quantity": 2
        }
    ],
    "total": 1999.98,
    "createdAt": "2025-06-08T19:19:11.2811302"
}
```
- `id`: Identificador generado automáticamente (MongoDB ObjectId)
- `customerId`: ID del cliente asociado
- `items`: Productos incluidos
- `total`: Precio total calculado
- `createdAt`: Fecha y hora de creación

> **✅ Nota**: Antes de crear una orden, asegúrate de que el cliente y productos existen:
> ```sql
> SELECT * FROM clients;
> SELECT * FROM products;
> ```

## 🧪 Pruebas

Para ejecutar las pruebas:
```bash
./mvnw test
```

## 📌 Notas Importantes

- Los datos iniciales se insertan automáticamente al ejecutar la app por primera vez
- Las bases de datos se ejecutan como contenedores y persisten los datos en volúmenes de Docker
- La API de órdenes usa MongoDB, mientras que los datos maestros (clientes/productos) usan PostgreSQL
- El ID del cliente y de productos deben ser válidos para crear una orden

## 🌐 Acceso a la Aplicación

Una vez ejecutado todo, la aplicación estará disponible en:
```
http://localhost:8080
```

Puedes probar los endpoints usando herramientas como Postman, Insomnia o el plugin REST Client de VS Code.
