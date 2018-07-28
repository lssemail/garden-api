package com.lssemail.garden;

import com.lssemail.garden.book.model.Book;
import com.lssemail.garden.book.model.Post;
import com.lssemail.garden.book.model.Tag;
import com.lssemail.garden.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2018-7-25.
 */

public class Base {

    @Test
    public void test(){
        SqlSession session = MybatisUtil.openSession();
        Book book = (Book) session.selectOne("findBookById", 1L);
        System.out.println(book.getAuthor());

        Book book1 = (Book) session.selectOne("selectByPrimaryKey", 1L);
        System.out.println(book1.getAuthor());
    }

    @Test
    public void insertTag(){
        SqlSession session = MybatisUtil.openSession();
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("精彩");
        int id = session.insert("com.lssemail.garden.book.mapper.TagMapper.insert", tag);
        session.commit();
        session.close();
        System.out.println(id);

    }

    @Test
    public void insertPost(){
        SqlSession session = MybatisUtil.openSession();
        Post model = new Post();
        model.setId(1);
        model.setBlog_id(1);
        model.setAuthor_id(1);
        model.setCreated_on(new Date());
        model.setSection("this is section");
        model.setSubject("this is subject");
        model.setBody("hello, world, this is a new world");
        model.setDraft("this is draf");

        int id = session.insert("com.lssemail.garden.book.mapper.PostMapper.insert", model);
        session.commit();
        session.close();
        System.out.println(id);
    }
}
