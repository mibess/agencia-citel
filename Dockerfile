FROM openjdk:17
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install
WORKDIR /app
COPY *.jar /app/citel-api.jar
CMD ["java", "-jar", "citel-api.jar"]
