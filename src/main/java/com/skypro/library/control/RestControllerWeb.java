package com.skypro.library.control;

import com.skypro.library.entity.Book;
import com.skypro.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RestControllerWeb {

    private BookService bookService;

    public RestControllerWeb(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/book")
    public List<Book> getAll() {
        return bookService.getAll();
    }


    @PostMapping("/api/book")
    public Book createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return book;

    }

    @PutMapping("/api/book")
    public Book updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
        return book;

    }

    @DeleteMapping("/api/book")
    public String deleteBook(@RequestParam String isbn) {
        bookService.deleteBook(isbn);
        return "Book with isbn = " + isbn + " was deleted";
    }

}

