package info.thecodinglive.feignClient.mapService;


import info.thecodinglive.feignclient.mapService.KakaoLocalSearchClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class KakaoLocalSearchTest {
    
    @Autowired
    private KakaoLocalSearchClient kakaoLocalSearchClient;

    @Test
    public void findLocation() {
        //given
        String query = "율동공원";
        String authHeaderValue = "KakaoAK 464b4c25cadd87ca45f1ea99a7822608";
        
        //when
        ResponseEntity<String> response = kakaoLocalSearchClient.findLocationWithHeader(authHeaderValue, query);
        
        //then
        System.out.println(response);
        
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
