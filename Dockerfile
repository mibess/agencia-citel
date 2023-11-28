FROM openjdk:17
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install
CMD ["java", "-jar", "citel-api.jar"]
