package info.thecodinglive.rabbitmq.sender;

import info.thecodinglive.rabbitmq.sample.model.MyTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMessagePublisher {
    
    private final RabbitTemplate rabbitTemplate;

    public void publish(String routingKey, MyTask myTask) {
        try {
            rabbitTemplate.convertAndSend("thecodinglive", "photo.sample", myTask);
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }
}
