FROM openjdk:11-jdk-slim
MAINTAINER Taner Diler <taner.diler@gmail.com>
ADD target/discovery-service-0.0.1-SNAPSHOT.jar discovery-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/discovery-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8761