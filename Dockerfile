FROM gradle AS BUILD
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    
# actual container
FROM openjdk:17-oracle
ENV ARTIFACT_NAME=app.jar
ENV APP=/app
ENV GRADLE=/usr/app
    
WORKDIR $APP
COPY --from=BUILD $GRADLE/build/libs/*.jar $APP/$ARTIFACT_NAME
    
EXPOSE 9090
ENTRYPOINT exec java -jar ${ARTIFACT_NAME} -Dspring.profiles.active=dev