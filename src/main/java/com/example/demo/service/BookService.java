package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.BookController;
import com.example.demo.entity.Book;

@RestController
@CrossOrigin
public class BookService {
@Autowired
BookController dao;
  @PostMapping("/add-book")
  public String addbook(@RequestBody Book c) {
    dao.add(c);
    return "course added";
  }
}