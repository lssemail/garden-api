package com.lssemail.garden.book.repo;


import com.lssemail.garden.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018-7-28.
 */
public interface BookRepo extends JpaRepository<Book, Long> {
}
