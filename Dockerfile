FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
COPY . .
RUN ./mvnw clean install
CMD ["java", "-jar", "citel-api.jar"]
