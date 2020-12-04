# Retail Store

This is a brief example project for a **retail store** RESTful API, it is implemented using spring boot, spring data, hibernate, mysql, maven, unit tested using Junit, mockito.

## Pre-requisites
Create a database schema using MySQL.

Upon running the application, the tables will be created, and DMLS can be found under the repository's root in DMLs folder

## UML
UML is available here https://drive.google.com/file/d/1CaToMilFLWTt8Uoy6Ev4tTmNbacN6hRU/view?usp=sharing

## Usage

### Running the API:
The api is exported as a deployable fat jar using the Spring boot embedded tomcat. to run it all you have to do is:

 1. Build it using maven, run the following command from the api
    directory ( with unit tests ):
	> mvn clean install -DMYSQL_HOST={DMYSQL_HOST} -DPORT={PORT} -DUSERNAME={USERNAME} -DPASSWORD={PASSWORD} -DDB_NAME={DB_NAME}

	> replace the variables with the correct values, below are the default values
	> MYSQL_HOST=localhost
	> PORT=3306
	> USERNAME=root
	> PASSWORD=root
	> DB_NAME=retailstore

 - to skip unit tests, run the following
	> mvn clean install -DskipTests 

 2. Run it using java:
	> java -jar target/RetailStore.jar
 
 3. API path is `GET localhost:8080/bill/price/{billId}`
