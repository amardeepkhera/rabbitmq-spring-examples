package in.rabbitmq.exchange.direct;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainerFactory",
                    bindings = {
                                    @QueueBinding(value = @Queue("${queue.black}"),
                                                    key = "${routingKey.black}",
                                                    exchange = @Exchange(value = "${exchange.direct}", type = ExchangeTypes.DIRECT))
                    })
    public void subscribeToQueueBlack(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainerFactory",
                    bindings = {
                                    @QueueBinding(value = @Queue("${queue.white}"),
                                                    key = "${routingKey.white}",
                                                    exchange = @Exchange(value = "${exchange.direct}", type = ExchangeTypes.DIRECT))})
    public void subscribeToQueueWhite(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

}
