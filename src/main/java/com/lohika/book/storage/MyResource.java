package com.lohika.book.storage;

import com.lohika.book.storage.dao.DaoBase;
import com.lohika.book.storage.model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("data")
public class MyResource {

    @GET
    @Path("/number")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        System.out.println("A");
        DaoBase daoBase = new DaoBase();
        daoBase.persistEntity(new Book("vova","rova",12d,"sasas"));
        String someString = "Got it !";
        return someString;
    }
}
