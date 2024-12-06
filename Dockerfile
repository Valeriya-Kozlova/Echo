FROM eclipse-temurin:21-jre-alpine
ADD opentelemetry-javaagent.jar /opt/opentelemetry-javaagent.jar
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} Echo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-javaagent:/opt/opentelemetry-javaagent.jar", "-jar", "-Dotel.service.name=echo", "-Dotel.traces.exporter=logging", "-Dotel.logs.exporter=logging", "-Dotel.metrics.exporter=logging", "/Echo.jar"]