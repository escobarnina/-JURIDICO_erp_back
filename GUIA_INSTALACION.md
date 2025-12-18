# Guía de Instalación - Proyecto JURIDICO ERP

## Estado Actual del Sistema
- ✅ Java 17: Instalado
- ✅ Python 3.13.3: Instalado  
- ✅ Maven Wrapper: Disponible en el proyecto
- ❌ PostgreSQL: No instalado
- ❌ Docker: No instalado

## Opción 1: Instalación con Docker (Recomendada)

### Pasos:
1. **Instalar Docker Desktop para Windows**
   - Descargar desde: https://www.docker.com/products/docker-desktop
   - Instalar y reiniciar el sistema
   - Asegurarse de que Docker Desktop esté ejecutándose

2. **Levantar el proyecto**
   ```bash
   docker compose up --build -d
   ```

3. **Inicializar la base de datos**
   - Conectar a PostgreSQL usando pgAdmin4 o TablePlus
   - Base de datos: `sw2_erp`
   - Usuario: `admin`
   - Contraseña: `adminpass`
   - Puerto: `5432`
   - Ejecutar el script `init-db/init.sql`

4. **Acceder a la aplicación**
   - Frontend: http://127.0.0.1:3000
   - Backend GraphQL: http://localhost:8080/graphql

---

## Opción 2: Instalación Manual (Sin Docker)

### Requisitos Previos:
1. **Instalar PostgreSQL**
   - Descargar desde: https://www.postgresql.org/download/windows/
   - Durante la instalación, configurar:
     - Puerto: `5432`
     - Usuario: `admin`
     - Contraseña: `adminpass`
   - Crear la base de datos: `sw2_erp`

2. **Inicializar la base de datos**
   - Ejecutar el script `init-db/init.sql` en la base de datos `sw2_erp`

### Pasos para Levantar el Proyecto:

#### 1. Levantar el Backend (Spring Boot)
```bash
# Desde la raíz del proyecto
.\mvnw.cmd clean package -DskipTests
.\mvnw.cmd spring-boot:run
```
El backend estará disponible en: http://localhost:8080

#### 2. Levantar el Frontend (Flask)
```bash
# Navegar a la carpeta del frontend
cd erp_front

# Instalar dependencias
pip install -r requirements.txt

# Ejecutar la aplicación
python app.py
```
El frontend estará disponible en: http://127.0.0.1:3000

### Configuración de la Base de Datos
- **URL**: `jdbc:postgresql://localhost:5432/sw2_erp`
- **Usuario**: `admin`
- **Contraseña**: `adminpass`
- **Puerto**: `5432`

---

## Verificación
- ✅ Backend corriendo en puerto 8080
- ✅ Frontend corriendo en puerto 3000
- ✅ Base de datos PostgreSQL accesible
- ✅ Datos iniciales cargados desde `init.sql`

## Notas
- El backend usa GraphQL, puedes acceder a GraphiQL en: http://localhost:8080/graphiql
- Asegúrate de que los puertos 3000, 5432 y 8080 no estén en uso por otras aplicaciones

