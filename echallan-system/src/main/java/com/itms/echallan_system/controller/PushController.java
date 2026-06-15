package com.itms.echallan_system.controller;

import com.itms.echallan_system.dto.PushViolationRequestDto;
import com.itms.echallan_system.dto.PushViolationResponseDto;
import com.itms.echallan_system.service.VoilationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/violation")
@RestController
@RequiredArgsConstructor
public class PushController {

    private final VoilationService voilationService;

    @PostMapping("/pushdata")
    public ResponseEntity<PushViolationResponseDto> pushData(@RequestBody PushViolationRequestDto request){
        return ResponseEntity.ok(voilationService.pushData(request));
    }
}
