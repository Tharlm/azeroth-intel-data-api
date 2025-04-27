docker run -itd -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -v pg_data:/var/lib/postgresql/data -v pg_backups:/dumps --name postgre-server-backup postgres:latest

Les Objets APIs vont servir uniquement pour les retours d'objets.
Dans ce cas on pourra les annoter avec les validations adéquates

Par ailleurs revoir la structure des CRs, mettre une clé primaire sur la région et l'ID
