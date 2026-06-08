package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicles,Long> {
     Optional<Vehicles> findByRegistrationNo(String registrationNo);
}
