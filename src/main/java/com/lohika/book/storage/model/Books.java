package com.lohika.book.storage.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Books {
	private List<Book> bookCollection;
	
	@XmlElement(name="book")
	public List<Book> getBookCollection() {
		return bookCollection;
	}

	public void setBookCollection(List<Book> bookCollection) {
		this.bookCollection = bookCollection;
	}
		
}
