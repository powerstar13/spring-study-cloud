package spring.study.eventapp.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileController {
    
    private final FileService fileService;
    
    @GetMapping("/upload/image")
    public ResponseEntity<?> fileUpload() {
    
        Map<String, Object> data = new HashMap<>();
        data.put("userId", "홍길동");
        data.put("type", "webp");
        data.put("fileSize", 5);
        
        fileService.fileUpload(data);
    
        return ResponseEntity.ok("success");
    }
}
