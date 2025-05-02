# Libreta API - GA7220501096AA2EV01
Codificación de módulos del software según requerimientos del proyecto uso de JDBC.
Aplicación Java para la gestión de una libreta de contactos, que permite almacenar información de nombres, domicilios y teléfonos mediante una API REST.

## Nota de uso
1. Clona el proyecto git clone https://github.com/joseluis1061/GA7220501096AA2EV01
2. Modifica el archivo application-example.properties cambia su nombre a application.properties
3. En el archivo application.properties cambia el valor del usuario spring.datasource.username=postgres y password spring.datasource.password=password por los de tu base de datos
4. Asegurate de crear la base de datos del proyecto. Puedes hacerlo manualmente o usando el archivo libreta de este repositorio
5. El proyecto corre en el local host 8080
6. Puedes usar swagger en la url http://localhost:8080/swagger-ui/index.html#/

## Tecnologías utilizadas

- **Java 21**: Lenguaje de programación utilizado
- **Spring Boot 3.x**: Framework para el desarrollo de aplicaciones Java
- **Spring Data JPA**: Para la capa de persistencia y mapeo objeto-relacional
- **PostgreSQL 14**: Base de datos relacional
- **Swagger / OpenAPI 3**: Para la documentación de la API
- **Maven**: Para la gestión de dependencias y construcción del proyecto
- **Lombok**: Para reducir código repetitivo (getters, setters, constructores)

## Estructura del proyecto

```
GA7220501096AA2/
├── pom.xml
├── libreta.sql           # Script para recrear la base de datos
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── libretaapp/
        │               ├── LibretaAppApplication.java
        │               ├── config/
        │               │   ├── DevConfig.java
        │               │   ├── TestConfig.java
        │               │   ├── ProdConfig.java
        │               │   └── SwaggerConfig.java
        │               ├── controller/
        │               │   ├── RegistroController.java
        │               │   └── ViewController.java
        │               ├── dto/
        │               │   └── RegistroDTO.java
        │               ├── model/
        │               │   └── Registro.java
        │               ├── repository/
        │               │   └── RegistroRepository.java
        │               └── service/
        │                   └── RegistroService.java
        └── resources/
            ├── application.properties
            ├── application-dev.properties
            ├── application-test.properties
            └── application-prod.properties
```

## Arquitectura (Patrón de Capas)

Esta aplicación sigue una arquitectura en capas:

1. **Capa de Presentación (Controllers)**: 
   - Gestiona las solicitudes HTTP y devuelve respuestas apropiadas
   - Maneja la validación básica de las solicitudes
   - Convierte entre DTO y entidades

2. **Capa de Lógica de Negocio (Services)**:
   - Implementa la lógica de negocio
   - Actúa como intermediario entre controladores y repositorios
   - Maneja las reglas de negocio y validaciones

3. **Capa de Acceso a Datos (Repositories)**:
   - Interactúa directamente con la base de datos
   - Implementa operaciones CRUD
   - Define consultas específicas

4. **Capa de Modelo de Datos (Models/Entities)**:
   - Define la estructura de datos
   - Mapea la estructura de la base de datos

5. **DTOs (Data Transfer Objects)**:
   - Objetos utilizados para transferir datos entre capas
   - Separa la representación externa de la interna

## Endpoints de la API

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/api/registros` | Obtiene todos los registros |
| GET | `/api/registros/{id}` | Obtiene un registro por su ID |
| POST | `/api/registros` | Crea un nuevo registro |
| PUT | `/api/registros/{id}` | Actualiza un registro existente |
| DELETE | `/api/registros/{id}` | Elimina un registro |
| GET | `/api/registros/buscar/nombre/{nombre}` | Busca registros por nombre |
| GET | `/api/registros/buscar/telefono/{telefono}` | Busca registros por teléfono |

### Ejemplos de uso

#### Crear un nuevo registro (POST /api/registros)
```json
{
  "nombre": "Juan Pérez",
  "domicilio": "Calle Principal 123",
  "telefono": "555-1234"
}
```

#### Actualizar un registro (PUT /api/registros/{id})
```json
{
  "nombre": "Juan Pérez Actualizado",
  "domicilio": "Nueva Dirección 456",
  "telefono": "555-5678"
}
```

## Swagger / OpenAPI

La API está documentada utilizando Swagger/OpenAPI, lo que permite explorar y probar los endpoints de forma interactiva.

### Acceso a Swagger

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Documentación OpenAPI**: http://localhost:8080/api-docs

### Funcionalidades de Swagger

- Exploración de todos los endpoints disponibles
- Prueba interactiva de endpoints directamente desde el navegador
- Visualización de modelos de datos y ejemplos
- Documentación detallada de parámetros y respuestas

## Configuración de entornos

La aplicación soporta diferentes entornos de ejecución mediante perfiles de Spring:

- **dev**: Entorno de desarrollo local
- **test**: Entorno de pruebas
- **prod**: Entorno de producción

### Activación de perfiles

```bash
# Ejecución con perfil de desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ejecución con perfil de pruebas
mvn spring-boot:run -Dspring-boot.run.profiles=test

# Ejecución con perfil de producción
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

## Ejecución del proyecto

### Requisitos previos

- Java 21 o superior
- Maven 3.8 o superior
- PostgreSQL 14 o superior

### Pasos para ejecutar

1. Clonar el repositorio:
   ```bash
   git clone [URL_DEL_REPOSITORIO]
   cd GA7220501096AA2
   ```

2. Crear la base de datos en PostgreSQL:
   ```bash
   # Acceder a PostgreSQL
   psql -U postgres
   
   # Crear la base de datos
   CREATE DATABASE libreta;
   
   # Salir
   \q
   
   # Ejecutar el script de creación de tablas
   psql -U postgres -d libreta -f libreta.sql
   ```

3. Compilar el proyecto:
   ```bash
   mvn clean install
   ```

4. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

5. Acceder a la aplicación:
   - API: http://localhost:8080/api/registros
   - Swagger UI: http://localhost:8080/swagger-ui.html

## Base de datos

La aplicación utiliza una base de datos PostgreSQL que puede ser recreada mediante el archivo `libreta.sql` incluido en el proyecto. Este script contiene todo el código necesario para crear la estructura de la base de datos:

```sql
-- Archivo: libreta.sql
-- Descripción: Script para crear la base de datos y tablas necesarias para la aplicación Libreta

-- Crear la tabla de registros si no existe
CREATE TABLE IF NOT EXISTS public.registros (
    id SERIAL PRIMARY KEY,
    nombre CHARACTER VARYING,
    domicilio CHARACTER VARYING,
    telefono CHARACTER VARYING
);

-- Comentarios de la tabla y columnas
COMMENT ON TABLE public.registros IS 'Tabla para almacenar los contactos de la libreta';
COMMENT ON COLUMN public.registros.id IS 'Identificador único autoincremental';
COMMENT ON COLUMN public.registros.nombre IS 'Nombre del contacto';
COMMENT ON COLUMN public.registros.domicilio IS 'Dirección del contacto';
COMMENT ON COLUMN public.registros.telefono IS 'Número telefónico del contacto';

-- Datos iniciales de ejemplo (opcional)
INSERT INTO public.registros (nombre, domicilio, telefono) VALUES
('Juan Pérez', 'Calle Principal 123', '555-1234'),
('María García', 'Avenida Central 456', '555-5678'),
('Carlos Rodríguez', 'Plaza Mayor 789', '555-9012');
```

Para utilizar este script, sigue las instrucciones en la sección "Pasos para ejecutar".

## Diagrama de flujo de datos

```
Cliente HTTP → Controller → Service → Repository → Base de datos
     ↑                ↓
     └────────────────┘
         Respuesta
```

## Contribución

Para contribuir al proyecto, sigue estos pasos:

1. Haz un fork del repositorio
2. Crea una rama para tu funcionalidad: `git checkout -b feature/nueva-funcionalidad`
3. Haz commit de tus cambios: `git commit -m 'Añadir nueva funcionalidad'`
4. Empuja tus cambios: `git push origin feature/nueva-funcionalidad`
5. Envía un Pull Request

## Contacto

Para más información, contacta a:
- Nombre: [Tu Nombre]
- Email: [Tu Email]
