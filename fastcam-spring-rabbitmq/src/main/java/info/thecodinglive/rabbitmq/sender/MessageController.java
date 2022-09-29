package info.thecodinglive.rabbitmq.sender;

import info.thecodinglive.rabbitmq.config.RabbitMQConfig;
import info.thecodinglive.rabbitmq.sample.model.MyTask;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {
    
    private final RabbitMessagePublisher rabbitMessagePublisher;

    @GetMapping("/send")
    public String sendData(@RequestParam(defaultValue = "") String msg) {
        
        rabbitMessagePublisher.publish(RabbitMQConfig.RABBIT_EXECHAGNGE_NAME, new MyTask(msg));

        return "send Data";
    }
}
