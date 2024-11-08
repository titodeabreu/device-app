FROM gradle:8-jdk21-alpine as builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build test

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/device-management*.jar /app/device-management.jar
CMD ["java", "-jar", "device-management.jar"]