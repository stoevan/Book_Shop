package com.example.bookshop.web;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.service.AuthorService;
import com.example.bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String ListAllAuthors(Model model,
                               @RequestParam(required = false)  String name

    )
    {
        List<Author> authors;
        if (name == null  ) {
            authors = this.authorService.listAll();
        } else {
            authors = this.authorService.filltered(name);
        }
        model.addAttribute("name",name);

        model.addAttribute("authors",authors);
        return "Authors/listAuthors";
    }
    @GetMapping("/add")
    public String ShowAdd(Model model)
    {


        return "Authors/form";
    }
    @PostMapping()
    public String AddRoom(@RequestParam String name,
                          @RequestParam String  surname,
                          @RequestParam String image


    )
    {
        authorService.save(name,surname,image);
        return "redirect:/authors";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable Long id, Model model)
    {
        model.addAttribute("author",authorService.findById(id));

        return "Authors/form";
    }
    @PostMapping("{id}")
    public String AddRoom(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam String  surname,
                          @RequestParam String image

    )
    {
        authorService.edit(id,name,surname,image);
        return "redirect:/authors";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        this.authorService.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("{id}/books")
    public String showBooksByAthor(@PathVariable Long id,Model model)
    {
        List<Book> books=bookService.getBookbyAuthorId(id);
        model.addAttribute("books",books);
        model.addAttribute("authorId",id);
        model.addAttribute("author",authorService.findById(id));
        return "Authors/booksList";
    }
}
