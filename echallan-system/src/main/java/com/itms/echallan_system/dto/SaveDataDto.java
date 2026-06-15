package com.itms.echallan_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveDataDto {

    private String transactionNo;
    private String regnNo;

    private String complaintID;
    private String offenceId;

    private String voilationSource;
    private String violationSourceCatg;

    private String vendorName;

    private String voilationTime;
    private String actionTime;

    private String location;
    private String latitude;
    private String longitude;

    private String image1;
    private String image2;
    private String image3;

    private String userId;
    private String StateCd;

    private String stateCd;
    private Integer offCd;

    private String totalFee;
    private String responseMsg;
}
