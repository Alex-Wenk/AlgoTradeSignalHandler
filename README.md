# Trading Algo Signal Handler
Simple Spring Boot HTTP trading algo signal handler. 

1. Receives signals at ``/signal/{signalId}``. 
2. Retrieves the algo configuration for that signal from an in memory cache backed by a mySql database.
3. Configures a library provided Algo according to the signal specification.
4. Executes the Algo


## Dependencies
* Docker
* Java 17

## Instructions
* Run ``docker-compose up -d`` in root directory to launch the database server
* Run ``gradlew build`` to build the bootJar
* Run ``java -jar build/libs/AlgoTradeSignalHandler-1.0.0.jar``

## Writeup
Please read ``Assignment Writeup.docx`` for a more detailed write up.
