package com.example.bookshop.web;

import com.example.bookshop.models.Genre;
import com.example.bookshop.service.AuthorService;
import com.example.bookshop.service.BookService;
import org.springframework.stereotype.Controller;
import com.example.bookshop.models.Book;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String ListAllBooks(Model model,
                                @RequestParam(required = false)  String title,
                               @RequestParam(required = false) Genre genre
                               )
    {
        List<Book> books;
        if (title == null && genre == null ) {
            books = this.bookService.listAll();
        } else {
            books = this.bookService.filltered(title, genre);
        }
        model.addAttribute("title",title);
        model.addAttribute("genres",Genre.values());
        model.addAttribute("genre",genre);
        model.addAttribute("books",books);
        return "Books/listBooks";
    }

    @GetMapping("/add")
    public String ShowAdd(Model model)
    {

        model.addAttribute("authorId",authorService.listAll());
        model.addAttribute("genre",Genre.values());
        return "Books/form";
    }
    @PostMapping()
    public String AddRoom(@RequestParam String title,
                          @RequestParam String  description,
                          @RequestParam Integer price,
                          @RequestParam Long authorId,
                          @RequestParam Integer numCopies,
                          @RequestParam Boolean available,
                          @RequestParam Genre genre,
                          @RequestParam String image

                          )
    {
        bookService.save(title, description, price, authorId , numCopies, available, genre,image);
        return "redirect:/books";
    }

    @GetMapping("{id}/edit")
    public String showEdit(@PathVariable Long id, Model model)
    {
        model.addAttribute("book",bookService.findById(id));
        model.addAttribute("authorId",authorService.listAll());
        model.addAttribute("genre",Genre.values());
        return "Books/form";
    }
    @PostMapping("{id}")
    public String AddRoom(@PathVariable Long id,
                          @RequestParam String title,
                          @RequestParam String  description,
                          @RequestParam Integer price,
                          @RequestParam Long authorId,
                          @RequestParam Integer numCopies,
                          @RequestParam Boolean available,
                          @RequestParam Genre genre,
                          @RequestParam String image
   )
    {
        bookService.edit(id,title, description, price, authorId , numCopies, available, genre,image);
        return "redirect:/books";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        this.bookService.delete(id);
        return "redirect:/books";
    }
    @GetMapping("{id}/details")
    public String BookDetails(@PathVariable Long id,Model model)
    {
        model.addAttribute("book",bookService.findById(id));
        return "Books/bookDetails";
    }

    @PostMapping("{id}/rented")
    public String rented(@PathVariable Long id,Model model)
    {
        bookService.rent(id);
        model.addAttribute("book",bookService.findById(id));
        return "Books/bookDetails";
    }

}
