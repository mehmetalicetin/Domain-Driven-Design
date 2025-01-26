package com.i2i.com.i2i.bookmanagement.infrastructure;

import com.i2i.com.i2i.bookmanagement.domain.Book;
import com.i2i.com.i2i.bookmanagement.domain.BookRepositoryPort;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class InMemoryBookAdapterTest extends TestCase {
	private BookRepositoryPort bookRepositoryPort;

	@BeforeEach
	void before() {
		bookRepositoryPort = new InMemoryBookAdapter();
	}

	@Test
	void testFindAllBooks() {
		List<Book> books = bookRepositoryPort.findAll();
		assertEquals(3, books.size());
	}

	@Test
	void testFindBookById() {
		Optional<Book> book = bookRepositoryPort.findById(1L);
		assertTrue(book.isPresent());
		assertEquals("Design Patterns", book.get().getTitle());
	}

	@Test
	void testSaveNewBook() {
		Book newBook = new Book(4L, "Domain-Driven Design", "Eric Evans");
		bookRepositoryPort.save(newBook);

		Optional<Book> savedBook = bookRepositoryPort.findById(4L);
		assertTrue(savedBook.isPresent());
		assertEquals("Domain-Driven Design", savedBook.get().getTitle());
	}
}