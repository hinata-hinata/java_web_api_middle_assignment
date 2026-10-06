package com.example.middle_assignment.mapper;

import com.example.middle_assignment.dto.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

  @Select("select id, title, author, created_at, updated_at from books order by created_at desc")
  List<Book> selectAll();
}
