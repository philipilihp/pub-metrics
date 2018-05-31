# pub-metrics

Collecting metrics in a JAX RS application with dropwizard-metrics and reporting them to Graphite.

## Setup Graphite 
Start Graphite Setup via docker (http://graphite.readthedocs.io/en/latest/install.html)

`docker run -d\  
 --name graphite\  
 --restart=always\      
 -p 80:80\
 -p 8081:8080\  
 -p 2003-2004:2003-2004\
 -p 2023-2024:2023-2024\
 -p 8125:8125/udp\
 -p 8126:8126\
 graphiteapp/graphite-statsd`

## Run Pub Application
Run with the lates version of Payara Micro (https://www.payara.fish/downloads)

`java -jar payara-micro-5.181.jar --deploy pub-metrics-1.0-SNAPSHOT.war`

List all available beers  
http://localhost:8080/pub/beers

View metrics in application
http://localhost:8080/metrics

## Collecting metrics

Order some beers  
http://localhost:8080/pub/beers/KASTEEL  
http://localhost:8080/pub/beers/GRIMBERGEN  
http://localhost:8080/pub/beers/LEFFE  

View metrics on Dashboard  
http://192.168.99.100:8081/dashboard

