# use maven image for build
FROM maven:3.9.3-eclipse-temurin-20 as build

# set work directory for build
WORKDIR /app

# copy maven configuration + source code into container
COPY pom.xml .
COPY src ./src

# build project in container
# add -DskipTests for faster build
RUN mvn clean package 

# build openjdk runtime image
FROM openjdk:21-jdk-slim

# set work directory for runtime 
WORKDIR /appr

# copy application from build stage into runtime container
COPY --from=build /app/target/*.jar app.jar

# backend port
EXPOSE 8091

# run backend application
ENTRYPOINT ["java", "-jar", "app.jar"]