FROM openjdk:17-oracle
COPY target/*.jar modern-warfare-marketplace.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","modern-warfare-marketplace.jar"]