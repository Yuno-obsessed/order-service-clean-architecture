FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY mail-service/pom.xml service/pom.xml
COPY mail-service/src service/src
COPY mail-service/mvnw service/
COPY mail-service/.mvn service/.mvn

RUN cd service && chmod +x ./mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/mail-service-0.0.1-SNAPSHOT.jar ./mail-service-0.0.1-SNAPSHOT.jar

EXPOSE 8081

CMD ["java", "-jar", "mail-service-0.0.1-SNAPSHOT.jar"]