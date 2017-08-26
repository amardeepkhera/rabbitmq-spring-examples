package in.rabbitmq.direct;

import in.rabbitmq.SampleRequestMessage;
import in.rabbitmq.SampleResponseMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    @RabbitListener(queues = {"requestQueueForConvertSendAndReceive"}, containerFactory = "simpleMessageListenerContainer")
    public void subscribeToConvertSendAndReceiveRequests(@Payload SampleRequestMessage sampleRequestMessage, Message message) {

        System.out.println("Received by subscriber for subscribeToConvertSendAndReceiveRequests:" + this.toString() + sampleRequestMessage);
        SampleResponseMessage sampleResponseMessage = new SampleResponseMessage(sampleRequestMessage.getMessage());
        rabbitTemplate.convertAndSend(message.getMessageProperties().getReplyTo(), sampleResponseMessage);
    }

    @RabbitHandler
    @RabbitListener(queues = {"requestQueueForConvertAndSend"}, containerFactory = "simpleMessageListenerContainer")
    public void subscribeToConvertAndSendRequests(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received by subscriber for subscribeToConvertAndSendRequests:" + this.toString() + sampleRequestMessage);
    }
}
