package in.rabbitmq.direct;

import in.rabbitmq.SampleRequestMessage;
import in.rabbitmq.SampleResponseMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class Publisher {

    private static SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue requestQueueForConvertSendAndReceive;
    @Autowired
    private Queue requestQueueForConvertAndSend;

    @Scheduled(fixedDelay = 1000 * 1)
    public void publishUsingConvertAndSend() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(String.valueOf(SECURE_RANDOM.nextInt()));
        System.out.println("Sending out message from publishUsingConvertAndSend:" + sampleRequestMessage);
        rabbitTemplate.convertAndSend(requestQueueForConvertAndSend.getName(), sampleRequestMessage);
    }

    @Scheduled(fixedDelay = 1000 * 2)
    public void publishUsingConvertSendAndReceive() {
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(String.valueOf(SECURE_RANDOM.nextInt()));
        System.out.println("Sending out message from publishUsingConvertSendAndReceive:" + sampleRequestMessage);
        SampleResponseMessage sampleResponseMessage = (SampleResponseMessage) rabbitTemplate.convertSendAndReceive(requestQueueForConvertSendAndReceive.getName(), sampleRequestMessage);
        System.out.println("Received by publisher:" + this.toString() + sampleResponseMessage + " for request message " + sampleRequestMessage);
    }
}
