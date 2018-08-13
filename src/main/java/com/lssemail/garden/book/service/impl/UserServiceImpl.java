package com.lssemail.garden.book.service.impl;

import com.lssemail.garden.book.model.User;
import com.lssemail.garden.book.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-8-6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getOne(String id) {
        User user = new User();
        user.setId("123");
        user.setName("123");
        return user;
    }
}
