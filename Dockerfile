FROM openjdk:latest
WORKDIR /app
COPY target/*.jar /app/citel-api.jar
EXPOSE 8080
CMD ["java", "-jar", "citel-api.jar"]