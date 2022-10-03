package info.thecodinglive.feignclient.mapService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalSearchResponse {
    
    private Integer id;
    
    @JsonProperty("address_name")
    private String addressName;
    
    @JsonProperty("category_group_name")
    private String categoryGroupName;
    
    @JsonProperty("category_group_code")
    private String categoryGroupCode;
    
    @JsonProperty("category_name")
    private String categoryName;
    
    private String distance;
    
    private String phone;
    
    @JsonProperty("place_name")
    private String placeName;
    
    @JsonProperty("place_url")
    private String placeUrl;
    
    @JsonProperty("road_address_name")
    private String roadAddressName;
    
    private String x;
    
    private String y;
}
