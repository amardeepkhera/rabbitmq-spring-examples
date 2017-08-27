package in.rabbitmq.exchange.headers;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;


public class Publisher {

    @Value("${exchange.headers}")
    private String headersExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedDelay = 1000 * 1)
    public void publishMessageFromIndianMale() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("Indian male");
        System.out.println("Sending out message from an Indian male:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(headersExchange, "routing_key.which_will_be_ignored.because_exchange_type_is.headers", sampleRequestMessage, message -> {
            message.getMessageProperties().setHeader("nationality", "indian");
            message.getMessageProperties().setHeader("gender", "male");
            return message;
        });
    }

    @Scheduled(fixedDelay = 1000 * 2)
    public void publishMessageFromAmericanFemale() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("American female");
        System.out.println("Sending out message from an American female:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(headersExchange, "", sampleRequestMessage, message -> {
            message.getMessageProperties().setHeader("nationality", "american");
            message.getMessageProperties().setHeader("gender", "female");
            return message;
        });
    }

    @Scheduled(fixedDelay = 1000 * 3)
    public void publishMessageFromAnAmerican() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage("American");
        System.out.println("Sending out message from an American :" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(headersExchange, "", sampleRequestMessage, message -> {
            message.getMessageProperties().setHeader("nationality", "american");
            message.getMessageProperties().setHeader("gender", "unknown");
            return message;
        });
    }
}
