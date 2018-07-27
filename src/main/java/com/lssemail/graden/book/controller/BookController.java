package com.lssemail.graden.book.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lssemail.graden.book.model.Book;
import com.lssemail.graden.book.model.SaleRecord;
import com.lssemail.graden.book.repo.BookRepo;
import com.lssemail.graden.book.repo.SaleRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
@ConfigurationProperties(prefix = "amazon")
public class BookController {

    private BookRepo bookRepo;

    private SaleRecordRepo recordRepo;

    private String associateId;

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }

    @Autowired
    public BookController(BookRepo bookRepo, SaleRecordRepo recordRepo){
        this.bookRepo = bookRepo;
        this.recordRepo = recordRepo;
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

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map buy(@PathVariable Long id){

        Book book = bookRepo.getOne(id);
        book.setStock(book.getStock() - 1);
        book.setSales(book.getSales() + 1);



        SaleRecord record = new SaleRecord();
        record.setBookId(id);
        record.setCreateDate(new Date());
        record.setNum(1);
        record.setPrice(book.getPrice());
        record.setTotal(record.getPrice());

        bookRepo.save(book);
        recordRepo.save(record);

        Map<String, Object> result = new HashMap<>();
        result.put("ok", true);
        result.put("msg", "success");
        return result;
    }

}

