package com.rfbsoft.nuxt.controllers;

import com.rfbsoft.nuxt.entities.Book;
import com.rfbsoft.nuxt.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping(BookController.CONTROLLER_PATH)
public class BookController {
    public static final String CONTROLLER_PATH = "/books";
    @Autowired
    BookRepository repository;

    @GetMapping
    ResponseEntity all() {

        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")
    ResponseEntity get(@PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(repository.findById(id));

    }

    @PostMapping
    ResponseEntity save(@RequestBody Book book) {
        System.out.println(book);

        return ResponseEntity.ok(repository.save(book));

    }


    @PutMapping("/{id}")
    ResponseEntity update(@PathVariable(name = "id") Long id, @RequestBody Book book) {

        return repository.findById(id)
                .map(book1 -> {
                    book1.setIsbn(book.getIsbn());
                    book1.setTitle(book.getTitle());
                    return ResponseEntity.ok(repository.save(book1));
                })
                .orElseGet(() -> {
                    book.setId(id);
                    return ResponseEntity.ok(repository.save(book));
                });


    }


    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable(name = "id") Long id) {

        return repository.findById(id)
                .map(book1 -> {
                    repository.delete(book1);
                    return ResponseEntity.ok(book1);
                })
                .orElseThrow(() -> new RuntimeException("Not Found"));


    }

}
