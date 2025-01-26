package com.i2i.com.i2i.bookmanagement.api;

import com.i2i.com.i2i.bookmanagement.application.BookServicePort;
import com.i2i.com.i2i.bookmanagement.domain.Book;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestAdapter.class)
class BookRestAdapterTest extends TestCase {
	@Autowired
	private MockMvc         mockMvc;

	@MockitoBean
	private BookServicePort bookServicePort;

	@BeforeEach
	void before() {
		List<Book> books = new ArrayList<>();
		Book designPattern = new Book(1L, "Design Patterns", "Erich Gamma");
		Book refactoring = new Book(2L, "Refactoring", "Martin Fowler");
		books.add(designPattern);
		books.add(refactoring);

		when(bookServicePort.getAllBooks()).thenReturn(books);
		when(bookServicePort.getBookById(1L)).thenReturn(Optional.of(designPattern));
	}

	@Test
	void testGetAllBooks() throws Exception {
		mockMvc.perform(get("/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].title").value("Design Patterns"))
				.andExpect(jsonPath("$[1].title").value("Refactoring"));
	}

	@Test
	void testGetBookById() throws Exception {
		mockMvc.perform(get("/books/{id}", 1) // Hata almamak için path variable ile ID geçiyoruz
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.title").value("Design Patterns"))
				.andExpect(jsonPath("$.author").value("Erich Gamma"));
	}

	@Test
	void testGetBookById_NotFound() throws Exception {
		when(bookServicePort.getBookById(99L)).thenReturn(Optional.empty()); // ID = 99 olmayan bir kitap için mock

		mockMvc.perform(get("/books/{id}", 99)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}