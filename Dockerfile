FROM openjdk:8-jdk-alpine

WORKDIR /app

ADD yxinmiracle_oj_backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8360

ENTRYPOINT ["java", "-Xmx512m","-jar","/app/yxinmiracle_oj_backend-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]