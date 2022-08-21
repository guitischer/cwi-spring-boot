FROM openjdk:18
ADD target/cooperativismo-api.jar cooperativismo-api.jar
ENTRYPOINT ["java", "-jar","cooperativismo-api.jar"]
EXPOSE 8080