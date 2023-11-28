FROM openjdk:latest
COPY . .
RUN ./mvnw clean install
CMD ["java", "-jar", "citel-api.jar"]
