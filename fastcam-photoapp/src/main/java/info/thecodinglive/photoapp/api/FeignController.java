package info.thecodinglive.photoapp.api;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeignController {
    
    private final DiscoveryClient discoveryClient;
    
    @GetMapping("/clients")
    public String welcome() {
        
        List<ServiceInstance> instances = discoveryClient.getInstances("photoapp"); // Eureka에서 서비스 가져온다.
        ServiceInstance selectedInstance = instances.get(0);
        
        return "welcome to spring cloud:" + selectedInstance.getInstanceId() + ":" + selectedInstance.getPort();
    }
}
