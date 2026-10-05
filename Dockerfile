FROM amazoncorretto:17
COPY ./target/coursework.jar /tmp/coursework.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "coursework.jar"]