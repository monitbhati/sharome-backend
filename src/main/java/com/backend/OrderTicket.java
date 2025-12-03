package com.backend;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Customer Info ---
    private String customerName;
    private String phoneNumber;

    // --- Address Details ---
    private String houseNumber;
    private String streetArea;
    private String landmark;
    private String city;
    private String pincode;

    // --- Order Details ---
    private String outfitType; 
    private String sizeDetails; 
    
    @Column(columnDefinition = "TEXT")
    private String referenceImageUrl;

    @Column(columnDefinition = "TEXT")
    private String userNotes;

    private String status;
    
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = "SUBMITTED"; 
    }
}