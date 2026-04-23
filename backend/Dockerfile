# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml và download dependencies trước để tận dụng Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy mã nguồn và build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy file jar từ Stage 1
COPY --from=build /app/target/*.jar app.jar

# Render sẽ tự động gán biến PORT, Spring Boot cần listen trên port này
EXPOSE 8080

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
