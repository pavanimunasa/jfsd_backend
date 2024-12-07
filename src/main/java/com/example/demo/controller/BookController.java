package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Add a book
    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book added successfully";
    }

    // Update a book
    @PutMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            bookRepository.save(existingBook);
            return "Book updated successfully";
        }).orElse("Book not found");
    }

    // Delete a book
    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return "Book deleted successfully";
        } else {
            return "Book not found";
        }
    }

    // View all books
    @GetMapping("/view-books")
    public List<Book> viewBooks() {
        return bookRepository.findAll();
    }
}
