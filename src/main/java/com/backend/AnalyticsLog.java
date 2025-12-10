package com.backend;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "analytics_logs")
@Data
public class AnalyticsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unlockCode;
    @Column(columnDefinition = "TEXT")
    private String userAgent;
    private String ipAddress;
    private String screenResolution;
    private String referrer;
    private String clientLanguage;
    private String clientTimezone;
    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate() {
        if (this.timestamp == null) this.timestamp = LocalDateTime.now();
    }
}