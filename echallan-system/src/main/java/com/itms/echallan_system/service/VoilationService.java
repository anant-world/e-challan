package com.itms.echallan_system.service;

import com.itms.echallan_system.dto.CctvNoticeDto;
import com.itms.echallan_system.dto.PushViolationRequestDto;
import com.itms.echallan_system.dto.PushViolationResponseDto;

public interface VoilationService {

    PushViolationResponseDto pushData(PushViolationRequestDto request);


}
