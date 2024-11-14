package com.example.bookshop.service.specification;

import com.example.bookshop.models.Author;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Genre;
import org.springframework.data.jpa.domain.Specification;

public class AuthorSpecification {
    public static Specification<Author> buildSpecification(String Name) {
        return Specification.where(withName(Name));


    }

    private static Specification<Author> withName(String Name) {
        if (Name == null || Name.isEmpty()) {
            return null;
        }
        return ((root, query, cb) -> cb.like(cb.lower(root.get("Name")), "%" + Name.toLowerCase() + "%"));
    }
}
