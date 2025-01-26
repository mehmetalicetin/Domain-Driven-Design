package com.i2i.com.i2i.bookmanagement.api;

import com.i2i.com.i2i.bookmanagement.application.BookServicePort;
import com.i2i.com.i2i.bookmanagement.domain.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookRestAdapter {
	private final BookServicePort bookServicePort;

	public BookRestAdapter(BookServicePort bookServicePort) {
		this.bookServicePort = bookServicePort;
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookServicePort.getAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
		return bookServicePort.getBookById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public void addBook(@RequestBody Book book) {
		bookServicePort.addBook(book);
	}
}
