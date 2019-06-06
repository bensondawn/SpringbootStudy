package com.benson.elasticsearch.controller;

import com.benson.elasticsearch.model.Book;
import com.benson.elasticsearch.service.BookRepository;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("put")
    public Book putBook(@RequestBody Book book) {

        if (book != null) {
            return bookRepository.save(book);
        }
        return new Book();
    }

//    @PostMapping("getAll")
//    public List<Book> getAll() {
//        return Lists.newArrayList(bookRepository.findAll());
//    }

    @PostMapping("getById")
    public Book getBook(@RequestBody Map<String, String> requestBody) {
        String id = requestBody.get("id");
        return bookRepository.findById(id).get();
    }

//    @PostMapping("getByName")
//    public List<Book> getBookByName(@RequestBody Map<String, String> requestBody){
//        String name = requestBody.get("name");
//        return Lists.newArrayList(bookRepository.findByName(name));
//    }

    //

    @RequestMapping(value = "add_index", method = RequestMethod.POST)
    public ResponseEntity<String> indexDoc(@RequestBody Book book) {
        System.out.println("book===" + book);
        bookRepository.save(book);
        return new ResponseEntity<>("save executed!", HttpStatus.OK);
    }

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    public ResponseEntity<Iterable> getAll() {
        Iterable<Book> all = bookRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "getByName", method = RequestMethod.POST)
    public ResponseEntity<Book> getByName(@RequestBody Map<String, String> requestBody) {
        String name = requestBody.get("name");
        Book book = bookRepository.findByName(name);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") String id,
                                           @RequestBody Book updateBook) {
        Book book = bookRepository.findBookById(id);
        if (StringUtils.isNotBlank(updateBook.getId())) {
            book.setId(updateBook.getId());
        }
        if (StringUtils.isNotBlank(updateBook.getName())) {
            book.setName(updateBook.getName());
        }
        if (StringUtils.isNotBlank(updateBook.getAuthor())) {
            book.setAuthor(updateBook.getAuthor());
        }
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("delete execute!", HttpStatus.OK);
    }
}