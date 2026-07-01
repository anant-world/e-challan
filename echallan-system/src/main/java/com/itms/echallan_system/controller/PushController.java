package com.itms.echallan_system.controller;

import com.itms.echallan_system.dto.PushViolationRequestDto;
import com.itms.echallan_system.dto.PushViolationResponseDto;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.service.NoticeService;
import com.itms.echallan_system.service.ViolationServiceImple;
import com.itms.echallan_system.service.VoilationService;
import jakarta.xml.bind.helpers.ValidationEventImpl;
import jdk.jshell.Snippet;
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
        PushViolationResponseDto response= voilationService.pushData(request);
        String status= response.getResponseMessageData().getStatus();

        switch (status){
            case "200":
                return ResponseEntity.ok(response);
            case "201":
                return ResponseEntity.status(201).body(response);
            case "205":
                return ResponseEntity.status(205).body(response);
            case "404":
                return ResponseEntity.status(404).body(response);
            default:
                return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approveViolation(@PathVariable Long id){
        voilationService.approve(id);

        return ResponseEntity.ok("violation approved and notice generated");
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectViolation(@PathVariable Long id){
        voilationService.reject(id);
        return ResponseEntity.ok("Not guilty violation rejected");
    }

}
