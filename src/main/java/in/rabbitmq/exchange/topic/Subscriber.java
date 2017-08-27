package in.rabbitmq.exchange.topic;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;

public class Subscriber {

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainer",
                    bindings = {
                                    @QueueBinding(value = @Queue("${queue.countriesInNorthernHemisphere}"),
                                                    key = "${binding.northern_hemisphere}",
                                                    exchange = @Exchange(value = "${exchange.topic}", type = ExchangeTypes.TOPIC)),
                                    @QueueBinding(value = @Queue("${queue.countriesInNorthernHemisphere}"),
                                                    key = "${binding.both}",
                                                    exchange = @Exchange(value = "${exchange.topic}", type = ExchangeTypes.TOPIC))})
    public void subscribeToCountriesInNorthernHemisphereQueue(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }

    @RabbitHandler
    @RabbitListener(containerFactory = "simpleMessageListenerContainer",
                    bindings = {
                                    @QueueBinding(value = @Queue("${queue.countriesInSouthernHemisphere}"),
                                                    key = "${binding.southern_hemisphere}",
                                                    exchange = @Exchange(value = "${exchange.topic}", type = ExchangeTypes.TOPIC)),
                                    @QueueBinding(value = @Queue("${queue.countriesInSouthernHemisphere}"),
                                                    key = "${binding.both}",
                                                    exchange = @Exchange(value = "${exchange.topic}", type = ExchangeTypes.TOPIC))})
    public void subscribeToCountriesInSouthernHemisphereQueue(@Payload SampleRequestMessage sampleRequestMessage, Message message) {
        System.out.println("Received message :" + sampleRequestMessage + " from " + message.getMessageProperties().getConsumerQueue());
    }
}
