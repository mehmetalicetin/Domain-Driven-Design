package com.i2i.com.i2i.bookmanagement.domain;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class BookTest extends TestCase {

	@Test
	void testBookCreation() {
		Book book = new Book(1L, "Effective Java", "Joshua Bloch");

		assertEquals(Optional.of(1L).get(), book.getId());
		assertEquals("Effective Java", book.getTitle());
		assertEquals("Joshua Bloch", book.getAuthor());
	}
}