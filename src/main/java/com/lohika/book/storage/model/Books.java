package com.lohika.book.storage.model;

import com.lohika.book.storage.dao.domain.Book;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author vroman Model of Books collection
 */
@XmlRootElement
public class Books {

    private List<Book> bookCollection;

    /**
     * Create {@link Books} instance with default values
     */
    public Books() {
    }

    /**
     * Create {@link Books} instance with transmitted parameters
     * 
     * @param bookCollection collection of book instances
     */
    public Books(final List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @XmlElement(name = "book")
    public final List<Book> getBookCollection() {
        return bookCollection;
    }

    public final void setBookCollection(final List<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }

    @Override
    public final String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
