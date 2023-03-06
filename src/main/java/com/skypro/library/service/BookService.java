package com.skypro.library.service;

import com.skypro.library.entity.Book;

import java.util.List;

public interface BookService {
    void createBook(Book book);
    void updateBook(Book book);
    void deleteBook(String isbn);
    List<Book> getAll();
    Book getByIsbn(String isbn);
}