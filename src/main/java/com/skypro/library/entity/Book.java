package com.skypro.library.entity;

public class Book {

    private String bookname;
    private String author;
    private Integer year;
    private String isbn;

    public Book(String bookName, String author, Integer year, String isbn) {
        this.bookname = bookName;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public Book() {

    }

    public String getBookName() {
        return bookname;
    }

    public void setBookName(String bookName) {
        this.bookname = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
