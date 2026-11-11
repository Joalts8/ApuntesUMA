# ==============================================================================
# Dockerfile de Referencia (Java 21 + Spring Boot)
# ==============================================================================
# Este Dockerfile sirve como plantilla de ejemplo para empaquetar una aplicación
# Spring Boot en una imagen optimizada y segura. Si tu proyecto utiliza Node.js,
# Python, Go u otra tecnología, sustituye esta imagen base y los comandos de ejecución.
# ==============================================================================

# Imagen base oficial y ligera con JRE 21 sobre Alpine Linux
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo en el contenedor
WORKDIR /app

# Metadatos informativos
LABEL maintainer="Equipo de Desarrollo" \
      description="Imagen contenerizada de aplicación Spring Boot"

# Argumento para especificar el archivo JAR (generado en target/ o build/libs/)
ARG JAR_FILE=target/*.jar

# Copia el binario empaquetado al contenedor
COPY ${JAR_FILE} app.jar

# Exponer el puerto por defecto de Spring Boot (8080)
EXPOSE 8080

# Buenas prácticas de seguridad: ejecutar como usuario sin privilegios (no-root)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Parámetros óptimos de ejecución de la JVM en contenedores
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
