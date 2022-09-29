package info.thecodinglive.kafka.eventService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {
    
    private final MyEventService myEventService;
    
    @GetMapping("/event")
    public String getEvent() {
        myEventService.sendMsg();
        return "OK";
    }
}
