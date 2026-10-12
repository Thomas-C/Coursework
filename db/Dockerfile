FROM mysql:8.4

COPY world.sql /docker-entrypoint-initdb.d/world.sql

ENV MYSQL_ROOT_PASSWORD=example
ENV MYSQL_DATABASE=world
ENV MYSQL_USER=coursework
ENV MYSQL_PASSWORD=coursework