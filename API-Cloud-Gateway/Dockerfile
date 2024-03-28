FROM openjdk:18
MAINTAINER Joao Louro
COPY target/API-Cloud-Gateway-0.0.1-SNAPSHOT.jar api-cloud-gateway.jar
ENTRYPOINT exec java $JAVA_OPTS -jar api-cloud-gateway.jar