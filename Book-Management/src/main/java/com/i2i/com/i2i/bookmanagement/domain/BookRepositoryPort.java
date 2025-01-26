package com.i2i.com.i2i.bookmanagement.domain;

import java.util.List;
import java.util.Optional;

//Outbound Port
public interface BookRepositoryPort {
	List<Book> findAll();
	Optional<Book> findById(Long id);
	void save(Book book);
}
