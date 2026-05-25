package com.nugasinbandung.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "coffeeshops")
public class Coffeeshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String address;

    private String mapsUrl;

    private String photoUrl;

    @Column(name = "submitted_by")
    private Long submittedBy;

    @ManyToOne
    @JoinColumn(name = "submitted_by", insertable = false, updatable = false)
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}