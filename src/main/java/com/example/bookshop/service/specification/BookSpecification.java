package com.example.bookshop.service.specification;
import com.example.bookshop.models.Book;
import com.example.bookshop.models.Genre;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> buildSpecification(String Title,
                                                         Genre genre
                                                         ) {
        return Specification.where(withTtile(Title))
                .and(withGenre(genre));


    }

    private static Specification<Book> withTtile(String Title) {
        if (Title == null || Title.isEmpty()) {
            return null;
        }
        return ((root, query, cb) -> cb.like(cb.lower(root.get("Title")), "%" + Title.toLowerCase() + "%"));
    }
    private static Specification<Book> withGenre(Genre genre) {
        if (genre == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("genre"), genre);
    }







}
