FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY order-service/pom.xml service/pom.xml
COPY order-service/src service/src
COPY order-service/mvnw service/
COPY order-service/.mvn service/.mvn
COPY library/pom.xml service/lib/pom.xml
COPY library/src service/lib/src
COPY library/mvnw service/lib/
COPY library/.mvn service/library/.mvn

RUN cd service/lib && chmod +x ./mvnw && ./mvnw clean install -DskipTests
RUN cd service && chmod +x ./mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/order-service-0.0.1-SNAPSHOT.jar ./order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]