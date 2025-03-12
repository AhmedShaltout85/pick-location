FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/pick-location-0.0.1-SNAPSHOT.jar /app/pick-location.jar
EXPOSE 9999
CMD ["java","-jar","/app/pick-location.jar"]


#docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=Ah2708Ra1985" -e "MSSQL_PID=Evaluation" -p 1433:1433  --name sql-server --hostname sql-server -d mcr.microsoft.com/mssql/server:2017-latest

# docker run -d \
#     --name postgres-sql \
#     -p 5432:5432 \
#     -e POSTGRES_PASSWORD=password \
#     -e POSTGRES_USER=postgres \
#     -e POSTGRES_DB=api_courses \
#     -v /var/lib/postgresql/data \
#     postgres

#change the jdbc url to container name (postgres-sql) in this case =>(spring.datasource.url=jdbc:postgresql://postgres-sql:5432/api_courses)
# docker build -t ahraahab85/pick-location-api:1.0 .
# docker login -u ahraahab85 => Password:Ah27o8R@1985
# docker push -t ahraahab85/pick-location-api:1.0 => upload to docker hub
# docker network create spring-boot-net
# docker network connect spring-boot-net sql-server
# docker run --network spring-boot-net ahraahab85/pick-location-api:1.0
# docker run -p 9999:9999 --network spring-boot-net ahraahab85/pick-location-api:1.0
# docker run -d --name pick-location-rest-api -p 9999:9999 ahraahab85/pick-location-api:1.0

