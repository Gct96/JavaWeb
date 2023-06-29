package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDAO;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import java.util.List;

/**
 * @author keyboardhero
 * @create 2023-03-27 17:10
 */
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
