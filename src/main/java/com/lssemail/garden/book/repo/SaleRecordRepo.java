package com.lssemail.garden.book.repo;

import com.lssemail.garden.book.model.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRecordRepo extends JpaRepository<SaleRecord, Long> {
}
