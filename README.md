# DeliveryRice API

RESTful backend para una plataforma de delivery de comida, construido con Java 21 y Spring Boot.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=springsecurity)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql)
![Docker](https://img.shields.io/badge/Docker-Containerized-2496ED?logo=docker)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?logo=swagger)

---

## Sobre el proyecto

DeliveryRice simula el backend de una plataforma de delivery especializada en platos de arroz.

Nació como Proyecto de Fin de Grado del ciclo de Desarrollo de Aplicaciones Multiplataforma (DAM), pero no es simplemente ese proyecto subido a GitHub: lo he reescrito desde cero aplicando prácticas de desarrollo backend profesional — arquitectura por capas, DTOs, seguridad con JWT, documentación con Swagger y despliegue con Docker — para llevarlo del nivel académico a algo más cercano a lo que se pide en un entorno real.

## Por qué refactoricé el proyecto

- **Records para DTOs Request y Response:** en la primera versión ya se trabajaba con DTOs, pero en esta se han introducido los records y se ha reducido el ruido al clasificarlos en Responses y Requests, simplificando el flujo de entrada y salida de parámetros en los controllers.
- **JWT como sistema de autenticación:** en mi primera versión no tenía control sobre los roles y los permisos a los que un usuario accedía, lo dejaba todo en manos del front, cargando pantallas según el tipo de usuario. Con esta nueva versión solucionamos este problema.
- **Uso de programación funcional, lambdas y mappers:** en el primer proyecto no se utilizaron esas funciones de Java 8, se recorrían todas las clases a través de bucles. Aplicando programación funcional y mappers facilitamos operaciones como la generación de pedidos o la actualización de totales y stocks, haciendo el código mucho más limpio y escalable.
- **OpenAPI / Swagger:** en la primera versión no hice uso de ningún sistema de documentación; en esta apliqué buenas prácticas de desarrollo y principios SOLID.
- **Eliminación de entidades Carrito e ItemCarrito:** en la primera versión tenía entidades Carrito–ItemCarrito y Pedido–DetallePedido. Con perspectiva, esas primeras entidades añadían complicación y sobreingeniería al proyecto: no es necesario persistir un carrito y sus ítems cuando a la postre guardan prácticamente los mismos estados que el Pedido y sus Detalles.
- **Uso de ResponseEntity y manejador global de excepciones:** en esta segunda versión aplico las prácticas recomendadas para construir APIs REST robustas y escalables, mejorando la usabilidad y comprensión de las respuestas.

## Objetivos del proyecto

- Diseñar una API REST siguiendo buenas prácticas del sector.
- Implementar autenticación con JWT y autorización basada en roles.
- Aplicar una arquitectura por capas limpia (Controller → Service → Repository).
- Separar entidades de dominio de los contratos de la API mediante DTOs.
- Centralizar el manejo de excepciones y validar las peticiones entrantes.
- Documentar todos los endpoints con Swagger/OpenAPI.
- Containerizar la aplicación con Docker y persistir datos en MySQL.

## Funcionalidades

**Autenticación**
- Registro y login de usuarios
- Autenticación stateless con JWT
- Cifrado de contraseñas con BCrypt
- Autorización basada en roles (ADMIN, CLIENT, DELIVER)

**Usuarios**
- Recuperación del usuario autenticado
- Gestión de perfil
- Gestión de direcciones de entrega

**Productos**
- CRUD de productos
- Categorías y disponibilidad
- Gestión de stock

**Pedidos**
- Creación de pedidos
- Consulta de pedidos e historial

**Infraestructura**
- Docker y Docker Compose
- Persistencia en MySQL con volumen
- Documentación Swagger
- Manejo global de excepciones
- Validación con Jakarta Bean Validation

## Stack técnico

| Categoría | Tecnologías |
|-----------|--------------|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3 |
| Seguridad | Spring Security, JWT |
| Persistencia | Spring Data JPA, Hibernate |
| Base de datos | MySQL |
| Validación | Jakarta Bean Validation |
| Documentación | Springdoc OpenAPI (Swagger) |
| Build | Maven |
| Contenedores | Docker, Docker Compose |
| Utilidades | Lombok |

## Arquitectura

```
Client
  │
  ▼
REST Controllers
  │
  ▼
Service Layer
  │
  ▼
Repository Layer
  │
  ▼
MySQL
```

- **Controller** — maneja peticiones y respuestas HTTP.
- **Service** — contiene la lógica de negocio.
- **Repository** — acceso a datos vía Spring Data JPA.
- **Model** — modelo de dominio.
- **DTO** — contrato de la API (Request y Response, como records).
- **Mapper** — convierte entre Entity y DTO.
- **Security** — autenticación y autorización.
- **Exception** — manejo centralizado de errores.

Los módulos de entidad (`user`, `order`, `product`, `direction`) siguen todos la misma estructura interna: `controller`, `service`, `repository`, `model`, `mapper` y `dto`. El módulo `auth` es distinto a propósito — no tiene repositorio ni mapper propios porque no gestiona una entidad de dominio nueva, sino el proceso de autenticación en sí, así que su paquete se compone de `controller`, `service`, `dto` y `jwt` (la lógica de generación y validación de tokens).

## Módulos

| Módulo | Descripción |
|--------|-------------|
| Auth | Login, registro y generación de JWT |
| User | Gestión de usuarios |
| Product | Catálogo y administración de productos |
| Direction | Direcciones de entrega |
| Order | Gestión de pedidos |
| Security | Configuración de Spring Security |
| Exception | Manejo global de excepciones |
| Config | Configuración de la aplicación |

## Estructura del proyecto

```
src/main/java/com/mol211/deliveryrice
├── auth
│   ├── controller
│   ├── service
│   ├── dto
│   └── jwt
├── user
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── mapper
│   └── dto
├── product
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── mapper
│   └── dto
├── order
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── mapper
│   └── dto
├── direction
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── mapper
│   └── dto
├── config
├── exception
└── common
```

## Manejo de excepciones

El manejo de errores está centralizado mediante un `GlobalExceptionHandler` (con `@RestControllerAdvice`), que captura las excepciones lanzadas en cualquier capa y las traduce en respuestas HTTP consistentes, en vez de dejar que cada controlador gestione sus propios errores por separado.

Además del manejo de excepciones genéricas de Spring (validación, errores de tipo, etc.), el proyecto define excepciones personalizadas para los casos de negocio propios — por ejemplo, para recursos no encontrados o conflictos de datos — de forma que el error devuelto al cliente sea claro y específico en vez de un `500` genérico.


## Base de datos

MySQL como base de datos relacional. La inicialización usa `data.sql`, que carga usuarios y productos de ejemplo en el primer arranque. Corre dentro de Docker con persistencia mediante volumen.

### Usuarios de prueba

El script `data.sql` crea automáticamente dos usuarios demo para poder probar la API sin necesidad de registrarte:

| Rol | Email | Password |
|-----|-------|----------|
| ADMIN | `admin@deliveryrice.com` | `Admin1234` |
| CLIENT | `cliente@deliveryrice.com` | `Admin1234` |

Las contraseñas se almacenan cifradas con BCrypt.

También se cargan productos de ejemplo (arroces, entrantes, postres y bebidas) para tener el catálogo poblado desde el primer arranque.

## Docker

```bash
# Levantar todo el entorno
docker compose up --build

# Detener
docker compose down
```

Incluye la API de Spring Boot, la base de datos MySQL y un volumen persistente.

## Cómo ejecutarlo

**Requisitos:** Java 21, Maven, Docker, Docker Compose

```bash
git clone https://github.com/Mol211/deliveryrice.git
cd deliveryrice

# Con Docker
docker compose up --build

# Local
mvn spring-boot:run
```

## Documentación de la API

Una vez levantada la aplicación:

```
http://localhost:8080/swagger-ui/index.html
```

Swagger permite explorar los endpoints, autenticarse con JWT y ejecutar peticiones directamente.

| Módulo | Endpoint |
|--------|----------|
| Auth | `/auth/**` |
| Users | `/api/v1/users/**` |
| Products | `/api/v1/products/**` |
| Directions | `/api/v1/directions/**` |
| Orders | `/api/v1/orders/**` |

## Testing

Todavía no hay tests automatizados — es lo siguiente que voy a añadir (unitarios de servicio y tests de integración de los controladores con Testcontainers).

## Estado del proyecto

| Feature | Estado |
|---------|--------|
| Autenticación y JWT | Hecho |
| Módulo de usuarios | Hecho |
| Módulo de productos | Hecho |
| Módulo de direcciones | Hecho |
| Módulo de pedidos | Hecho |
| Validación y excepciones globales | Hecho |
| Documentación Swagger | Hecho |
| Docker | Hecho |
| Tests automatizados | Pendiente |
| CI/CD | Pendiente |

## Próximos pasos

- Tests con JUnit, Mockito y Testcontainers
- CI/CD con GitHub Actions
- Paginación y filtrado en los listados de productos y pedidos


## Autor

Víctor Molins Martínez — Java Backend Developer

[GitHub](https://github.com/Mol211) · [LinkedIn](https://www.linkedin.com/in/victor-molins/)
