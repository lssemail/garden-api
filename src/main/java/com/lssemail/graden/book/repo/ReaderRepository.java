package com.lssemail.graden.book.repo;

import com.lssemail.graden.book.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}

