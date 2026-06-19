package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.Challan;
import com.itms.echallan_system.entity.ChallanStatus;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Violation;
import com.itms.echallan_system.repository.ChallanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ChallanServiceImple implements ChallanService{

    private final ChallanRepository challanRepository;

    public Challan generateChallan(Notices notice){
        Challan challan = new Challan();

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
}
