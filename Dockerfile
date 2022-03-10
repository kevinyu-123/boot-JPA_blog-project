FROM adoptopenjdk/openjdk8

ARG WAR_FILE_PATH=target/blog-0.0.1-SNAPSHOT.war

EXPOSE 8000

COPY ${WAR_FILE_PATH} blog.war

ENTRYPOINT ["java","-jar","blog.war"]