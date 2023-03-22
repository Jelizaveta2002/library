package com.cgi.library.service;

import com.cgi.library.entity.Book;
import com.cgi.library.model.BookDTO;
import com.cgi.library.model.BookStatus;
import com.cgi.library.repository.BookRepository;
import com.cgi.library.util.ModelMapperFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private static final int PAGE_SIZE = 2;

    public Page<BookDTO> getBooks(int page, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, sort);
        return bookRepository.findAll(pageable).map(book -> modelMapper.map(book, BookDTO.class));
    }

    public List<BookDTO> searchBookByTitle(String title, int page) {
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        List<BookDTO> bookDTOs = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        List<Book> books = bookRepository.findBookByTitleContainingIgnoreCase(title, pageable);
        for (Book book : books) {
            bookDTOs.add(modelMapper.map(book, BookDTO.class));
        }
        return bookDTOs;
    }

    public List<BookDTO> searchBookByStatus(BookStatus status, int page) {
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        List<BookDTO> bookDTOs = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        List<Book> books = bookRepository.findBookByStatus(status, pageable);
        for (Book book : books) {
            bookDTOs.add(modelMapper.map(book, BookDTO.class));
        }
        return bookDTOs;
    }

    public void updateBookStatus(UUID id) {
        Book book = bookRepository.getOne(id);
        book.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(book);
    }

    public BookDTO getBook(UUID bookId) {
        Book book = bookRepository.getOne(bookId);
        return ModelMapperFactory.getMapper().map(book, BookDTO.class);
    }

    public UUID saveBook(BookDTO bookDTO) {
        ModelMapper modelMapper = ModelMapperFactory.getMapper();
        return bookRepository.save(modelMapper.map(bookDTO, Book.class)).getId();
    }

    public void deleteBook(UUID bookId) {
        bookRepository.deleteById(bookId);
    }
}
