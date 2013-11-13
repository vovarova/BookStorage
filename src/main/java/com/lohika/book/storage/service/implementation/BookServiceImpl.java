package com.lohika.book.storage.service.implementation;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.lohika.book.storage.service.BookService;
import com.lohika.book.storage.service.FileService;
import com.lohika.book.storage.dao.BooksDao;
import com.lohika.book.storage.domain.Book;
import com.lohika.book.storage.model.Books;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.*;

@Path("/book")
public class BookServiceImpl implements BookService {
    private BooksDao booksDao = new BooksDao();

    private FileService fileService = new FileServiceImpl();

    @Override
    public Book getById(Integer id) {
	return booksDao.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
	Book book = booksDao.getById(id);
	if (book != null) {
	    booksDao.removeEntity(book);
	    fileService.deleteFile(book);
	} else {
	    throw new BadRequestException("Cant find file for book with id "
		    + id);
	}
    }

    @Override
    public Book create(Book book) {
	booksDao.persistEntity(book);
	return book;
    }

    @Override
    public Book update(Book book) {
	booksDao.mergeEntity(book);
	return book;

    }

    @Override
    public Books getAll() {
	return new BooksDao().getAll();
    }

    @Override
    public Response downloadFile(Integer bookId) {
	Book book = booksDao.getById(bookId);
	if (book == null || book.getFileName() == null) {
	    throw new BadRequestException("Cant find file for book with id "
		    + bookId);
	} else {
	    File file = fileService.getFile(book);
	    ResponseBuilder responseBuilder = Response.ok();
	    responseBuilder.type(new MimetypesFileTypeMap()
		    .getContentType(file));
	    responseBuilder.header("content-disposition",
		    "attachment; filename = " + book.getFileName());
	    responseBuilder.entity(file);
	    return responseBuilder.build();
	}
    }

    @Override
    public Response uploadFile(Integer bookId, InputStream uploadedInputStream,
	    FormDataContentDisposition fileDetail) {
	Book book = booksDao.getById(bookId);
	if (book == null) {
	    throw new BadRequestException("Cant find book with id " + bookId);
	} else {
	    fileService.deleteFile(book);
	    String fileName = fileDetail.getFileName();
	    book.setFileName(fileName);
	    booksDao.mergeEntity(book);
	    fileService.saveFile(uploadedInputStream, fileName, book);
	    return Response.status(200).entity("File was successfully saved")
		    .build();
	}
    }

    @Override
    public Response deleteFile(Integer bookId) {
	Book book = booksDao.getById(bookId);
	if (book != null && book.getFileName() != null) {
	    throw new BadRequestException("Cant find book with id " + bookId);
	} else {
	    fileService.deleteFile(book);
	    book.setFileName(null);
	    booksDao.mergeEntity(book);
	    return Response.status(200).entity("File was successfully deleted")
		    .build();
	}
    }

    public BooksDao getBooksDao() {
	return booksDao;
    }

    public void setBooksDao(BooksDao booksDao) {
	this.booksDao = booksDao;
    }

    public FileService getFileService() {
	return fileService;
    }

    public void setFileService(FileService fileService) {
	this.fileService = fileService;
    }
}
