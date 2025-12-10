package com.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*") 
public class AnalyticsController {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    @PostMapping("/unlock")
    public void logUnlock(@RequestBody UnlockRequest requestData, HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty()) {
            clientIp = request.getRemoteAddr();
        }

        AnalyticsLog log = new AnalyticsLog();
        log.setUnlockCode(requestData.getCode());
        log.setUserAgent(requestData.getUserAgent());
        log.setScreenResolution(requestData.getScreenResolution());
        log.setReferrer(requestData.getReferrer());
        log.setClientLanguage(requestData.getLanguage());
        log.setClientTimezone(requestData.getTimezone());
        log.setIpAddress(clientIp);
        
        analyticsRepository.save(log);
        System.out.println("✅ Logged IP: " + clientIp);
    }
}