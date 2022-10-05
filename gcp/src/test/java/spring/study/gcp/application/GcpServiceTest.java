package spring.study.gcp.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GcpServiceTest {
    
    @Autowired
    private GcpService gcpService;
    
    @Test
    void upload() throws IOException {
        
        gcpService.upload();
    }
    
    @Test
    void search() throws IOException {
        
        gcpService.searchFile();
    }
    
    @Test
    void share() throws IOException {
        
        gcpService.shareFile("1yTIu8EYaxmBp7l1SLLBVIAe9GZyLZY2F", "powerstar13@kai-i.com", "global");
    }
}