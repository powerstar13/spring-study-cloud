package info.thecodinglive.photoapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MDCKeys {
    /**
     * MDC.put("requestId", requestId);
     * MDC.put("requestUrl", requestUrl);
     * MDC.put("userAgent", userAgent);
     * MDC.put("clientIP", clientIP);
     */
    
    REQUEST_ID("requestId"),
    REQUEST_URL("requestUrl"),
    USER_AGENT("userAgent"),
    CLIENT_IP("clientIP");
    
    private final String propertyKey;
}
