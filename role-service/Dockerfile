FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY role-service/pom.xml service/pom.xml
COPY role-service/src service/src
COPY role-service/mvnw service/
COPY role-service/.mvn service/.mvn

RUN cd service && chmod +x mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/role-service-0.0.1-SNAPSHOT.jar ./role-service-0.0.1-SNAPSHOT.jar

EXPOSE 8004

CMD ["java", "-jar", "role-service-0.0.1-SNAPSHOT.jar"]