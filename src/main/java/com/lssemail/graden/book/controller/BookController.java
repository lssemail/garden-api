package com.lssemail.graden.book.controller;

import com.lssemail.graden.book.model.Book;
import com.lssemail.graden.book.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/book")
@ConfigurationProperties(prefix = "amazon")
public class BookController {

    private BookRepo bookRepo;

    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    @Autowired
    public BookController(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = bookRepo.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", associateId);
        }
        return "readingList";
    }
    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        bookRepo.save(book);
        return "redirect:/{reader}";
    }

}

