package com.itms.echallan_system.service;

import com.itms.echallan_system.entity.Challan;
import com.itms.echallan_system.entity.Notices;
import com.itms.echallan_system.entity.Violation;

public interface ChallanService {
    Challan generateChallan(Notices notices);


}
