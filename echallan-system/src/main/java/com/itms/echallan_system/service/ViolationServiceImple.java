package com.itms.echallan_system.service;

import com.itms.echallan_system.dto.*;
import com.itms.echallan_system.entity.Offences;
import com.itms.echallan_system.entity.Vehicles;
import com.itms.echallan_system.entity.Violation;
import com.itms.echallan_system.repository.EvidencesRepository;
import com.itms.echallan_system.repository.OffenceRepository;
import com.itms.echallan_system.repository.VehicleRepository;
import com.itms.echallan_system.repository.ViolationRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViolationServiceImple implements VoilationService{

    private final ViolationRepository violationRepository;
    private final VehicleRepository vehicleRepository;
    private final OffenceRepository offenceRepository;
    private final EvidencesRepository evidencesRepository;

    @Override
    public PushViolationResponseDto pushData(
            PushViolationRequestDto request
    ){
        List<SaveDataDto> saveDataList= new ArrayList<>();

        for (CctvNoticeDto dto:request.getCctvNoticeData()){

            Vehicles vehicle=vehicleRepository.findByRegistrationNo(dto.getRegnNo())
                    .orElseThrow(()->new RuntimeException("Vehicle not found"));

            Offences offence=offenceRepository.findByOffenceCode(dto.getOffenceId())
                    .orElseThrow(()->new RuntimeException("offence not found"));

            Violation violation= new Violation();

            violation.setTransactionNo(dto.getTransationNo());

            violation.setDpCd(dto.getDbCd());

            violation.setLocation(dto.getLocation());

            violation.setStateCd(dto.getStateCd());

            violation.setVehicle(vehicle);

            violation.setOffence(offence);

            Violation savedViolation= violationRepository.save(violation);

            SaveDataDto saveData= new SaveDataDto();

            saveData.setTransactionNo(
                    savedViolation.getTransactionNo()
            );

            saveData.setRegnNo(dto.getRegnNo());

            saveData.setOffenceId(dto.getOffenceId());

            saveDataList.add(saveData);

        }

       ResponseMessageDto response= new ResponseMessageDto();

        response.setStatus("200");

        response.setStatus("Data saved successfully");

        response.setMessage("Success");



        return response;

    }
}
