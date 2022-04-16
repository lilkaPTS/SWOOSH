FROM openjdk:11-jdk
ADD target/SWOOSH-1.0-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
