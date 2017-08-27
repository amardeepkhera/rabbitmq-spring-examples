package in.rabbitmq.exchange.topic;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;


public class Publisher {

    @Value("${exchange.topic}")
    private String topicExchange;
    @Value("${routingKey.denmark}")
    private String denmarkRoutingKey;
    @Value("${routingKey.australia}")
    private String australiaRoutingKey;
    @Value("${routingKey.indonesia}")
    private String indonesiaRoutingKey;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedDelay = 1000 * 1)
    public void publishToCountryInNorthernHemisphere() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Denmark");
        System.out.println("Sending out message to city in northern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(topicExchange, denmarkRoutingKey, sampleRequestMessage);
    }

    @Scheduled(fixedDelay = 1000 * 2)
    public void publishToCountryInSouthernHemisphere() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Australia");
        System.out.println("Sending out message to city in southern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(topicExchange, australiaRoutingKey, sampleRequestMessage);
    }

    @Scheduled(fixedDelay = 1000 * 3)
    public void publishToCountryInInBothTheHemispheres() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Indonesia");
        System.out.println("Sending out message to city in southern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(topicExchange, indonesiaRoutingKey, sampleRequestMessage);
    }

}
