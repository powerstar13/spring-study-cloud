package info.thecodinglive.cloud;

import info.thecodinglive.cloud.config.ConfigProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@RefreshScope
@EnableScheduling
@EnableConfigurationProperties(ConfigProp.class)
@SpringBootApplication
public class ConfigConsumerApp implements CommandLineRunner {
    
    private final ConfigProp configProp;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("load config server property : {}", configProp.toString());
    }
    
    public ConfigConsumerApp(ConfigProp configProp) {
        this.configProp = configProp;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigConsumerApp.class, args);
    }
    
    private void recheckProp() {
        log.info("property: {}", configProp.toString());
    }
}
