FROM amazoncorretto:17.0.10-al2023-headless
WORKDIR /app
COPY ./target/core-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]