package com.cgi.library.controller;

import com.cgi.library.model.BookDTO;
import com.cgi.library.model.BookStatus;
import com.cgi.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = "getBooks")
    public ResponseEntity<Page<BookDTO>> getBooks(int page, String sortBy) {
        return ResponseEntity.ok(bookService.getBooks(page, sortBy));
    }

    @GetMapping("search")
    public List<BookDTO> searchBooksByTitle(@RequestParam int page, String title) {
        return bookService.searchBookByTitle(title, page);
    }

    @GetMapping("updateStatus")
    public void updateStatus(@RequestParam UUID id) {
        bookService.updateBookStatus(id);
    }

    @GetMapping("searchByStatus")
    public List<BookDTO> searchBooksByStatus(@RequestParam BookStatus status, int page) {
        return bookService.searchBookByStatus(status, page);
    }

    @GetMapping("searchByMultiple")
    public List<BookDTO> searchBooksByMultipleCriteria(@RequestParam String title, String author, BookStatus status, int page) {
        return bookService.searchBookByMultipleCriteria(title, author,status, page);
    }

    @GetMapping(value = "getBook")
    public ResponseEntity<BookDTO> getBook(@RequestParam(value = "bookId") UUID bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PostMapping(value = "saveBook")
    public ResponseEntity<String> saveBook(@RequestBody BookDTO book) {
        return ResponseEntity.ok(String.valueOf(bookService.saveBook(book)));
    }

    @DeleteMapping(value = "deleteBook")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "bookId") UUID bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("");
    }
}
