FROM azul/zulu-openjdk-centos:11.0.5
VOLUME /tmp
EXPOSE 8080
COPY target/demo-es-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
