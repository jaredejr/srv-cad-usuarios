FROM openjdk:21-jdk-slim
LABEL authors="jaredejr"
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]