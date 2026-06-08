package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.CameraDevices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraDeviceRepo extends JpaRepository<CameraDevices,Long> {
    Optional<CameraDevices> findByCameraName(String cameraName);

    Optional<CameraDevices> findByApiKey(String apiKey);
}
