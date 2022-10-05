package info.thecodinglive.photoapp.config;

import info.thecodinglive.photoapp.api.MDCLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class WebConfig {
    
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new MDCLogFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/v1/*")); // 해당 패턴으로 접근할 경우 MDCLogFilter가 작동되게 처리
        
        return registrationBean;
    }
}
