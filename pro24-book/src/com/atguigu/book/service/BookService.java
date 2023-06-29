package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * @author keyboardhero
 * @create 2023-03-27 17:09
 */
public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
}
