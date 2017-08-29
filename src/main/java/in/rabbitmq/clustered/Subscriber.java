package in.rabbitmq.clustered;

import in.rabbitmq.SampleRequestMessage;
import in.rabbitmq.SampleResponseMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainerFactory", queues = "${queue.request}")
    public SampleResponseMessage subscribeToRequestQueue(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + message);

        return new SampleResponseMessage(sampleRequestMessage.getMessage());
    }

}
