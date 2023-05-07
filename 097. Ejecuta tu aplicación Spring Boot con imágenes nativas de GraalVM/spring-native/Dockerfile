FROM eclipse-temurin:17-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# docker build -t springapp:1.0 .
# docker run --name springapp -p 8080:8080 springapp:1.0