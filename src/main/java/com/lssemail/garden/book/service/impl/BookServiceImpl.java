package com.lssemail.garden.book.service.impl;

import com.lssemail.garden.book.model.Book;
import com.lssemail.garden.book.repo.BookRepo;
import com.lssemail.garden.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018-7-30.
 */
@Service
public class BookServiceImpl implements BookService {

    private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepo bookRepo;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void insert(Book book) {

    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public String findOne(Long id) {

        String sid = String.valueOf(id);
        bookRepo.findAll().stream().forEach(item -> {
            logger.info("from db");
            redisTemplate.opsForValue().set(item.getId().toString(), item);

        });

        logger.info("from redis");
        Book bk = (Book) redisTemplate.opsForValue().get(sid);
        logger.info(bk.toString());
        return bk.getTitle();
    }
}
