FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Copy maven wrapper and pom.xml
COPY invernadero_inteligente_backend/.mvn/ .mvn/
COPY invernadero_inteligente_backend/mvnw .
COPY invernadero_inteligente_backend/pom.xml .

# Make mvnw executable
RUN chmod +x ./mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code and build
COPY invernadero_inteligente_backend/src ./src
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
