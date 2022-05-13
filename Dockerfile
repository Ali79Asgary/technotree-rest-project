# syntax=docker/dockerfile:1

FROM openjdk:11-ea-11-jdk

RUN apt-get update -y && apt-get install -y curl

COPY main/build/libs/*.jar /opt/spring/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/opt/spring/app.jar"]