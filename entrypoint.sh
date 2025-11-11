#!/bin/sh

export SPRING_DATASOURCE_USERNAME=$(cat /run/secrets/postgres_user)
export SPRING_DATASOURCE_PASSWORD=$(cat /run/secrets/postgres_password)
export SPRING_DATASOURCE_URL=jdbc:postgresql://tharlm-postgres:5432/$(cat /run/secrets/postgres_db)

exec java -jar /app/app.jar
