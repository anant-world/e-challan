package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Offences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OffenceRepository extends JpaRepository<Offences,Long> {
    Optional<Offences> findByOffenceCode(String offenceCode);
}
