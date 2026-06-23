package com.itms.echallan_system.controller;

import com.itms.echallan_system.dto.PushViolationRequestDto;
import com.itms.echallan_system.dto.PushViolationResponseDto;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.service.NoticeService;
import com.itms.echallan_system.service.VoilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/violation")
@RestController
@RequiredArgsConstructor
public class PushController {

    private final VoilationService voilationService;
    private final NoticeService noticeService;

    @PostMapping("/pushdata")
    public ResponseEntity<PushViolationResponseDto> pushData(@RequestBody PushViolationRequestDto request){
        return ResponseEntity.ok(voilationService.pushData(request));
    }

}
