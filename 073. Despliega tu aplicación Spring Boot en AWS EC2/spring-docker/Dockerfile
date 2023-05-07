FROM eclipse-temurin:18-jre-jammy
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# docker build -t springbootapp:1.0 .
# docker image list
# docker run -p 8080:8080 --name springapp -d -t springbootapp:1.0
# docker stop springapp
# docker start springapp
# docker stats
# docker logs -f springapp
# docker rm springapp
# docker login
# docker tag springbootapp:1.0 alansastre/springbootapp:1.0
# docker push alansastre/springbootapp:1.0

# Desde un servidor:
# docker pull alansastre/springbootapp:1.0
# docker run -p 8080:8080 --name springapp -d -t alansastre/springbootapp:1.0
# docker rmi alansastre/springbootapp:1.0