FROM openjdk:11-jdk
ADD SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
