FROM openjdk:19
ADD target/taxi-bot.jar app.jar
VOLUME /simple.app
EXPOSE 8891
ENTRYPOINT ["java", "-jar", "/app.jar"]



