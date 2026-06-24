package com.itms.echallan_system.repository;

import com.itms.echallan_system.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notices,Long> {
    Optional<Notices> findByNoticeNumber(String noticeNumber);

    List<>
}
