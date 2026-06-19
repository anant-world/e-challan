package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Violation;

public interface NoticeService {
    Notices generateNotice(Violation violation);

    void approve(Long noticeId);
    void reject(Long noticeId);
}
