# BudgetAid
Demo app for home budget management.

## Available endpoints

    @PutMapping("/registry/recharge")
    @PostMapping("/registry/transfer")
    @GetMapping("/registry/balances")

## Tests coverage

Tests implemented covers all created methods,
there is used MockMvc framework and custom application-tests.properties file which uses in-memory h2 db with pre-loaded data for registries.

There is additional endpoint implemented for tests purposes:

    @GetMapping("/registry/{id}")

## Used database and configuration

For this application is used H2 db which could be downloaded from http://www.h2database.com/html/download.html .
It could be started as well with java command and installed with installer as standalone app. By default, it starts at
http://localhost:8082. Example configuration is described in application.properties file in resources.
## How to start an app

Java Version used : 11

First ensure you have running db instance and proper configuration in application.properties file.
Perform **mvn clean install** command and when application is built and tests passed you should be able to start it with
your IDE like IntelliJ Idea or with following command:
        
        java -jar target/app-0.0.1-SNAPSHOT.jar

After firs start schema.sql and data.sql should be used and proper data inserted into db. After first start you should change:
      
        spring.sql.init.mode=always
to :

        spring.sql.init.mode=never


so application won't try to insert table once again. You can also invoke scripts in h2 UI and have this flag deleted from properties file.

## Ideas for further developments and improvements
As I wrote also in code there can be easily implemented creating history of transactions.
We could create Transaction Entity which could persist date, registries and amount for an action.

This app for now only take care of registries without connecting them to a real person so implementing person entity and connection
between this person and registries could be very useful.

There should be also needed better exception handling with custom BudgetAid exceptions created,
as well as should be added logging. With Java, I'm big fan of using Aspects for logging so once written nice generic logs can be used in many similar places.
For example in methods starts and ends with proper results description.
