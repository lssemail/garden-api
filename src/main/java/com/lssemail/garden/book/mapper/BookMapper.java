package com.lssemail.garden.book.mapper;

import com.lssemail.garden.book.model.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

    @Select("select * from book where id = #{id}")
    Book findBookById(@Param("id") Long id);

    //@Insert("insert into book(id, reader, title) values (#{id}, #{reader}, #{title})")
    void insert(Book book);

}
