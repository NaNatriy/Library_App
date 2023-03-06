package com.skypro.library.service;

import com.skypro.library.dao.BookDAO;
import com.skypro.library.entity.Book;
import com.skypro.library.exception.BookException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public void createBook(Book book) {

        validISBN(book);
        bookDAO.createBook(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Book updateBook = this.bookDAO.getByISBN(book.getIsbn());
        if (book == null) {
            throw new BookException("Книга не найдена");
        }
        updateBook.setBookName(book.getBookName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setYear(book.getYear());
        bookDAO.updateBook(updateBook);
    }

    @Override
    @Transactional
    public void deleteBook(String isbn) {
        Book book = bookDAO.getByISBN(isbn);
        if (book == null) {
            throw new BookException("Книга не найдена");
        }
        bookDAO.deleteBook(isbn);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    @Transactional
    public Book getByIsbn(String isbn) {
        Book book = bookDAO.getByISBN(isbn);
        if (book == null) {
            throw new BookException("Book with isbn= " + isbn + "doesn't exist");
        }
        return bookDAO.getByISBN(isbn);
    }

    public boolean validISBN(Book book) {

        if (book.getBookName() == null || book.getAuthor() == null
                || book.getIsbn() == null || book.getYear() < 0) {
            throw new BookException("Не все поля заполнены");
        }

        String currentIsbn = book.getIsbn();
        String cleanIsbn = currentIsbn.replaceAll("[\\-\\s]", "");
//      Убираем лишние символы из ISBN
        if (cleanIsbn.length() != 13 && !cleanIsbn.matches("[0-9]+")) {
            throw new BookException("Неверный ISBN");
        }
//      Проверяем длину ISBN

        int sum = 0;
        for (int i = 0; i < cleanIsbn.length(); i++) {
            int digit = Character.getNumericValue(cleanIsbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = 10 - (sum % 10);
//      Сравниваем контрольную сумму с последней цифрой ISBN
        return checkDigit == Character.getNumericValue(cleanIsbn.charAt(cleanIsbn.length() - 1));
    }
}