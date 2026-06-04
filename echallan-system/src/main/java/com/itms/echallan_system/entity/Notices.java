package com.itms.echallan_system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notices")

public class Notices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "violation_id")
    private Violation violation;

    private String noticeNumber;

    private Date issue_Date;

    private Date expiry_Date;

    private String status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime created_at;
}


