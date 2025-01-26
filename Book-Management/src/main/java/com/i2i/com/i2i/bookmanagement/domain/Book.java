package com.i2i.com.i2i.bookmanagement.domain;

//# Çekirdek iş mantığı
public class Book {
	private Long id;
	private String title;
	private String author;

	public Book(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
}
