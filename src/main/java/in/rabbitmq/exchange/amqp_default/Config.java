package in.rabbitmq.exchange.amqp_default;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration("amqp_defaultConfig")
@Profile("amqp_default")
@EnableScheduling
@EnableRabbit
@ComponentScan(basePackages = {"in.rabbitmq.exchange.amqp_default"})
public class Config {

    @Value("${queue.requestQueueForConvertSendAndReceive}")
    private String requestQueueForConvertSendAndReceive;
    @Value("${queue.requestQueueForConvertAndSend}")
    private String requestQueueForConvertAndSend;

    @Bean
    public Publisher publisher() {
        return new Publisher();
    }

    @Bean
    public Queue requestQueueForConvertAndSend() {
        return new Queue(requestQueueForConvertAndSend);
    }

    @Bean
    public Queue requestQueueForConvertSendAndReceive() {
        return new Queue(requestQueueForConvertSendAndReceive);
    }


    @Bean
    public SimpleRabbitListenerContainerFactory simpleMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                               SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(jsonMessageConverter());
        factory.setConcurrentConsumers(2);
        factory.setMaxConcurrentConsumers(3);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public Subscriber subscriber() {
        return new Subscriber();
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
