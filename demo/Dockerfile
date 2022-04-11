FROM openjdk:19-jdk-alpine3.15
COPY ./target/*.jar /Documents/mydocker/demo.jar
WORKDIR /Documents/mydocker
RUN sh -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","demo.jar"]