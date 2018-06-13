# spring-module
Spring MVC, Java

### Requirements
Java 8 ( tested with `java version "1.8.0_172` )

### Install dependences 
`gradle`

### Running Spring Boot
`gradle bootRun`

### Auto Reloading (Another Terminal)
`gradle -t classes`

### Windows Environment
Install Scoop
`iex (new-object net.webclient).downloadstring('https://get.scoop.sh')`

Install Gradle
`scoop install gradle`

Install Java
`scoop install oraclejdk-lts`

Install PostgreSQL
`scoop install postgresql`


### Setup Postgre SQL

Create User Role PDA with no password
`>psql -U postgres -c "CREATE ROLE pda LOGIN SUPERUSER INHERIT CREATEDB CREATEROLE;" postgres`

Create Database with Role PDA 
`>psql -U postgres -c "CREATE DATABASE pda OWNER pda;`

Login to SQL 
`>psql -U pda;`

Run the query from the file: 
`ROOT_PROJECT/src/main/resources/db/migration/V1__Create_post_table.sql`