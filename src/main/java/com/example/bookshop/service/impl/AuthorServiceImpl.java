package com.example.bookshop.service.impl;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Dto.AuthorDto;
import com.example.bookshop.models.exceptions.InvalidAuthorId;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.service.AuthorService;
import com.example.bookshop.service.specification.AuthorSpecification;
import com.example.bookshop.service.specification.BookSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(InvalidAuthorId::new);
    }

    @Override
    public Optional<Author> FindById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(String name, String surname,String image) {
        Author author=new Author(name,surname,image);
        authorRepository.save(author);
        return author;
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Author author=new Author(authorDto.getName(),authorDto.getSurname(),authorDto.getImage());
        authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Author edit(Long id, String name, String surname,String image) {
        Author author=authorRepository.findById(id).orElseThrow(InvalidAuthorId::new);
        author.setName(name);
        author.setSurname(surname);
        author.setImage(image);
        authorRepository.save(author);
        return author;
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author=authorRepository.findById(id).orElseThrow(InvalidAuthorId::new);
        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setImage(authorDto.getImage());
        authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void delete(Long id) {
        Author author=authorRepository.findById(id).orElseThrow(InvalidAuthorId::new);
        authorRepository.delete(author);
    }

    @Override
    public List<Author> filltered(String Name) {
        Specification<Author> spec = AuthorSpecification.buildSpecification(Name);



        return authorRepository.findAll(spec);
    }
}
