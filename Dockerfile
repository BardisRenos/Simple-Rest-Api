FROM openjdk:11.0.15-slim
LABEL maintainer="Renos Bardis"
EXPOSE 8081
ADD target/spring-boot-application-docker.jar spring-boot-application-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-application-docker.jar"]
