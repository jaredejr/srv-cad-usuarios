FROM openjdk:21-jdk-slim
LABEL authors="jaredejr"
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*
RUN mkdir /app
WORKDIR /app

COPY target/*.jar /app/app.jar
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
EXPOSE 8080 5005
CMD ["java","-jar","/app/app.jar"]