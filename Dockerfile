FROM openjdk:17-alpine

COPY build/libs/*.jar app.jar

# 데이터베이스 초기화를 위한 SQL 파일을 복사합니다.
COPY ./src/main/resources/data.sql data.sql

# 8080 포트를 노출합니다.
EXPOSE 8080

# 애플리케이션을 실행합니다.
ENTRYPOINT ["java", "-jar", "/app.jar"]

# 데이터베이스 초기화를 위한 SQL 파일을 사용하여 데이터베이스 초기화를 수행합니다.
CMD ["java", "-jar", "/app.jar", "--spring.datasource.initialization-mode=always", "--spring.datasource.data=classpath:data.sql"]

#image 생성
#docker build -t concert-img .

#container 실행
#docker run --rm --name concert -p 8080:8080 concert-img
#-e PROFILE=dev
