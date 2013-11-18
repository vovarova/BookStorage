package com.lohika.book.storage.service.implementation;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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
    public final Book getById(final Integer id) {
        return booksDao.getById(id);
    }

    @Override
    public final void deleteById(final Integer id) {
        final Book book = booksDao.getById(id);
        if (book != null) {
            booksDao.removeEntity(book);
            fileService.deleteFile(book);
        } else {
            throw new NotFoundException("Cant find file for book with id " + id);
        }
    }

    @Override
    public final Book create(final Book book) {
        booksDao.persistEntity(book);
        return book;
    }

    @Override
    public final Book update(final Book book) {
        booksDao.mergeEntity(book);
        return book;

    }

    @Override
    public final Books getAll() {
        List<Book> all = new BookDao().getAll();
        return new Books(all);
    }

    @Override
    public final Response downloadFile(final Integer bookId) {
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
    public final Response uploadFile(final Integer bookId,
            final InputStream uploadedInputStream,
            final FormDataContentDisposition fileDetail) {
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
    public final Response deleteFile(final Integer bookId) {
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
