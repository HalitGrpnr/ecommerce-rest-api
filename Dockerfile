FROM java:8
VOLUME /tmp
ADD target/api-0.0.1-SNAPSHOT.jar api.jar
RUN bash -c 'touch /api.jar'
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api.jar"]