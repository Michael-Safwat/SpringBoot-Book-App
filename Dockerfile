FROM openjdk:17-jdk-alpine
MAINTAINER michael.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]