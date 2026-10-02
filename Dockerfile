FROM amazoncorretto:17
COPY ./target/coursework-jar-with-dependencies.jar  /tmp/coursework.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "coursework.jar"]