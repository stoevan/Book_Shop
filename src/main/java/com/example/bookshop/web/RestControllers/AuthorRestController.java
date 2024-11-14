package com.example.bookshop.web.RestControllers;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Dto.AuthorDto;
import com.example.bookshop.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    private List<Author> findAll()
    {
        return this.authorService.listAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Author> create(@RequestBody AuthorDto authorDto)
    {
        return this.authorService.save(authorDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id,@RequestBody AuthorDto authorDto)
    {
        return this.authorService.edit(id,authorDto)
                .map(book->ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Author> delete(@PathVariable Long id)
    {
        this.authorService.delete(id);
        if(this.authorService.FindById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
