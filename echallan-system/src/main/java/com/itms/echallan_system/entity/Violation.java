package com.itms.echallan_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "violation")

public class Violation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicles vehicles;

    @ManyToOne
     @JoinColumn(name = "offence_id")
    private Offences offence;

    @ManyToOne
    @JoinColumn(name="camera_id")
    private CameraDevices camera;

    private LocalDateTime violation_time;

    private String location;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;
    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime verified_at;
}
