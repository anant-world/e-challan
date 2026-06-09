package com.itms.echallan_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PushViolationResponseDto {

    private List<SaveDataDto> saveData;

    private Object CctvNoticeData;

    private List<Object> rejectedData;

    private ResponseMessageDto responseMessageData;
}
