package com.lohika.book.storage.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model of Book entity.
 * 
 * @author vroman
 */
@Entity
@XmlRootElement
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String author;
    private String title;
    private Double price;
    private String fileName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_genre")
    private BookGenre bookGenre;

    public Book() {
    }

    public Book(String author, String title, Double price, BookGenre bookGenre) {
	this.author = author;
	this.title = title;
	this.price = price;
	this.bookGenre = bookGenre;
    }

    @Override
    public int hashCode() {
	return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
	return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this,
		ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public BookGenre getBookGenre() {
	return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
	this.bookGenre = bookGenre;
    }

    public final String getFileName() {
	return fileName;
    }

    public final void setFileName(String fileName) {
	this.fileName = fileName;
    }

}