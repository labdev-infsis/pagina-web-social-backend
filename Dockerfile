# Usar una imagen base de Java
FROM ubuntu:latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]