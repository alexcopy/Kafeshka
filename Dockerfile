FROM openjdk:20

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    KAFESH_SLEEP=0 \
    JAVA_OPTS=""

# add source

WORKDIR /app
ADD . /code/

RUN cd /code/ && \
    ./mvnw clean package  -DskipTests && \
    mv /code/target/*.jar /app.jar && \
    rm -Rf /code  /tmp && \
    rm -Rf /root/.m2/


VOLUME /tmp

EXPOSE 8080:8080
EXPOSE 8081:8888


CMD echo "The application will start in ${KAFESH_SLEEP}s..." && \
    sleep ${KAFESH_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar

