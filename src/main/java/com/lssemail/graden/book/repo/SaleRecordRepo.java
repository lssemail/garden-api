package com.lssemail.graden.book.repo;

import com.lssemail.graden.book.model.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRecordRepo extends JpaRepository<SaleRecord, Long> {
}
