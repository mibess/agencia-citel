# ETAPA 1: Build com o JDK (versão alpine)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package

# ETAPA 2: Imagem final com o JRE (versão alpine)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/agencia-citel.jar
EXPOSE 8080
CMD ["java", "-jar", "agencia-citel.jar"]
