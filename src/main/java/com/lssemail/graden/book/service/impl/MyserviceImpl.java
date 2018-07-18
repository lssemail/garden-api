package com.lssemail.graden.book.service.impl;

import com.lssemail.graden.book.condition.JdbcTemplateCondition;
import com.lssemail.graden.book.service.Myservice;
import org.springframework.context.annotation.Conditional;

@Conditional(JdbcTemplateCondition.class)
public class MyserviceImpl implements Myservice{
}

