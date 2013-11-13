package com.lohika.book.storage.model;

import com.lohika.book.storage.domain.Book;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vroman Model of Books collection
 */

@XmlRootElement
public class Books {

    private List<Book> bookCollection;

    public Books() {
    }

    public Books(List<Book> bookCollection) {
	this.bookCollection = bookCollection;
    }

    @XmlElement(name = "book")
    public List<Book> getBookCollection() {
	return bookCollection;
    }

    public void setBookCollection(List<Book> bookCollection) {
	this.bookCollection = bookCollection;
    }

    @Override
    public String toString() {
	return "Books{" + "bookCollection=" + bookCollection + '}';
    }
}
