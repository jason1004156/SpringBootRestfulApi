FROM openjdk:19-jdk
COPY ./target/*.jar /Documents/mydocker/demo.jar
WORKDIR /Documents/mydocker
RUN sh -c 'touch demo.jar'
ENTRYPOINT ["java","-jar","demo.jar"]