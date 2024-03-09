FROM openjdk:17
COPY target/Gauess-The-Number-0.0.1-SNAPSHOT.jar Gauess-The-Number-0.0.1-SNAPSHOT.jar
CMD java -jar Gauess-The-Number-0.0.1-SNAPSHOT.jar

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/Gauess-The-Number-0.0.1-SNAPSHOT.jar Gauess-The-Number.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Gauess-The-Number.jar"]