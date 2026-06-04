package com.itms.echallan_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "challan")
@NoArgsConstructor
@AllArgsConstructor
public class Challan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "violation_id")
    private Violation violation;

    @OneToOne
    @JoinColumn(name="notice_id")
    private Notices notice;

    @Column(unique = true)
    private String challan_no;

    private BigDecimal amount;

    private Date issue_date;

    private Date due_date;

    private String status;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime created_at;

}
