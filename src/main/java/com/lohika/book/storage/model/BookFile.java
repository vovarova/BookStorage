package com.lohika.book.storage.model;

import javax.persistence.*;

/**
 * @author: vroman
 * Model to store information about book file
 * */

@Entity
public class BookFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;

    private String bookFileUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBookFileUrl() {
        return bookFileUrl;
    }

    public void setBookFileUrl(String bookFileUrl) {
        this.bookFileUrl = bookFileUrl;
    }
}
