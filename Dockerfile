FROM openjdk:17
WORKDIR /app
RUN chmod +x mvn
RUN mvn clean package
COPY target/*.jar /app/citel-api.jar
EXPOSE 8080
CMD ["java", "-jar", "citel-api.jar"]
