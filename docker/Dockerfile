FROM openjdk

COPY target/*.jar /app.jar

EXPOSE 80

# wait for mysql
ENTRYPOINT sleep 1 && java -jar /app.jar
