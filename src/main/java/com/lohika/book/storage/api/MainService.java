package com.lohika.book.storage.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author vroman
 *         Test service
 */
@Path("/")
public class MainService {
    @GET
    public String getIndexPage() {
        return "Hello World";
    }

}
