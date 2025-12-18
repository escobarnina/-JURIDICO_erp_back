@echo off
echo ========================================
echo Levantando Backend Spring Boot
echo ========================================
cd /d "%~dp0"
call mvnw.cmd clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo Error al compilar el proyecto
    pause
    exit /b 1
)
echo.
echo Compilacion exitosa. Iniciando servidor...
call mvnw.cmd spring-boot:run
pause

