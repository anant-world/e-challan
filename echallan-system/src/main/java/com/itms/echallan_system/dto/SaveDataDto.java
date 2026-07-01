package com.itms.echallan_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaveDataDto {

    private String transactionNo;
    private String regnNo;

    private String complaintId;
    private String offenceId;

    private String violationSource;
    private String violationSourceCatg;

    private String vendorName;

    private LocalDateTime violationTime;
    private LocalDateTime actionTime;

    private String location;
    private Double latitude;
    private Double longitude;

    private String image1;
    private String image2;
    private String image3;

    private String userId;
    private String stateCd;
    private Integer offCd;
    private String dpCd;

    private BigDecimal totalFee;
    private String responseMsg;
}