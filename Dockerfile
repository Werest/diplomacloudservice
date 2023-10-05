FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/ROOT.jar /app/ROOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/ROOT.jar"]