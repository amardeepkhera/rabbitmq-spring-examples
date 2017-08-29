package in.rabbitmq.clustered;

import in.rabbitmq.SampleRequestMessage;
import in.rabbitmq.SampleResponseMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Publisher {

    @Value("${routingKey.request}")
    private String requestRoutingKey;
    @Autowired
    private DirectExchange directExchange;

    private static SecureRandom SECURE_RANDOM;

    static {
        try {
            SECURE_RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    private AsyncRabbitTemplate asyncRabbitTemplate;


    @Scheduled(fixedDelay = 100 * 1)
    public void publishToDirectExchangeRPCStyle() {
        Integer integer = SECURE_RANDOM.nextInt();
        SampleRequestMessage sampleRequestMessage = new SampleRequestMessage(String.valueOf(integer));
        System.out.println("Sending out message on direct directExchange:" + sampleRequestMessage);

        AsyncRabbitTemplate.RabbitConverterFuture<SampleResponseMessage> sampleResponseMessageRabbitConverterFuture = asyncRabbitTemplate
                        .convertSendAndReceive(directExchange.getName(), requestRoutingKey, sampleRequestMessage);

        sampleResponseMessageRabbitConverterFuture.addCallback(
                        sampleResponseMessage ->
                                        System.out.println("Response for request message:" + sampleRequestMessage + " is:" + sampleResponseMessage)
                        , failure ->
                                        System.out.println(failure.getMessage())
        );

    }

}
