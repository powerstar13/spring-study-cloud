package info.thecodinglive.cloud.app;

import info.thecodinglive.cloud.config.MySqlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(MySqlConfig.class)
public class SpringVaultApp implements CommandLineRunner {
    
    private final MySqlConfig mySqlConfig;
    
    public SpringVaultApp(MySqlConfig mySqlConfig) {
        this.mySqlConfig = mySqlConfig;
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SpringVaultApp.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("proerties: {} , {}", mySqlConfig.getUserName(), mySqlConfig.getPassword());
    }
}
