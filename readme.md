docker run -itd -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -v pg_data:/var/lib/postgresql/data -v pg_backups:/dumps --name postgre-server-backup postgres:latest

Les Objets APIs vont servir uniquement pour les retours d'objets.
Dans ce cas on pourra les annoter avec les validations adéquates

Par ailleurs revoir la structure des CRs, mettre une clé primaire sur la région et l'ID

export VERSION=0.1.0

docker build -t azeroth-intel-data-api:0.1.0 .
docker tag azeroth-intel-data-api:0.1.0 azeroth-intel-data-api:latest
docker push azeroth-intel-data-api:0.1.0
docker push azeroth-intel-data-api:latest

docker build -t registry.gitlab.com/tharlm62/azeroth/azeroth-intel-data-api:0.1.1 .
docker tag registry.gitlab.com/tharlm62/azeroth/azeroth-intel-data-api:0.1.1
registry.gitlab.com/tharlm62/azeroth/azeroth-intel-data-api:latest
docker push registry.gitlab.com/tharlm62/azeroth/azeroth-intel-data-api
docker push registry.gitlab.com/tharlm62/azeroth/azeroth-intel-data-api:0.1.0
