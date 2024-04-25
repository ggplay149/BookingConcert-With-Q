FROM openjdk:17-alpine
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app.jar"]

#image 생성
#docker build -t concert-img .

#container 실행
#docker run --rm --name concert-img -p 8080:8080 concert-img
#-e PROFILE=dev
