package com.lssemail.garden.book.service;

import com.lssemail.garden.book.model.Book;

import java.util.List;

/**
 * Created by Administrator on 2018-7-30.
 */
public interface BookService {

    void insert(Book book);

    List<Book> findAll();

    String findOne(Long id);
}
