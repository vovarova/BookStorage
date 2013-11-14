package com.lohika.book.storage.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.lohika.book.storage.service.BookService;

/**
 * Application configs to use MultiPartFeatures.
 * 
 * @author: vroman
 */
public class ApplicationConfig extends Application {
    @Override
    public final Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features
        classes.add(MultiPartFeature.class);
        classes.add(BookService.class);
        classes.add(LoggingFilter.class);
        return classes;
    }
}
