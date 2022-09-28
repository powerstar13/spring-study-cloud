package spring.study.eventapp.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileEventPublisher {
    
    private final ApplicationEventPublisher applicationEventPublisher; // ApplicaitonEventPublisher를 이용하면 이벤트를 전달할 수 있다.
    
    public void notifyComplete(FileEvent fileEvent) {
        
        applicationEventPublisher.publishEvent(fileEvent); // publishEvent() 메서드는 Object를 받을 수 있다.
    }
    
    public void notifyError(FileEvent fileEvent) {
        
        applicationEventPublisher.publishEvent(fileEvent);
    }
}
