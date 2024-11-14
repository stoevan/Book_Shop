package com.example.bookshop.web.RestControllers;

import com.example.bookshop.models.Book;
import com.example.bookshop.models.Dto.BookDto;
import com.example.bookshop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> findAll()
    {
        return this.bookService.listAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> create(@RequestBody BookDto booksDto)
    {
        return this.bookService.save(booksDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,@RequestBody BookDto booksDto)
    {
        return this.bookService.edit(id,booksDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Book> delete(@PathVariable Long id)
    {
        this.bookService.delete(id);
        if(this.bookService.FindById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }


    @PostMapping ("/rented/{id}")
    public  ResponseEntity<Book> rented(@PathVariable Long id)
    {
        return this.bookService.rent(id)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

}
