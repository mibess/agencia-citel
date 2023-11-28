FROM openjdk:17
COPY . .
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]
