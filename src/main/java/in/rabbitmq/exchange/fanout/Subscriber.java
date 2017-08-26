package in.rabbitmq.exchange.fanout;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainer",
                    bindings = {
                                    @QueueBinding(value = @Queue("queueA"),
                                                    exchange = @Exchange(value = "spring-boot-rabbitmq-examples.fanout", type = ExchangeTypes.FANOUT)),
                                    @QueueBinding(value = @Queue("queueB"),
                                                    exchange = @Exchange(value = "spring-boot-rabbitmq-examples.fanout", type = ExchangeTypes.FANOUT))})
    public void subscribe(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

}
