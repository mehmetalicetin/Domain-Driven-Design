package com.i2i.com.i2i.bookmanagement.application;

import com.i2i.com.i2i.bookmanagement.domain.Book;
import com.i2i.com.i2i.bookmanagement.domain.BookRepositoryPort;

import java.util.List;
import java.util.Optional;

//Inbound Port
public class BookServicePort {
	private final BookRepositoryPort bookRepositoryPort;

	public BookServicePort(BookRepositoryPort bookRepositoryPort) {
		this.bookRepositoryPort = bookRepositoryPort;
	}

	public List<Book> getAllBooks() {
		return bookRepositoryPort.findAll();
	}

	public Optional<Book> getBookById(Long id) {
		return bookRepositoryPort.findById(id);
	}

	public void addBook(Book book) {
		bookRepositoryPort.save(book);
	}
}
