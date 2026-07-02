package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.Challan;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Notification;

public interface NotificationService {
    void sentNotification(Notices notices);

    void ChallanGenerateNoti(Challan challan);
}
