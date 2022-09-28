package spring.study.eventapp.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileEventListener {
    
    @EventListener // EventListener 애노테이션을 이용하여 스프링 이벤트를 받을 수 있다.
    public void onFileEventHandler(FileEvent fileEvent) {
    
        log.info("file event receive type: {}, data: {}", fileEvent.getType(), fileEvent.getData());
        
        if (fileEvent.getType().equals(EventType.COMPLETE.name())) {
            log.info("사용자에게 파일 업로드 완료 메시지를 전송");
        }
    }
}
