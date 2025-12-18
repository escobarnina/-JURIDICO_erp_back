# Guía de Instalación de Docker Desktop para Windows

## Pasos para Instalar Docker Desktop

### 1. Descargar Docker Desktop
1. Ve a: https://www.docker.com/products/docker-desktop
2. Haz clic en "Download for Windows"
3. Se descargará el archivo `Docker Desktop Installer.exe`

### 2. Instalar Docker Desktop
1. Ejecuta el archivo `Docker Desktop Installer.exe` descargado
2. Acepta los términos y condiciones
3. Durante la instalación, asegúrate de marcar:
   - ✅ "Use WSL 2 instead of Hyper-V" (recomendado)
   - ✅ "Add shortcut to desktop"
4. Haz clic en "Ok" cuando termine la instalación

### 3. Reiniciar el Sistema
- **IMPORTANTE**: Reinicia tu computadora después de la instalación
- Docker Desktop requiere reiniciar para configurar correctamente WSL 2

### 4. Iniciar Docker Desktop
1. Después del reinicio, busca "Docker Desktop" en el menú de inicio
2. Ejecuta Docker Desktop
3. Acepta los términos de servicio
4. Espera a que Docker Desktop se inicie completamente (verás el ícono de Docker en la bandeja del sistema)

### 5. Verificar la Instalación
Abre PowerShell o CMD y ejecuta:
```bash
docker --version
docker compose version
```

Deberías ver las versiones instaladas.

### 6. Levantar el Proyecto
Una vez que Docker Desktop esté corriendo:

```bash
# Navegar al directorio del proyecto
cd "C:\Users\Hp\2do sw2\JURIDICO_erp_back-main"

# Levantar todos los servicios
docker compose up --build -d
```

Este comando:
- Construirá las imágenes de Docker
- Levantará PostgreSQL, Spring Boot y Flask
- Ejecutará todo en segundo plano (`-d`)

### 7. Verificar que los Contenedores Están Corriendo
```bash
docker compose ps
```

Deberías ver 3 contenedores corriendo:
- `erp-postgres` (PostgreSQL)
- `erp-springboot` (Backend Spring Boot)
- `erp-front` (Frontend Flask)

### 8. Inicializar la Base de Datos
1. Descarga e instala **pgAdmin4** o **TablePlus**:
   - pgAdmin4: https://www.pgadmin.org/download/
   - TablePlus: https://tableplus.com/

2. Conecta a PostgreSQL con:
   - **Host**: `localhost`
   - **Puerto**: `5432`
   - **Base de datos**: `sw2_erp`
   - **Usuario**: `admin`
   - **Contraseña**: `adminpass`

3. Ejecuta el script `init-db/init.sql` en la base de datos

### 9. Acceder a la Aplicación
- **Frontend**: http://127.0.0.1:3000
- **Backend GraphQL**: http://localhost:8080/graphql
- **GraphiQL (Interfaz)**: http://localhost:8080/graphiql

## Comandos Útiles de Docker

```bash
# Ver logs de los contenedores
docker compose logs

# Ver logs de un servicio específico
docker compose logs springboot-app
docker compose logs web
docker compose logs postgres

# Detener los contenedores
docker compose down

# Detener y eliminar volúmenes (¡cuidado! esto borra la BD)
docker compose down -v

# Reiniciar un servicio específico
docker compose restart springboot-app

# Reconstruir y levantar
docker compose up --build -d
```

## Solución de Problemas

### Docker Desktop no inicia
- Verifica que WSL 2 esté instalado: `wsl --status`
- Si no está instalado: `wsl --install`
- Reinicia la computadora

### Error de puertos ocupados
- Verifica que los puertos 3000, 5432 y 8080 no estén en uso
- Puedes cambiar los puertos en `docker-compose.yml` si es necesario

### Los contenedores se detienen
- Revisa los logs: `docker compose logs`
- Verifica que Docker Desktop esté corriendo

## Referencias
- Documentación oficial: https://docs.docker.com/desktop/install/windows-install/
- Repositorio del proyecto: https://github.com/escobarnina/-JURIDICO_erp_back.git

