package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.NoticeStatus;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Violation;
import com.itms.echallan_system.exception.ResourceNotFoundException;
import com.itms.echallan_system.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService{

    private final NoticeRepository noticeRepository;



    @Override
    public Notices generateNotice(Violation violation) {
       Notices notice=new Notices();

       notice.setViolation(violation);

       notice.setNoticeNumber("NTC-"+System.currentTimeMillis());

       notice.setIssue_Date(new Date());

       notice.setStatus(NoticeStatus.PENDING);


        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,15);

        notice.setExpiry_Date(calendar.getTime());



        return noticeRepository.save(notice);
    }

    @Override
    public void approve(Long noticeId) {
        Notices notice = noticeRepository.findById(noticeId).orElseThrow(()-> new ResourceNotFoundException("Notice not found"+noticeId));

        if(notice.getStatus()==NoticeStatus.APPROVED) {
            throw new RuntimeException("notice already approved");
        }
        notice.setStatus(NoticeStatus.APPROVED);
        noticeRepository.save(notice);
    }

    @Override
    public void reject(Long noticeId) {
        Notices notice= noticeRepository.findById(noticeId).orElseThrow(()->new ResourceNotFoundException("Notice rejected"+noticeId));

        if(notice.getStatus()==NoticeStatus.REJECTED){
            throw new RuntimeException("notice already rejected");
        }
        notice.setStatus(NoticeStatus.REJECTED);
        noticeRepository.save(notice);
    }

}
