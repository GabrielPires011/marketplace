package com.br.marketplace.repository;

import com.br.marketplace.model.CieloLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CieloLogRepository extends JpaRepository<CieloLog, UUID> {
}
