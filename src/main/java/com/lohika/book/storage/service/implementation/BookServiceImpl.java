package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.lohika.book.storage.dao.BookDao;
import com.lohika.book.storage.dao.domain.Book;
import com.lohika.book.storage.model.Books;
import com.lohika.book.storage.service.BookService;
import com.lohika.book.storage.service.FileService;

/**
 * Book Service implementation with root path /book all jax-rs annotations will
 * be inherited
 */
@Path("/book")
public class BookServiceImpl implements BookService {

    private BookDao booksDao = new BookDao();
    private FileService fileService = new FileServiceImpl();

    @Override
    @GET
    @Path("{id}")
    public final Book getById(final @PathParam(value = "id") Integer id) {
        return booksDao.getById(id);
    }

    @Override
    @DELETE
    @Path("{id}")
    public final void deleteById(final @PathParam(value = "id") Integer id) {
        final Book book = booksDao.getById(id);
        if (book != null) {
            booksDao.removeEntity(book);
            fileService.deleteFile(book);
        } else {
            throw new NotFoundException("Cant find file for book with id " + id);
        }
    }

    @Override
    @POST
    @Path("/")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public final Book create(final Book book) {
        booksDao.persistEntity(book);
        return book;
    }

    @Override
    @PUT
    @Path("/")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public final Book update(final Book book) {
        booksDao.mergeEntity(book);
        return book;

    }

    @Override
    @GET
    @Path("all")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public final Books getAll() {
        List<Book> all = new BookDao().getAll();
        return new Books(all);
    }

    @Override
    @GET
    @Path("file/{bookId}")
    public final Response downloadFile(
            final @PathParam(value = "bookId") Integer bookId) {
        final Book book = booksDao.getById(bookId);
        if (book == null || book.getFileName() == null) {
            throw new NotFoundException("Cant find file for book with id "
                    + bookId);
        } else {
            final File file = fileService.getFile(book);
            final ResponseBuilder responseBuilder = Response.ok();
            responseBuilder.type(new MimetypesFileTypeMap()
                    .getContentType(file));
            responseBuilder.header("content-disposition",
                    "attachment; filename = " + book.getFileName());
            responseBuilder.entity(file);
            return responseBuilder.build();
        }
    }

    @Override
    @POST
    @Path("file/{bookId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public final Response uploadFile(
            final @PathParam(value = "bookId") Integer bookId,
            final @FormDataParam("file") InputStream uploadedInputStream,
            final @FormDataParam("file") FormDataContentDisposition fileDetail) {
        final Book book = booksDao.getById(bookId);
        if (book == null) {
            throw new NotFoundException("Cant find book with id " + bookId);
        } else {
            fileService.deleteFile(book);
            final String fileName = fileDetail.getFileName();
            book.setFileName(fileName);
            booksDao.mergeEntity(book);
            fileService.saveFile(uploadedInputStream, fileName, book);
            return Response.status(200).entity("File was successfully saved")
                    .build();
        }
    }

    @Override
    @DELETE
    @Path("file/{bookId}")
    public final Response deleteFile(
            final @PathParam(value = "bookId") Integer bookId) {
        final Book book = booksDao.getById(bookId);
        if (book == null || book.getFileName() == null) {
            throw new NotFoundException("Cant find file for book " + bookId);
        } else {
            fileService.deleteFile(book);
            book.setFileName(null);
            booksDao.mergeEntity(book);
            return Response.status(200).entity("File was successfully deleted")
                    .build();
        }
    }

    public final BookDao getBooksDao() {
        return booksDao;
    }

    public final void setBooksDao(final BookDao booksDao) {
        this.booksDao = booksDao;
    }

    public final FileService getFileService() {
        return fileService;
    }

    public final void setFileService(final FileService fileService) {
        this.fileService = fileService;
    }
}
