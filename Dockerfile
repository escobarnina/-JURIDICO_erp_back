# Archivo: Dockerfile

# Etapa 1: construir el JAR con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar el JAR
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/ERP-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]