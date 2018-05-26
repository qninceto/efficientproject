## Efficientproject.com
Spring Boot 2 based resource server
Spring Boot 2 with Spring Security and OAuth2 base Authorization server

## Build the Project
```
mvn clean install
```

## Projects/Modules
This project contains a number of modules, but here are the main ones you should focus on and run: 
- `authetification-server` - the Authorization Server (port = 8081)
- `efficientproject` - the Resource Server (port = 8080)

## Run the Modules
You can run any sub-module using command line: 
```
mvn spring-boot:run
```
authetification-server is reached on `http://localhost:8081/authentification-server` 
the subdomain can be changed from the application.properties