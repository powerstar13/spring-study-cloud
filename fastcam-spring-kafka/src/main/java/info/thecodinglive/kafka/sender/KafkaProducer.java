package info.thecodinglive.kafka.sender;

import info.thecodinglive.kafka.eventService.FastcamJacksonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    
    public static final String TOPIC_NAME = "thecodinglive";
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, Object data) {
        try {
            kafkaTemplate.send(topic, FastcamJacksonConverter.toJson(data));
        } catch (Exception e) {
            System.err.println("error" +  e.getMessage());
        }
    }
}
