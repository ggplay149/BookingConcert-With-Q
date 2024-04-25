FROM openjdk:17-alpine

WORKDIR /app

ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

#image 생성
#docker build -t concert-dev .

#container 실행
#docker run --rm --name concert-img -p 8080:8080 concert-dev



