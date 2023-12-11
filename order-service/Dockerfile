FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY order-service/pom.xml service/pom.xml
COPY order-service/src service/src
COPY order-service/mvnw service/
COPY order-service/.mvn service/.mvn

RUN cd service && chmod +x ./mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/order-service-0.0.1-SNAPSHOT.jar ./order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]