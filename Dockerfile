FROM amazoncorretto:25
COPY ./target/coursework-jar-with-dependencies.jar /tmp/
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "coursework-jar-with-dependencies.jar"]