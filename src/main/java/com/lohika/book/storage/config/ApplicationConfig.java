package com.lohika.book.storage.config;

import com.lohika.book.storage.api.BookServices;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: vroman
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register resources and features
        classes.add(MultiPartFeature.class);
        classes.add(BookServices.class);
        classes.add(LoggingFilter.class);
        return classes;
    }
}
