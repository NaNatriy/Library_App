package com.skypro.library.control;

import com.skypro.library.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skypro")
public class SpringMVCController {
    private final BookService bookService;

    public SpringMVCController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/web")
    public String showBooks(Model model){
        model.addAttribute("bookS", bookService.getAll());
        return "dashboard";
    }
}
