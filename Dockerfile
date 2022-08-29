FROM openjdk:8
EXPOSE 9595
ADD target/tweet-app.jar tweet-app.jar
ENTRYPOINT ["java", "-jar", "/tweet-app.jar"]