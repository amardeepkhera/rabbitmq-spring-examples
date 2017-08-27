package in.rabbitmq.exchange.direct;

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

    @Value("${exchange.direct}")
    private String directExchange;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000 * 1)
    public void publishToDirectExchange() {
        Integer integer = SECURE_RANDOM.nextInt();
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(String.valueOf(integer));
        System.out.println("Sending out message on direct directExchange:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(directExchange, integer % 2 == 0 ? "white" : "black", sampleRequestMessage);
    }
}
