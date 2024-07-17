FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY ./target/review-service-0.0.1-SNAPSHOT.jar review-service.jar
ENTRYPOINT ["java","-jar","/review-service.jar"]