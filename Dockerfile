FROM openjdk:17
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install
ENTRYPOINT ["java", "-jar", "target/*.jar"]
