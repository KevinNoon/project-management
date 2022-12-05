FROM ubuntu:latest

MAINTAINER Kevin Noon "knoon1959@gmail.com"

RUN apt-get update && apt-get install -y openjdk-11-jdk

ENV version=aws-pma-appliaction
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.cwuyxnyiurmf.us-east-1.rds.amazonaws.com:5432/postgres
ENV dbuser=postgres
ENV dbpass=password321

WORKDIR /usr/local/bin/

ADD target/pam-app.jar .

# CMD ["/bin/bash"]

ENTRYPOINT ["java", "-jar", "pam-app.jar"]