package com.lssemail.garden;

import com.lssemail.garden.book.model.Blog;
import com.lssemail.garden.book.model.BlogDTO;
import com.lssemail.garden.book.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * Created by Administrator on 2018-7-25.
 */
public class BlogTest {

    @Test
    public void select(){

        SqlSession session = MybatisUtil.openSession();
        BlogDTO blog = session.selectOne("com.lssemail.garden.book.mapper.BlogMapper.selectBlogDetails", 1);
        System.out.println(blog.toString());
        session.close();
    }
}
