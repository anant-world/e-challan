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

    // Unique Transaction Number from Camera System
    @Column(name = "transaction_no", unique = true, nullable = false)
    private String transactionNo;

    // Department Code (TP/TR)
    @Column(name = "dp_cd")
    private String dpCd;

    // Violation Source (RLVD, SLVD, OSVD etc.)
    @Column(name = "violation_source")
    private String violationSource;

    @Column(name = "violation_source_catg")
    private String violationSourceCatg;

    @Column(name = "state_cd")
    private String stateCd;

    @Column(name = "off_cd")
    private Integer offCd;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "equipment_id")
    private String equipmentId;

    @Column(name = "location")
    private String location;

    @Column(precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8)
    private BigDecimal longitude;

    @Column(name = "vehicle_speed")
    private Integer vehicleSpeed;

    @Column(name = "speed_limit")
    private Integer speedLimit;

    @Column(name = "vehicle_weight")
    private Integer vehicleWeight;

    @Column(name = "vh_class")
    private String vhClass;

    @Column(name = "road_catg")
    private String roadCatg;

    @Column(name = "road_lane_no",nullable = true)
    private String roadLaneNo;

    @Column(name = "district")
    private String district;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "police_station_name")
    private String policeStationName;

    @Column(name = "video_link_url")
    private String videoLinkUrl;

    @Column(name = "violation_time")
    private LocalDateTime violationTime;

    @Column(name = "action_time")
    private LocalDateTime actionTime;

    @Column(name = "status")
    private String status;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicles vehicle;

    @ManyToOne
    @JoinColumn(name = "offence_id")
    private Offences offence;

    @ManyToOne
    @JoinColumn(name = "camera_id")
    private CameraDevices camera;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    private User verifiedBy;
}