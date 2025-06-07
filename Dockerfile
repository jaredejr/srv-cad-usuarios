FROM openjdk:21-jdk-slim
LABEL authors="jaredejr"
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]