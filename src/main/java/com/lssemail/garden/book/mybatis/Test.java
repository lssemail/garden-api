package com.lssemail.garden.book.mybatis;

import com.lssemail.garden.book.model.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) throws Exception{
        String resource = "com/lssemail/garden/book/mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        Book book = (Book) session.selectOne("selectByPrimaryKey", 1L);
        System.out.println(book.getAuthor());
    }
}

