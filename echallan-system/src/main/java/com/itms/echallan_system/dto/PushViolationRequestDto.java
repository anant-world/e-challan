package com.itms.echallan_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PushViolationRequestDto {

    private List<CctvNoticeDto> cctvNoticeData;
}
