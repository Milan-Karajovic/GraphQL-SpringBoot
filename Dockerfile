# How to build docker image: docker image build -t graphql-spring-boot .
FROM openjdk:17-jdk-alpine
COPY target/graphql-spring-boot-0.0.1-SNAPSHOT.jar graphql-spring-boot-0.0.1.jar
ENTRYPOINT ["java","-jar","/graphql-spring-boot-0.0.1.jar"]