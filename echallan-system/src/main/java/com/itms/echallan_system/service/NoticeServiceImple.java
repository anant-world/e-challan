package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.NoticeStatus;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Violation;
import com.itms.echallan_system.exception.ResourceNotFoundException;
import com.itms.echallan_system.repository.NoticeRepository;
import com.itms.echallan_system.repository.ViolationRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService{

    private final NoticeRepository noticeRepository;
    private final NotificationService notificationService;


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



       Notices savedNotice = noticeRepository.save(notice);
        notificationService.sentNotification(savedNotice);
        return savedNotice;
    }

}
