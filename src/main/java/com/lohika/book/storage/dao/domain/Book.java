package com.lohika.book.storage.dao.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model of Book entity.
 * 
 * @author vroman
 */
@Entity
@XmlRootElement
public final class Book {

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

    /**
     * Create {@link Book} instance with default values
     */
    public Book() {
    }

    /**
     * Create {@link Book} instance with transmitted parameters
     * 
     * @param author book author
     * @param title book title
     * @param price price of book
     * @param bookGenre {@link BookGenre}
     */
    public Book(final String author, final String title, final Double price,
            final BookGenre bookGenre) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.bookGenre = bookGenre;
    }

    /**
     * Clone {@link Book} instance
     * 
     * @param book entity to clone
     */
    public Book(final Book book) {
        this.id = book.id;
        this.author = book.author;
        this.title = book.title;
        this.price = book.price;
        if (book.getBookGenre() != null) {
            this.bookGenre = new BookGenre(book.getBookGenre());
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(final BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}