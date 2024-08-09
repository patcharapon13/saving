FROM openjdk:17-oracle

RUN mkdir /usr/src/app

COPY ./build/libs/saving.jar /usr/src/app/libs/saving.jar
COPY ./docker-entrypoint.sh /usr/src/app/docker-entrypoint.sh
RUN chmod +x /usr/src/app/docker-entrypoint.sh
WORKDIR /usr/src/app

EXPOSE 20120
ENTRYPOINT ["/usr/src/app/docker-entrypoint.sh"]
