package info.thecodinglive.kafka.eventService;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.UUID;

@Getter
@ToString
public class MyEvent {

    private final String eventId;
    private final Map<String, Object> myData;

    public MyEvent(Map<String, Object> myData) {
        
        this.eventId = UUID.randomUUID().toString();
        this.myData = myData;
    }
}
