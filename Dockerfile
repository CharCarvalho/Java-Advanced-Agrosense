FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    bash \
    && apt-get clean

ENV SPRING_DATASOURCE_URL=jdbc:sqlserver://agrosense.database.windows.net:1433;database=AgroSense
ENV SPRING_DATASOURCE_USERNAME=AgrosenseDatabase@sqlchallengeagrosense@agrosense
ENV SPRING_DATASOURCE_PASSWORD=@groSense-2024

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:resolve

COPY src ./src

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "/app/target/Java-Advanced-Challenge-0.0.1-SNAPSHOT.jar"]