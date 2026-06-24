package com.itms.echallan_system.controller;

import com.itms.echallan_system.entity.Challan;
import com.itms.echallan_system.service.ChallanService;
import com.itms.echallan_system.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {
        private final NoticeService noticeService;
        private final ChallanService challanService;

        @PostMapping("/{id}/approve")
        public ResponseEntity<String> approve(@PathVariable Long id){
           noticeService.approve(id);
           return ResponseEntity.ok("Notice approved successfully");

        }
        @PostMapping("/{id}/reject")
        public ResponseEntity<String> reject(@PathVariable Long id){
            noticeService.reject(id);
            return ResponseEntity.ok("Notice rejected successfully");
        }
}
