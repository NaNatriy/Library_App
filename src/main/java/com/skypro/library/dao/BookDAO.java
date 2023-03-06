package com.skypro.library.dao;

import com.skypro.library.entity.Book;

import java.util.List;
public interface BookDAO {

    void createBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
    List<Book> getAll();
    Book getByISBN(String isbn);
}
