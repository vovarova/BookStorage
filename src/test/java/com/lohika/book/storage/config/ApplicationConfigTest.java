package com.lohika.book.storage.config;

import static org.junit.Assert.*;

import java.util.Set;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.junit.Test;

import com.lohika.book.storage.service.BookService;

public class ApplicationConfigTest {

    @Test
    public void testGetClasses() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        Set<Class<?>> classes = applicationConfig.getClasses();
        assertNotNull(classes);
        assertTrue(classes.contains(MultiPartFeature.class));
        assertTrue(classes.contains(BookService.class));
        assertTrue(classes.contains(LoggingFilter.class));
    }
}
