package com.lohika.book.storage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String genreName;

	public BookGenre() {
	}
	
	public BookGenre(Integer id, String genreName) {
		this.id = id;
		this.genreName = genreName;
	}
	
	public BookGenre(String genreName) {
		this.genreName = genreName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	

}
