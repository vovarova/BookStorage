package com.lohika.book.storage.dao.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model of BookGenre entity.
 * 
 * @author vroman
 */
@Entity
public final class BookGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * Create {@link BookGenre} instance with default values
     */
    public BookGenre() {
    }

    /**
     * Create {@link Book} instance with transmitted parameters
     * 
     * @param id unique {@link BookGenre} identifier
     * @param name literature genre
     */
    public BookGenre(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Create {@link Book} instance with name,id will be assigned when entoty
     * will be persisted
     * 
     * @param name literature genre
     */
    public BookGenre(final String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
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
