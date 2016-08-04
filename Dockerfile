FROM 192.168.1.103:8999/mattersoft/java8:latest
VOLUME /tmp
ADD build/libs/likebackend.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]