package info.thecodinglive.cloud.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigChangeListener implements ApplicationListener<EnvironmentChangeEvent> {
    
    private final ConfigProp configProp;
    
    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        //if(event.getKeys().contains("username"))
        log.info("event key: {}", event.getKeys().toString());
        //configProp.ref
    }
}
