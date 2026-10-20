package com.example.middle_assignment.mapper;

import com.example.middle_assignment.dto.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

  @Select("select id, title, author, created_at, updated_at from books order by created_at desc")
  List<Book> selectAll();

  @Insert("insert into books (title, author, price) values(#{title}, #{author}, #{price})")
  @Options(useGeneratedKeys = true, keyProperty = "id,createdAt,updatedAt", keyColumn = "id,created_at,updated_at")
  int insert(Book book);

  @Select("select id, title, author, price, created_at, updated_at from books where id = #{id}")
  Book findById(long id);

  @Delete("delete from books where id = #{id}")
  int delete(long id);

  @Update("update books set title = #{title}, author = #{author}, price = #{price} where id = #{id}")
  int update(Book book);
}