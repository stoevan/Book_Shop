package com.example.bookshop.repository;

import com.example.bookshop.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuthorRepository extends JpaRepository<Author,Long>,JpaSpecificationExecutor {
}
