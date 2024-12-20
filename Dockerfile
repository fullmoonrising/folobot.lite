FROM openjdk:19-jdk-alpine as base
WORKDIR /app
COPY /build/libs/folobot.lite-*.jar folobot.jar
ENTRYPOINT ["java","-jar","folobot.jar"]

