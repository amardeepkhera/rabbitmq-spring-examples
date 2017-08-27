package in.rabbitmq.exchange.fanout;

import in.rabbitmq.SampleRequestMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Publisher {

    private static SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Value("${exchange.fanout}")
    private String fanoutExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Scheduled(fixedDelay = 1000 * 1)
    public void publishToFanoutExchange() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(String.valueOf(SECURE_RANDOM.nextInt()));
        System.out.println("Sending out message on fanout directExchange:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(fanoutExchange, "", sampleRequestMessage);
    }


}
