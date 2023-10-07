# This project compares the received transaction amount field in Kafka with the transaction in Redis.
## Initial conditions:

1) Redis with stored transaction:
  - key - transaction id
  - value - metadata with json string

Commands to populate Redis:
- `set 123 '{"amount":100.05,"metadata":{"originatorId":1,"destinationId":2}}'`
- `set 124 '{"amount":150.75,"metadata":{"originatorId":10,"destinationId":20}}'`
- `set 125 '{"amount":1010.00,"metadata":{"originatorId":20,"destinationId":30}}'`
- `set 126 '{"amount":15.5,"metadata":{"originatorId":30,"destinationId":40}}'`

2) You receive transactions from Kafka topic. Example of a message in Kafka:
    ```json
    [
        {
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 123,
          "PAMOUNT": 94.7,
          "PDATA": 20160101120000
        },
        {   
          "PID": 124,
          "PAMOUNT": 150.75,
          "PDATA": 20160101120001
        },
        {   
          "PID": 125,
          "PAMOUNT": 1020.2,
          "PDATA": 20160101120002
        },
        {   
          "PID": 126,
          "PAMOUNT": 15.5,
          "PDATA": 20160101120003
        },
        {   
          "PID": 127,
          "PAMOUNT": 120.74,
          "PDATA": 20160101120004
        }
    ]
    ```

## conditions:
You have to compare the `amount` field of the received transaction against the transaction in Redis. You should query Redis by transaction id.
Comparison has to be sent to another kafka topic, the message should contain enough information to understand the result.

## Requirements:
Take into account, that in future you:
* Might receive transactions not only from Kafka topic
* Might have to support several result message formats
* Might need to send check results via different channels, e.g. make an API call

## Expected result:
* Source code of Spring Boot 3 application, using Java 17+
* Maven should be able to package code to jar

## Config Appllication Reqirement
before run application please config your kafka host and port in application.yml file. "bootstrap-servers : Host:Port"
and config your Redis database in application.properties file . "spring.data.redis.host=host and spring.data.redis.port=port"
you can change application server port in application.yml file. "server:port: Port"
for send check results via different channels pleae set API Url in application.yml file. "API:url: url"

## Run Application

### For run in Linux OS terminal go 
 ./mvnw spring-boot:run

### For run in Windows OS command line
 mvnw.cmd spring-boot:run
 
 ### Application url
```
	Application Name:   'Spring-boot Comparison transactions Kafka Redis' 
	Application URL: 	http://localhost:8099
	Swagger UI URL: 	http://localhost:8099/swagger-ui/index.html
```

## important curls
### registering transactions in the Kafka topic.
insert transaction to kafka

```bash
 curl --location 'http://localhost:8099/api/v1/kafka/register' \
--header 'Content-Type: application/json' \
--data '[
    {
      "pID": 123,
      "pAMOUNT": 94.7,
      "pDATA": 20160101120000
    },
    {   
      "pID": 123,
      "pAMOUNT": 94.7,
      "pDATA": 20160101120000
    },
    {   
      "pID": 124,
      "pAMOUNT": 150.75,
      "pDATA": 20160101120001
    },
    {   
      "pID": 125,
      "pAMOUNT": 1020.2,
      "pDATA": 20160101120002
    },
    {   
      "pID": 126,
      "pAMOUNT": 15.5,
      "pDATA": 20160101120003
    },
    {   
      "pID": 127,
      "pAMOUNT": 120.74,
      "pDATA": 20160101120004
    }
]'
```
### inserting and retrieving information from the Redis database.
insert transaction to redis

```bash
 curl --location 'http://localhost:8099/api/v1/transaction' \
--header 'Content-Type: application/json' \
--data '{   "id":123,
    "amount": 100.05,
    "metadata": {
        "originatorId": 1,
        "destinationId": 2
    }
}'

```
get transaction from redis

```bash
 curl --location 'http://localhost:8099/api/v1/transaction/123' \
--data ''
```


## Used technologies
Source code of Spring Boot 3 application, using Java 17+ and Maven.