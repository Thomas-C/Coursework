FROM amazoncorretto:17
COPY ./target/coursework-jar-with-dependencies.jar /tmp/
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "coursework-jar-with-dependencies.jar"]