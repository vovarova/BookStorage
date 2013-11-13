package com.lohika.book.storage.config;

import com.lohika.book.storage.service.BookService;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Application configs to use MultiPartFeatures.
 * 
 * @author: vroman
 */
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
	final Set<Class<?>> classes = new HashSet<Class<?>>();
	// register resources and features
	classes.add(MultiPartFeature.class);
	classes.add(BookService.class);
	classes.add(LoggingFilter.class);
	return classes;
    }
}
