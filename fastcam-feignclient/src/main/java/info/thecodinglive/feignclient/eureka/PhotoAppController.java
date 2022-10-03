package info.thecodinglive.feignclient.eureka;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhotoAppController {
    
    private final PhotoClient photoClient;

    @GetMapping("/photo")
    public String findServer() {
        return photoClient.sayHello();
    }

    @FeignClient("photoapp")  //도메인 정보
    interface PhotoClient {
        @GetMapping("/clients")
        String sayHello();
    }
}
