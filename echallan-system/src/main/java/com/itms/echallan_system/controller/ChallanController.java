package com.itms.echallan_system.controller;


import com.itms.echallan_system.entity.Challan;
import com.itms.echallan_system.service.ChallanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/challan")
@RequiredArgsConstructor
public class ChallanController {

    private final ChallanService challanService;

    @PostMapping("/generate/{noticeId}")
    public ResponseEntity<Challan> generate(@PathVariable Long noticeId){
        return ResponseEntity.ok(challanService.generateChallan(noticeId));
    }
}
