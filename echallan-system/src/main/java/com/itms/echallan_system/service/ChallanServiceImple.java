package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.*;
import com.itms.echallan_system.exception.ResourceNotFoundException;
import com.itms.echallan_system.repository.ChallanRepository;
import com.itms.echallan_system.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ChallanServiceImple implements ChallanService{

    private final ChallanRepository challanRepository;
    private final NoticeRepository noticeRepository;



    public Challan generateChallan(Long noticeId){
        Challan challan = new Challan();

        Notices notice = noticeRepository.findById(noticeId).orElseThrow(()->new RuntimeException("Notice not found :"+noticeId));

        challan.setNotice(notice);

        challan.setAmount(notice.getViolation().getOffence().getChallanAmount());

        challan.setChallanNo("CHL"+System.currentTimeMillis());

        challan.setViolation(notice.getViolation());

        challan.setCreated_at(LocalDateTime.now());

        challan.setCreatedBy(null);

        Calendar calender= Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH,15);
        challan.setIssue_date(new Date());

        challan.setDue_date(calender.getTime());

        challan.setStatus(ChallanStatus.PENDING);



        return challanRepository.save(challan);

    }

    @Transactional
    public Challan generateNotice(Long noticeId){
        Notices notice= noticeRepository.findById(noticeId).orElseThrow(()-> new ResourceNotFoundException("challan not found" + noticeId));

        if(notice.getStatus()!= NoticeStatus.APPROVED) {
            throw new RuntimeException("Notice not found exception");


        };
        if (challanRepository.existsByNoticeId(noticeId)) {
            throw new RuntimeException("Challan already generated");
        }

        Challan  challan = new Challan();

        challan.setNotice(notice);

        challan.setAmount(notice.getViolation().getOffence().getChallanAmount());

        challan.setChallanNo("CHL" +System.currentTimeMillis());

        challan.setIssue_date(new Date());

        Calendar calendar= Calendar.getInstance();calendar.add(Calendar.DAY_OF_MONTH,15);

        challan.setDue_date(calendar.getTime());

        challan.setCreatedBy(null);

        challan.setStatus(ChallanStatus.PENDING);

       return challanRepository.save(challan);

       
    }
}
