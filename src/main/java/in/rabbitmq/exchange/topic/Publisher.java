package in.rabbitmq.exchange.topic;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class Publisher {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedDelay = 1000 * 1)
    public void publishToCountryInNorthernHemisphere() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Denmark");
        System.out.println("Sending out message to city in northern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend("spring-boot-rabbitmq-examples.topic", "denmark.country.northern_hemisphere", sampleRequestMessage);
    }

    @Scheduled(fixedDelay = 1000 * 2)
    public void publishToCountryInSouthernHemisphere() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Australia");
        System.out.println("Sending out message to city in southern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend("spring-boot-rabbitmq-examples.topic", "australia.country.southern_hemisphere", sampleRequestMessage);
    }

    @Scheduled(fixedDelay = 1000 * 3)
    public void publishToCountryInInBothTheHemispheres() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Indonesia");
        System.out.println("Sending out message to city in southern hemisphere:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend("spring-boot-rabbitmq-examples.topic", "indonesia.country.both", sampleRequestMessage);
    }

}
