package com.lssemail;

import com.lssemail.garden.book.mapper.BookMapper;
import com.lssemail.garden.book.model.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class BookTest extends GardenApplicationTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void test(){
//        Book book = bookMapper.findBookById(1L);
//        System.out.println(book.getAuthor());
    }

    @Test
    @Transactional
    public void insert(){

        Book book = new Book();
        book.setId(3L);
        book.setAuthor("night");
        book.setDescription("good night");
        book.setIsbn("isbn:12312321");
        book.setTitle("night");

//        bookMapper.insert(book);
    }
}

