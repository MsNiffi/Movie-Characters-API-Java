FROM gradle:jdk17 AS gradle
WORKDIR /app
COPY . .
RUN gradle bootJar

FROM openjdk:17 as runtime
WORKDIR /app

ENV SPRING_PROFILE "prod"
ENV DDL_AUTO "none"
ENV INIT_MODE "always"
ENV SHOW_JPA_SQL "false"

COPY --from=gradle /app/build/libs/*.jar /app/app.jar
RUN chown -R 1000:1000 /app
USER 1000:1000
ENTRYPOINT ["java", "-jar", "app.jar"]