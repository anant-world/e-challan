package com.itms.echallan_system.service;

import com.itms.echallan_system.dto.*;
import com.itms.echallan_system.entity.Evidences;
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

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;





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



            Offences offence=offenceRepository.findByOffenceCode(dto.getOffenceId())
                    .orElseThrow(()->new RuntimeException("offence not found"));

            Violation violation= new Violation();

            violation.setTransactionNo(dto.getTransationNo());

            violation.setDpCd(dto.getDpCd());

            violation.setLocation(dto.getLocation());

            violation.setStateCd(dto.getStateCd());



            violation.setOffence(offence);

            violation.setRegistrationNo(dto.getRegnNo());

            violation.setViolationSource(dto.getViolationSource());

            violation.setViolationSourceCatg(dto.getViolationSourceCatg());

            violation.setOffCd(dto.getOffCd());

            violation.setVendorName(dto.getVendorName());

            violation.setEquipmentId(dto.getEquipmentId());

            violation.setLatitude(BigDecimal.valueOf(dto.getLatitude()));

            violation.setLongitude(BigDecimal.valueOf(dto.getLongitude()));

            violation.setVehicleSpeed(dto.getVehicleSpeed());


            violation.setSpeedLimit(dto.getSpeedLimit());

            violation.setVehicleWeight(dto.getVehicleWeight());

            violation.setVhClass(dto.getVhClass());

            violation.setRoadCatg(dto.getRoadCatg());

            violation.setRoadLaneNo(dto.getRoadLaneNo());

            violation.setPoliceStationName(dto.getPoliceStationName());

            violation.setVideoLinkUrl(dto.getVideoLinkUrl());

            violation.setViolationTime(LocalDateTime.now());

            violation.setDistrict(dto.getDistrict());

            violation.setDistrictId(dto.getDistrictId());

            violation.setActionTime(dto.getActionTime());


            Violation savedViolation= violationRepository.save(violation);

            if(dto.getDistrict()!=null || dto.getImage1().isBlank()){
                String path=saveImage(dto.getImage1(),dto.getTransationNo()+"_1.png" );
            Evidences evidences=new Evidences();

            evidences.setViolation(savedViolation);
            evidences.setFileType("IMAGE");
            evidences.setImagePath(path);
            evidences.setImageOrder(1);
            evidences.setUploaded_at(LocalDateTime.now());
            evidencesRepository.save(evidences);

            }




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

        response.setReason("Data saved successfully");

        response.setMessage("Success");

        PushViolationResponseDto responseSave= new PushViolationResponseDto();
        responseSave.setSaveData(saveDataList);
        responseSave.setResponseMessageData(response);

        responseSave.setRejectedData(new ArrayList<>());




        
        return responseSave;





    }

    private String saveImage(String base64Image,String fileName){
        try{
            String UploadDir="uploads/evidences/";

            Files.createDirectories(Paths.get(UploadDir));

            byte [] byteImage= Base64.getDecoder().decode(base64Image);

            String path= UploadDir+fileName;

            Files.write(Paths.get(path),byteImage);

            return path;



         } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("failed to save image",e);

        }
    }

}
