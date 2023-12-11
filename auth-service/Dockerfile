FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY auth-service/pom.xml service/pom.xml
COPY auth-service/src service/src
COPY auth-service/mvnw service/
COPY auth-service/.mvn service/.mvn

RUN cd service && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/auth-service-0.0.1-SNAPSHOT.jar ./auth-service-0.0.1-SNAPSHOT.jar

EXPOSE 8003

CMD ["java", "-jar", "auth-service-0.0.1-SNAPSHOT.jar"]