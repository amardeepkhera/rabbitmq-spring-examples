package in.rabbitmq.exchange.headers;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainer",
                    bindings = {
                                    @QueueBinding(value = @Queue("indianAndMale"),
                                                    arguments = {
                                                                    @Argument(name = "nationality", value = "indian"),
                                                                    @Argument(name = "gender", value = "male")},
                                                    exchange = @Exchange(value = "spring-boot-rabbitmq-examples.headers", type = ExchangeTypes.HEADERS)),
                    })
    public void subscribeToMessagesFromIndianMale(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainer",
                    bindings = {
                                    @QueueBinding(value = @Queue("americanAndFemale"),
                                                    arguments = {
                                                                    @Argument(name = "nationality", value = "american"),
                                                                    @Argument(name = "gender", value = "female"),
                                                                    @Argument(name = "x-match", value = "any")},
                                                    exchange = @Exchange(value = "spring-boot-rabbitmq-examples.headers", type = ExchangeTypes.HEADERS))
                    })
    public void subscribeToMessagesFromAmericanFemale(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }
}
