package com.backend; // Adjust package name if needed
import lombok.Data; // Or use Getters/Setters

@Data
public class UnlockRequest {
    private String code;
    private String timestamp;
    private String userAgent;
    private String screenResolution;
    private String referrer;
    private String language;
    private String timezone;
}
