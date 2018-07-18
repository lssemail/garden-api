package com.lssemail.graden.book.repo;

import com.lssemail.graden.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long>{

    List<Book> findByReader(String reader);
}

