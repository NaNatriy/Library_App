package com.skypro.library.dao;

import com.skypro.library.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

    private JdbcTemplate jdbcTemplate;

    public BookDAOImpl(@Lazy JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void createBook(Book book) {
        String sql = "INSERT INTO books (bookname, author, year, isbn) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getBookName(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void updateBook(Book book) {
        String sql = "UPDATE books SET bookname = ?, author = ?, year = ?, isbn = ?";
        jdbcTemplate.update(sql, book.getBookName(), book.getAuthor(), book.getYear(), book.getIsbn());
    }

    @Override
    public void deleteBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Book(rs.getString("bookname"), rs.getString("author"), rs.getInt("year"), rs.getString("isbn")));
    }

    @Override
    public Book getByISBN(String isbn) {
        String sql = "SELECT bookname, author, year FROM books WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql, getAll().toArray(new Book[0]), (rs, rowNum) -> new Book(rs.getString("bookname"), rs.getString("author"), rs.getInt("year"), rs.getString("isbn")));
    }
}
