FROM openjdk:11
LABEL maintainer="vinuta"
EXPOSE 8093
ADD target/zuulapigateway-0.0.1-SNAPSHOT.jar zuulapigateway.jar
ENTRYPOINT ["java","-jar","/zuulapigateway.jar"]