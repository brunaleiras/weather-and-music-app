# weather-and-music-app
Welcome to our new platform, where the mood is a music party!

Projeto usando OpenJDK 11, Spring Boot, ReactJS

O aplicativo faz integração com Open Weather and Spotify

## Pré-requisitos

* Java >= 11
* Maven >= 3.1.1
* Docker >= 19.0

## Iniciando banco de dados

Para iniciar um container com as configurações necessarias usar o comando abaixo:

```
docker run --name postgresql-container -p 5432:5432 -e POSTGRES_DB=weather-and-music-app POSTGRES_PASSWORD=123 -d postgres
```
Necessário abrir o arquivo src/main/resources/application.properties e editar o seu iplocal na string de conexão com PostgresSQL

## Iniciando o projeto backend and frontend

Por questôes de praticidade esse projeto é integrado com o frontend (ReactJS) com backend (Java) no mesmo projeto usando o Maven com plugin:
https://github.com/eirslett/frontend-maven-plugin

Para compilar o projeto usar o comando na raiz do pom.xml:
```
mvn clean install -DskipTests
```
Isso vai compilar tanto o projeto backend quanto o frontend na mesma solução

## Iniciando o projeto

Para iniciar o projeto usar o comando:
```
java -jar target/weather-and-music-app-0.0.1-SNAPSHOT.jar
```










