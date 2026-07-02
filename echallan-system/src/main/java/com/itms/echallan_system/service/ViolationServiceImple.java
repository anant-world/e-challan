package com.itms.echallan_system.service;

import com.itms.echallan_system.dto.*;
import com.itms.echallan_system.entity.*;
import com.itms.echallan_system.exception.ResourceNotFoundException;
import com.itms.echallan_system.exception.ValidateException;
import com.itms.echallan_system.repository.EvidencesRepository;
import com.itms.echallan_system.repository.OffenceRepository;
import com.itms.echallan_system.repository.VehicleRepository;
import com.itms.echallan_system.repository.ViolationRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;


import java.io.IOException;
import java.math.BigDecimal;


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
    private final NoticeService noticeService;

    @Override
    public PushViolationResponseDto pushData(
            PushViolationRequestDto request
    ){
        List<SaveDataDto> saveDataList= new ArrayList<>();
        List<RejectedDataDto> rejectedList= new ArrayList<>();
        for (CctvNoticeDto dto:request.getCctvNoticeData()) {

            try {

                Offences offence = offenceRepository.findByOffenceCode(dto.getOffenceId())
                        .orElseThrow(() -> new ResourceNotFoundException("Offence not found" + dto.getOffenceId()));




                fieldValidation(dto);
                validate(dto);

                Violation violation = new Violation();

                violation.setTransactionNo(dto.getTransationNo());

                violation.setDpCd(dto.getDpCd());

                violation.setLocation(dto.getLocation());

                violation.setStateCd(dto.getStateCd());

                violation.setDpCd(dto.getDpCd());

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

                violation.setStatus(ViolationStatus.PENDING);




                Violation savedViolation = violationRepository.save(violation);


                System.out.println("Image1 = " + dto.getImage1());
                SaveDataDto saveData = new SaveDataDto();
                if (dto.getImage1() != null && !dto.getImage1().isBlank()) {
                    String path = saveImage(dto.getImage1(), dto.getTransationNo() + "_1.png");
                    System.out.println("Inside image block");
                    saveData.setImage1(path);

                    Evidences evidences = new Evidences();

                    evidences.setViolation(savedViolation);
                    evidences.setFileType("IMAGE");
                    evidences.setImagePath(path);
                    evidences.setImageOrder(1);
                    evidences.setUploaded_at(LocalDateTime.now());
                    evidencesRepository.save(evidences);


                }

                if (dto.getImage2() != null && !dto.getImage2().isBlank()) {
                    String path = saveImage(dto.getImage2(), dto.getTransationNo() + "_2.png");
                    saveData.setImage2(path);

                    Evidences evidences = new Evidences();

                    evidences.setViolation(savedViolation);
                    evidences.setFileType("IMAGE_2");
                    evidences.setImagePath(path);
                    evidences.setImageOrder(2);
                    evidences.setUploaded_at(LocalDateTime.now());
                    evidencesRepository.save(evidences);
                }

                if (dto.getImage3() != null && !dto.getImage3().isBlank()) {
                    String path = saveImage(dto.getImage3(), dto.getTransationNo() + "_3.png");

                    saveData.setImage3(path);

                    Evidences evidences = new Evidences();
                    evidences.setViolation(savedViolation);
                    evidences.setFileType("IMAGE_3");
                    evidences.setImagePath(path);
                    evidences.setImageOrder(3);
                    evidences.setUploaded_at(LocalDateTime.now());
                    evidencesRepository.save(evidences);

                }


                saveData.setTransactionNo(
                        savedViolation.getTransactionNo()
                );

                saveData.setUserId(String.valueOf(dto.getUserId()));

                saveData.setRegnNo(dto.getRegnNo());

                saveData.setOffenceId(dto.getOffenceId());

                saveData.setViolationSource(savedViolation.getViolationSource());

                saveData.setViolationSourceCatg(savedViolation.getViolationSourceCatg());

                saveData.setVendorName(savedViolation.getVendorName());

                saveData.setViolationTime(savedViolation.getViolationTime());

                saveData.setActionTime(savedViolation.getActionTime());

                saveData.setLatitude(savedViolation.getLatitude().doubleValue());

                saveData.setLocation(savedViolation.getLocation());

                saveData.setLongitude(savedViolation.getLongitude().doubleValue());

                saveData.setStateCd(savedViolation.getStateCd());

                saveData.setOffCd(savedViolation.getOffCd());

                saveData.setDpCd(savedViolation.getDpCd());

                saveData.setComplaintId(null);

                saveData.setTotalFee(null);

                saveData.setResponseMsg(null);
                saveDataList.add(saveData);


            }
            catch(RuntimeException e){
                RejectedDataDto rejected = new RejectedDataDto();
                rejected.setTransactionNo(dto.getTransationNo());

                rejected.setReason(e.getMessage());

                rejectedList.add(rejected);
        }
            catch(Exception e){
                RejectedDataDto rejected= new RejectedDataDto();

                rejected.setReason(dto.getTransationNo());

                rejected.setReason("Internal server error: " + e.getMessage());

                rejectedList.add(rejected);
                e.printStackTrace();
        }
        }







       ResponseMessageDto response= new ResponseMessageDto();

        if (saveDataList.isEmpty()){
            response.setStatus("201");
            response.setMessage("Failed");
            response.setReason("Bad request input parameter is missing");
        } else if (rejectedList.isEmpty()) {
            response.setStatus("200");
            response.setMessage("Data saved successfully");
            response.setMessage("All records are saved successfully");
            
        }else {
            response.setStatus("200");
            response.setReason("Partial success");
            response.setMessage("Some data are saved but are failed to do so");
        }

        PushViolationResponseDto responseSave= new PushViolationResponseDto();
        responseSave.setSaveData(saveDataList);
        responseSave.setResponseMessageData(response);

        responseSave.setRejectedData(rejectedList);






        
        responseSave.setCctvNoticeData(request.getCctvNoticeData());

        return responseSave;



    }


    @Override
    public void approve(Long ViolationId){
        Violation violation= violationRepository.findById(ViolationId).orElseThrow(()->new RuntimeException("Notice is already approved"));

        violation.setStatus(ViolationStatus.APPROVED);

        violationRepository.save(violation);

        noticeService.generateNotice(violation);
    }

    public void reject(Long ViolationId){
        Violation violation= violationRepository.findById(ViolationId).orElseThrow(()->new RuntimeException("Violation rejected"));
        violation.setStatus(ViolationStatus.REJECT);
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


    private void fieldValidation(CctvNoticeDto dto){
        if(dto.getTransationNo()==null || dto.getTransationNo().isBlank()){
            throw new ValidateException("Transaction number is mandatory");
        }

        else if(!dto.getTransationNo().matches("^[A-Z0-9]{20,30}$")){
            throw new ValidateException("Invalid Transaction Number");
        }
        else if (dto.getRegnNo()==null || dto.getRegnNo().isBlank()){
            throw new ValidateException("Registration number is mandatory");
        }

        else if(!dto.getRegnNo().matches("^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$")){
            throw new ValidateException("Invalid Registration Number");
        }
        else if(dto.getImage1()==null || dto.getImage1().isBlank()){
            throw new ValidateException("Image1 is mandatory");
        }
        else if (dto.getVehicleSpeed()<0){
            throw new ValidateException("Vehicle Speed Cannot be negative");
        }
        else if (dto.getSpeedLimit()<=0){
            throw new ValidateException("Invalid Speed Limit");
        }
        else if (dto.getLocation()==null || dto.getLocation().isBlank()){
            throw new ValidateException("Location is mandatory");
        }
        else if (dto.getLatitude()<-90 || dto.getLatitude()>90){
            throw new ValidateException("Invalid latitude");
        }
        else if (dto.getLongitude()<-180 || dto.getLongitude()>180){
            throw new ValidateException("Invalid Longitude");

        }
        else if (dto.getStateCd()==null || dto.getStateCd().isBlank()){
            throw new ValidateException("State code is mandatory");
        }
        else if (dto.getStateCd().length()!=2){
            throw new ValidateException("Invalid state code");
        }
        else if (dto.getDpCd()==null || dto.getDpCd().isBlank()){
            throw new ValidateException("DP Code is mandatory");
        }
    }
    private void validate(CctvNoticeDto dto){



        if (violationRepository.existsByTransactionNo(dto.getTransationNo())){
            System.out.println("inside validata and violation repository method");
            throw new ValidateException("Transation number is already exsist");

        }
        if (offenceRepository.findByOffenceCode(dto.getOffenceId()).isEmpty()){
            System.out.println("inside validate offence repository method");
            throw new ValidateException("Invalid offence id");
        }
    }
}

