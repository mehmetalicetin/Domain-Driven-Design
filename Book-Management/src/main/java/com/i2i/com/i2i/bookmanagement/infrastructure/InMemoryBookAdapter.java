package com.i2i.com.i2i.bookmanagement.infrastructure;

import com.i2i.com.i2i.bookmanagement.domain.Book;
import com.i2i.com.i2i.bookmanagement.domain.BookRepositoryPort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookAdapter implements BookRepositoryPort {
	private final List<Book> books = new ArrayList<>();

	public InMemoryBookAdapter() {
		// Dummy Data ile başlangıç yapıyoruz
		books.add(new Book(1L, "Design Patterns", "Erich Gamma"));
		books.add(new Book(2L, "Refactoring", "Martin Fowler"));
		books.add(new Book(3L, "Clean Code", "Robert C. Martin"));
	}

	@Override
	public List<Book> findAll() {
		return new ArrayList<>(books);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return books.stream().filter(book -> book.getId().equals(id)).findFirst();
	}

	@Override
	public void save(Book book) {
		books.add(book);
	}
}
