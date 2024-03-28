FROM openjdk:18
MAINTAINER Joao Louro
COPY target/Naming-server-0.0.1-SNAPSHOT.jar Naming-server.jar
ENTRYPOINT exec java $JAVA_OPTS -jar Naming-server.jar