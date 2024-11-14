package com.example.bookshop.service.impl;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Dto.BookDto;
import com.example.bookshop.models.Genre;
import com.example.bookshop.models.exceptions.InvalidAuthorId;
import com.example.bookshop.models.exceptions.InvalidBookId;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.specification.BookSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookId::new);
    }

    @Override
    public Optional<Book> FindById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(String title, String description, Integer price, Long authorId, Integer numCopies, Boolean available, Genre genre,String image) {
        Author author=authorRepository.findById(authorId).orElseThrow();
        Book book=new Book(title,description,price,author,numCopies,available,genre,image);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthor()).orElseThrow(InvalidAuthorId::new);
        Book book=new Book(bookDto.getTitle(),bookDto.getDescription(),bookDto.getPrice(),author,bookDto.getNumCopies(),bookDto.getAvailable(),bookDto.getGenre(),bookDto.getImage());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Book edit(Long id, String title, String description, Integer price, Long authorId, Integer numCopies, Boolean available, Genre genre,String image) {
        Book book=bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        Author author=authorRepository.findById(authorId).orElseThrow();
        book.setTitle(title);
        book.setDescription(description);
        book.setPrice(price);
        book.setAuthor(author);
        book.setNumCopies(numCopies);
        book.setAvailable(available);
        book.setGenre(genre);
        book.setImage(image);

        bookRepository.save(book);
        return book;
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthor()).orElseThrow(InvalidAuthorId::new);
        Book book=bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setAuthor(author);
        book.setPrice(bookDto.getPrice());
        book.setGenre(bookDto.getGenre());
        book.setImage(bookDto.getImage());
        book.setAvailable(bookDto.getAvailable());
        book.setNumCopies(bookDto.getNumCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void delete(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        bookRepository.delete(book);
    }
    @Override
    public List<Book> filltered(String Title, Genre genre)
    {
        Specification<Book> spec = BookSpecification.buildSpecification(Title,genre);



        return bookRepository.findAll(spec);
    }

    @Override
    public List<Book> getBookbyAuthorId(Long authorId) {
        return bookRepository.findBookByAuthorId(authorId);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book book=bookRepository.findById(id).orElseThrow(InvalidBookId::new);
        int count=book.getNumCopies();
        book.setNumCopies(--count);
        bookRepository.save(book);
        return Optional.of(book);
    }
}
