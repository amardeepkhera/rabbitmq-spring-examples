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
                                    @QueueBinding(value = @Queue("${queue.A}"),
                                                    exchange = @Exchange(value = "${exchange.fanout}", type = ExchangeTypes.FANOUT)),
                                    @QueueBinding(value = @Queue("${queue.B}"),
                                                    exchange = @Exchange(value = "${exchange.fanout}", type = ExchangeTypes.FANOUT))})
    public void subscribe(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

}
