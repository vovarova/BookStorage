package integration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lohika.book.storage.dao.DomainManagerFactory;
import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.service.BookService;
import com.lohika.book.storage.service.implementation.BookServiceImpl;

public class BookServiceIntegrationTest extends JerseyTest {
    private WebTarget baseTarget = target().path("book");

    @Before
    public void initializeDomainManagerFactory() {
        EntityManagerFactory createEntityManagerFactory = Persistence
                .createEntityManagerFactory("persistenceTest");
        DomainManagerFactory
                .setEntityManagerFactory(createEntityManagerFactory);
    }

    @After
    public void closeDomainManagerFactory() {
        new DomainManagerFactory().contextDestroyed(null);
    }

    @Override
    protected Application configure() {
        ResourceConfig resourceConfig = new ResourceConfig(BookService.class,
                MultiPartFeature.class, BookServiceImpl.class,
                LoggingFilter.class, Logger.class);
        return resourceConfig;
    }

    @Override
    public TestContainerFactory getTestContainerFactory() {
        return new GrizzlyTestContainerFactory();

    }

    private Book createBook(Book book, MediaType mediaType) {
        Builder request = baseTarget.request();
        request.accept(mediaType);
        return request.post(Entity.entity(book, mediaType), Book.class);
    }

    private Book updateBook(Book book, MediaType mediaType) {
        Builder request = baseTarget.request();
        request.accept(mediaType);
        return request.put(Entity.entity(book, mediaType), Book.class);
    }

    private Book getBookById(Integer id, MediaType mediaType) {
        Builder request = baseTarget.path(String.valueOf(id)).request();
        request.accept(mediaType);
        return request.get(Book.class);
    }

    private Response deleteBookById(Integer id) {
        return baseTarget.path(String.valueOf(id)).request()
                .delete(Response.class);
    }

    private void commonBookScenario(MediaType mediaType) {
        Book book = new Book("Author", "title", 12d, null);
        Book createdBook = createBook(book, mediaType);
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());
        Book bookById = getBookById(createdBook.getId(), mediaType);
        Book incorectBookById = getBookById(-1, mediaType);
        assertNull(incorectBookById);
        assertNotNull(bookById);
        assertEquals(createdBook, bookById);
        bookById.setAuthor("New Author");
        updateBook(bookById, mediaType);
        assertEquals(bookById, getBookById(bookById.getId(), mediaType));
        deleteBookById(bookById.getId());
    }

    @Test
    public void test() {
        commonBookScenario(MediaType.APPLICATION_JSON_TYPE);
        commonBookScenario(MediaType.APPLICATION_XML_TYPE);
    }
}
