# Étape 1 : Build avec Maven et Corretto 21
FROM maven:3.9.4-amazoncorretto-21 as build
WORKDIR /app

COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Exécution avec Corretto 21 JRE
FROM amazoncorretto:21
WORKDIR /app

COPY --from=build /app/target/azeroth-intel-data-api-0.0.1-SNAPSHOT.war app.jar

# Profil par défaut (modifiable via variable d’environnement)
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
