FROM openjdk:11

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    KAFESH_SLEEP=0 \
    JAVA_OPTS=""

# add source

WORKDIR /app
ADD . /code/
COPY . /code/

RUN cd /code/ && \
    rm -Rf /root/.m2/*.* && \
    ./mvnw clean package -DskipTests && \
    mv /code/target/*.war /app/app.war
COPY target/*.jar /app/app.jar

VOLUME /tmp

EXPOSE 8080:8080
EXPOSE 8081:8888


CMD echo "The application will start in ${KAFESH_SLEEP}s..." && \
    sleep ${KAFESH_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar

