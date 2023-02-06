FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /app
COPY pom.xml
COPY src ./src/

RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk17:latest

COPY --from=builder /app/target/redis-*.jar /redis.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-Dserver.port=8093","-jar","/redis.jar"]
