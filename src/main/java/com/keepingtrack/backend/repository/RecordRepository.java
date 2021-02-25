package com.keepingtrack.backend.repository;

import com.keepingtrack.backend.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
