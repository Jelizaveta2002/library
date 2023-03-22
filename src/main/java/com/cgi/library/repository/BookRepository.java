package com.cgi.library.repository;

import com.cgi.library.entity.Book;
import com.cgi.library.model.BookStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findBookByTitleContainingIgnoreCase(String title, Pageable pageable);

    List<Book> findBookByStatus(BookStatus status, Pageable pageable);

}
