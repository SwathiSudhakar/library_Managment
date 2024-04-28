package com.library.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.book.entity.Book;
import com.library.book.repository.BookRepository;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
//        Optional<Room> room = roomRepository.findById(id);
//        return room.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
    
    @GetMapping("/bn/{bn}")
    public Book getRoomById(@PathVariable String bn) {
        Optional<List<Book>> book = bookRepository.findByBookName(bn);
        if(book.isPresent() && book.get().size()>0) {
        	return book.get().get(0);
        	 //room;
        }
        return null;
        
    }

    @PostMapping
    public ResponseEntity<Book> createRoom(@Valid @RequestBody Book book) {
        Book createdBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateRoom(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();
            existingBook.setBookName(bookDetails.getBookName());
            existingBook.setType(bookDetails.getType());
            existingBook.setAvailable(bookDetails.isAvailable());
            return ResponseEntity.ok(bookRepository.save(existingBook));
        }
        return ResponseEntity.notFound().build();
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
