package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Evidences;
import com.itms.echallan_system.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvidencesRepository extends JpaRepository<Evidences,Long> {
    Optional<Evidences> findByFileType(Violation violation);
}
