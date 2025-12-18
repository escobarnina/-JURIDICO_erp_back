# JURIDICO ERP - Sistema de Gestión Jurídica

Sistema ERP para gestión de casos jurídicos desarrollado con Spring Boot (GraphQL) y Flask.

## 🚀 Inicio Rápido

### Prerrequisitos
- Docker Desktop instalado ([Ver guía de instalación](INSTALAR_DOCKER.md))
- O alternativamente:
  - Java 17+
  - Maven
  - Python 3.10+
  - PostgreSQL 15+

### Instalación con Docker (Recomendado)

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/escobarnina/-JURIDICO_erp_back.git
   cd JURIDICO_erp_back
   ```

2. **Levantar los servicios**
   ```bash
   docker compose up --build -d
   ```

3. **Inicializar la base de datos**
   - Conectar a PostgreSQL usando pgAdmin4 o TablePlus
   - **Host**: `localhost`
   - **Puerto**: `5432`
   - **Base de datos**: `sw2_erp`
   - **Usuario**: `admin`
   - **Contraseña**: `adminpass`
   - Ejecutar el script `init-db/init.sql`

4. **Acceder a la aplicación**
   - **Frontend**: http://127.0.0.1:3000
   - **Backend GraphQL**: http://localhost:8080/graphql
   - **GraphiQL (Interfaz)**: http://localhost:8080/graphiql

## 📋 Configuración de Base de Datos

- **POSTGRES_DB**: `sw2_erp`
- **POSTGRES_USER**: `admin`
- **POSTGRES_PASSWORD**: `adminpass`
- **PORTS**: `5432`

## 🛠️ Tecnologías

### Backend
- **Spring Boot 3.5.0** - Framework Java
- **GraphQL** - API GraphQL
- **Spring Data JPA** - Persistencia de datos
- **PostgreSQL** - Base de datos

### Frontend
- **Flask** - Framework Python
- **HTML/CSS** - Interfaz de usuario

### Infraestructura
- **Docker** - Contenedores
- **Docker Compose** - Orquestación

## 📁 Estructura del Proyecto

```
├── src/                    # Código fuente Spring Boot
│   ├── main/java/         # Código Java
│   │   ├── model/         # Entidades JPA
│   │   ├── repository/    # Repositorios
│   │   ├── service/       # Lógica de negocio
│   │   ├── resolver/      # Resolvers GraphQL
│   │   └── DTOs/          # Data Transfer Objects
│   └── resources/         # Recursos (application.properties, schema.graphqls)
├── erp_front/             # Frontend Flask
│   ├── entities/          # Módulos de entidades
│   └── templates/         # Plantillas HTML
├── init-db/               # Scripts de inicialización de BD
├── docker-compose.yml     # Configuración Docker Compose
└── Dockerfile             # Dockerfile del backend
```

## 📚 Documentación

- [Guía de Instalación Completa](GUIA_INSTALACION.md)
- [Guía de Instalación de Docker](INSTALAR_DOCKER.md)

## 🔧 Comandos Útiles

### Docker
```bash
# Levantar servicios
docker compose up --build -d

# Ver logs
docker compose logs

# Detener servicios
docker compose down

# Reiniciar un servicio
docker compose restart springboot-app
```

### Desarrollo Local (sin Docker)
```bash
# Backend
.\mvnw.cmd clean package -DskipTests
.\mvnw.cmd spring-boot:run

# Frontend
cd erp_front
pip install -r requirements.txt
python app.py
```

## 📝 Entidades del Sistema

- **Cliente** - Gestión de clientes
- **Abogado** - Gestión de abogados
- **Caso** - Gestión de casos jurídicos
- **Contrato** - Gestión de contratos

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de un trabajo académico.

## 👥 Autores

Grupo 8 - Proyecto JURIDICO ERP

## 🔗 Enlaces

- [Repositorio en GitHub](https://github.com/escobarnina/-JURIDICO_erp_back.git)
