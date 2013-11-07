package com.lohika.book.storage.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: vroman
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
    private String contentUrl;

    public Book() {
    }

    public Book(String author, String title, Double price, String contentUrl) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.contentUrl = contentUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
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

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", contentUrl='" + contentUrl + '\'' +
                '}';
    }
}