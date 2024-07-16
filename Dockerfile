FROM openjdk:17
ADD target/forward-payment.jar   /forward-payment.jar
ENTRYPOINT ["java","-jar","forward-payment.jar"]