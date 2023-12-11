FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY user-service/pom.xml service/pom.xml
COPY user-service/src service/src
COPY user-service/mvnw service/
COPY user-service/.mvn service/.mvn

RUN cd service && chmod +x mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/user-service-0.0.1-SNAPSHOT.jar ./user-service-0.0.1-SNAPSHOT.jar

EXPOSE 8002

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]