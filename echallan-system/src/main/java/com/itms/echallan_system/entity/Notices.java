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

    @Column(name = "notice_number",unique = true,nullable = false)
    private String noticeNumber;

    @Column(name = "issue_date")
    private Date issue_Date;

    @Column(name = "expiry_date")
    private Date expiry_Date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NoticeStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;


    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime created_at;
}


