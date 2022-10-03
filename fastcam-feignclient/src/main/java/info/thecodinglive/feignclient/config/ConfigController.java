package info.thecodinglive.feignclient.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ConfigController {
    
    private final ConfigProp configProp;
    
    @GetMapping("/v1/check/prop")
    public String findPropMessage() {
        log.info("config:: {}", configProp.toString());
        return "ok";
    }
}
