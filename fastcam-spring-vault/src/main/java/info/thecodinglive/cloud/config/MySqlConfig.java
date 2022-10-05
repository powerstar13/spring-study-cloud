package info.thecodinglive.cloud.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("mysql")
public class MySqlConfig {
    
    private String userName;
    private String password;
}
