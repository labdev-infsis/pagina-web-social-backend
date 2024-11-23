# Usar una imagen base de Java
FROM ubuntu_latest AS build
RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080
COPY --from=build /build/libs/*.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]