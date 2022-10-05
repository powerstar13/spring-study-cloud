package spring.study.gcp.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.study.gcp.application.GcpService;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class GcpController {
    
    private final GcpService gcpService;
    
    @PostMapping(value = "/google-drive-upload", name = "구글 드라이브 테스트")
    public ResponseEntity<?> googleDriveUpload() throws IOException {
    
        gcpService.upload();
        
        return ResponseEntity.ok("ok");
    }
}
