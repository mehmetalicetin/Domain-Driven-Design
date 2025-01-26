package com.i2i.com.i2i.bookmanagement;

import com.i2i.com.i2i.bookmanagement.api.BookRestAdapter;
import com.i2i.com.i2i.bookmanagement.application.BookServicePort;
import com.i2i.com.i2i.bookmanagement.domain.BookRepositoryPort;
import com.i2i.com.i2i.bookmanagement.infrastructure.InMemoryBookAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookManagementApplication.class, args);
	}

	@Bean
	public BookRepositoryPort bookRepositoryPort() {
		return new InMemoryBookAdapter();
	}

	@Bean
	public BookServicePort bookServicePort(BookRepositoryPort repository) {
		return new BookServicePort(repository);
	}

	@Bean
	public BookRestAdapter bookRestAdapter(BookServicePort service) {
		return new BookRestAdapter(service);
	}
}
