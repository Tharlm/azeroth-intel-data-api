docker run -itd -e POSTGRES_USER=myuser -e POSTGRES_PASSWORD=mypassword -p 5432:5432 -v pg_data:/var/lib/postgresql/data -v pg_backups:/dumps --name postgre-server-backup postgres:latest
