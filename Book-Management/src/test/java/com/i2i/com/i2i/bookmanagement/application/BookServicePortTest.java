package com.i2i.com.i2i.bookmanagement.application;

import com.i2i.com.i2i.bookmanagement.domain.Book;
import com.i2i.com.i2i.bookmanagement.domain.BookRepositoryPort;
import com.i2i.com.i2i.bookmanagement.infrastructure.InMemoryBookAdapter;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class BookServicePortTest extends TestCase {
	private BookServicePort    bookServicePort;
	private BookRepositoryPort bookRepositoryPort;

	@BeforeEach
	void before() {
		bookRepositoryPort = new InMemoryBookAdapter();
		bookServicePort = new BookServicePort(bookRepositoryPort);
	}

	@Test
	void testGetAllBooks() {
		List<Book> books = bookServicePort.getAllBooks();
		assertFalse(books.isEmpty());
	}

	@Test
	void testGetBookById() {
		Optional<Book> book = bookServicePort.getBookById(1L);
		assertTrue(book.isPresent());
		assertEquals("Design Patterns", book.get().getTitle());
	}

	@Test
	void testAddBook() {
		Book newBook = new Book(4L, "The Pragmatic Programmer", "Andy Hunt");
		bookServicePort.addBook(newBook);

		Optional<Book> savedBook = bookServicePort.getBookById(4L);
		assertTrue(savedBook.isPresent());
		assertEquals("The Pragmatic Programmer", savedBook.get().getTitle());
	}
}