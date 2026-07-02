package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.*;
import com.itms.echallan_system.repository.ChallanRepository;
import com.itms.echallan_system.repository.NoticeRepository;
import com.itms.echallan_system.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImple implements NotificationService{

 private final NotificationRepository notificationRepository;



    @Override
    public void sentNotification(Notices notices) {

        if (notices==null){
            throw new RuntimeException("Notice can not be null");
        }
        Notification notification= new Notification();

       Violation violation= notices.getViolation();

        notification.setViolation(violation);

        notification.setRecipient(notices.getViolation().getRegistrationNo());

        notification.setMobileNo("0000000000");

        notification.setMessage("Traffic Violation notice has been generated for vehicle number "
                +notices.getViolation().getRegistrationNo() +  ", Notice Number"
                 +notices.getNoticeNumber());

        notification.setNotificationType(NotificationType.NOTICE_GENERATED);

        notification.setStatus(NotificationStatus.PENDING);

        notificationRepository.save(notification);


    }

    @Override
    public void ChallanGenerateNoti(Challan challan) {

        if (challan==null){
            throw new RuntimeException("Challan cannot be null");
        }
        Notification notification= new Notification();

        notification.setViolation(challan.getViolation());

        notification.setRecipient(challan.getViolation().getRegistrationNo());

        notification.setNotificationType(NotificationType.CHALLAN_GENERATED);

        notification.setMessage("Dear Vehicle Owner,\n" +
                "\n" +
                "Your e-Challan has been generated.\n" +
                "\n" +
                "Vehicle Number : " +challan.getViolation().getRegistrationNo()+
                 "\n" +
                "Challan Number : " +challan.getChallanNo()+
                "\n" +
                "Please pay the challan before the due date.");



        notification.setStatus(NotificationStatus.PENDING);

        notificationRepository.save(notification);
    }
}
