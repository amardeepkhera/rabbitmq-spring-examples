# rabbitmq-spring-examples
This project contains examples for using Spring-Boot with RabbitMQ.
<br/>The examples are Spring-Boot implementation of various concepts and tutorial covered on [RabbitMQ Official Website](https://www.rabbitmq.com/getstarted.html)

## Structure
Each example is contained is a self contained package.<br>For instance ,example demonstrating messaging using topic exchange type is contained inside ```in.rabbitmq.exchange.topic```.<br/>Similarly example demonstrating messaging using async RPC is contained inside ```in.rabbitmq.async_rpc```.

### Build
```git clone ...```<br/>
``` mvn clean install```
### Run
You should have Java 8 and RabbitMQ 3.6.6 or above.RabbitMQ should be running on localhost:5672<br/>
You can run it as an executubale jar<br/> ```java -jar -Dspring.profiles.active=direct rabbitmq-spring-examples.jar```
### Spring Profiles
Each example can be run by specifying a profile , for instance to run an example demonstrating messaging using fanout exchange types , use following command.<br/>
```java -jar -Dspring.profiles.active=fanout rabbitmq-spring-examples.jar```<br/>
#### Available profiles:
- amqp_default
- fanout
- direct
- topic
- headers
- async_rpc
