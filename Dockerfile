FROM openjdk:18
ADD target/desafio-cooperativismo.jar desafio-cooperativismo.jar
ENTRYPOINT ["java", "-jar","desafio-cooperativismo.jar"]
EXPOSE 8080