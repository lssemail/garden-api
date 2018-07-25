package com.lssemail.garden.book.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018-7-25.
 */
public class MybatisUtil {

    private final static String packageName = "com.lssemail.garden.book.mapper";
    private static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "com/lssemail/garden/book/mybatis/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMappers(packageName);
    }

    public static SqlSession openSession(){

        return sqlSessionFactory.openSession();
    }
}
