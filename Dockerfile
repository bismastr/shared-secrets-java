FROM gradle:8.5.0-jdk17-alpine AS build
WORKDIR /home/gradle/project

COPY gradle gradle
COPY gradlew build.gradle.kts settings.gradle.kts ./
RUN chmod +x gradlew
RUN ./gradlew --no-daemon build -x test

COPY . .
RUN ./gradlew --no-daemon clean bootJar -x test

FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app
USER spring:spring

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]