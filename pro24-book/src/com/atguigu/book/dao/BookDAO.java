package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author keyboardhero
 * @create 2023-03-27 16:55
 */
public interface BookDAO {
    List<Book> getBookList();
    Book getBook(Integer id);
}
