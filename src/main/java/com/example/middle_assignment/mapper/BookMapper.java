package com.example.middle_assignment.mapper;

import com.example.middle_assignment.dto.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

  @Select("select id, title, author, price, created_at, review from books order by created_at desc")
  List<Book> selectAll();

  @Insert("insert into books (title, author, price, review) values(#{title}, #{author}, #{price}, #{review})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  int insert(Book book);

  @Select("select id, title, author, price, created_at, review from books where id = #{id}")
  Book findById(long id);

  @Delete("delete from books where id = #{id}")
  int delete(long id);

  @Update("update books set title = #{title}, author = #{author}, price = #{price}, review = #{review} where id = #{id}")
  int update(Book book);

  @Select("""
    select id, title, author, price, created_at, review
    from books
    where title like '%' || #{title} || '%'
    order by created_at desc
    """)
  List<Book> searchByTitle(@Param("title") String title);

  @Select("""
    select id, title, author, price, created_at, review
    from books
    where price between #{minPrice} and #{maxPrice}
    order by price asc, id asc
    """)
  List<Book> searchByPrice (
    @Param("minPrice") int minPrice,
    @Param("maxPrice") int maxPrice
  );
}
