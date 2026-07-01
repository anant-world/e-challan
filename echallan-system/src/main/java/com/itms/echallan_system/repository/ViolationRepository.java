package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Violation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ViolationRepository extends JpaRepository<Violation,Long> {
     Optional<Violation> findById(Long Id);
     boolean existsByTransactionNo(String transactionNo);


}
