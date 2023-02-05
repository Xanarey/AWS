FROM openjdk:17
ADD target/AWS-0.0.1-SNAPSHOT.jar AWS-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/AWS-0.0.1-SNAPSHOT.jar"]