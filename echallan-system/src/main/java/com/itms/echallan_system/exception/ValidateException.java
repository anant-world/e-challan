package com.itms.echallan_system.exception;

import com.itms.echallan_system.dto.CctvNoticeDto;
import com.itms.echallan_system.repository.OffenceRepository;
import com.itms.echallan_system.repository.ViolationRepository;

public class ValidateException extends RuntimeException{
    public ValidateException(String message){
        super(message);
    }
}
