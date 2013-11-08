package com.lohika.book.storage.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vroman
 * Model of Book entity
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", bookGenre=" + bookGenre +
                '}';
    }
}