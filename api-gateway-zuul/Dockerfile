FROM openjdk:11-jdk-slim
MAINTAINER Taner Diler <taner.diler@gmail.com>
ADD target/api-gateway-zuul-0.0.1-SNAPSHOT.jar api-gateway-zuul-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/api-gateway-zuul-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080