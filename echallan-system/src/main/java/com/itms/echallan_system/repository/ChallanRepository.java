package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Challan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChallanRepository extends JpaRepository<Challan,Long> {
    boolean existsByNoticeId(Long NoticeId);
}
