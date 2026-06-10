package com.itms.echallan_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CctvNoticeDto {

    private String offenceId;
    private String dbCd;

    private String transationNo;
    private Long UserId;
    private String regnNo;

    @JsonProperty("violationSource")
    private String violationSource;
    private String violationSourceCatg;

    private String stateCd;
    private String location;

    private String offCd;
    private String vendorName;

    private LocalDateTime violationTime;
    private LocalDateTime actionTime;

    private Double latitude;
    private Double longitude;

    private String image1;
    private String image2;
    private String image3;

    private Integer vehicleSpeed;
    private Integer speedLimit;

    private Integer districtId;
    private String equipmentId;

    private Integer vehicleWeight;

    private String vhClass;

    private String roadCatg;

    private String roadLaneNo;

    private String policeStationName;

    private String videoLinkUrl;
    private String district;

}
