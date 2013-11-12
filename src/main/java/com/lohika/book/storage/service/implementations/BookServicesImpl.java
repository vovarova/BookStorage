package com.lohika.book.storage.service.implementations;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.lohika.book.storage.api.BookServices;
import com.lohika.book.storage.api.FileServices;
import com.lohika.book.storage.dao.BooksDao;
import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.*;

@Path("/book")
public class BookServicesImpl implements BookServices {
    private BooksDao booksDao = new BooksDao();
    private FileServices fileServices = new FileServicesImpl();

    @Override
    public Book getBookById(Integer id) {
        return booksDao.getBookById(id);
    }

    @Override
    public void deleteBookById(Integer id) {
        Book book = booksDao.getBookById(id);
        if (book != null) {
            booksDao.removeEntity(book);
            fileServices.deleteFullFilePath(book);
        }else{
        	throw new BadRequestException("Cant find file for book with id " + id);
        }
    }

    @Override
    public Book createBook(Book book) {
        booksDao.persistEntity(book);
        return book;
    }

    @Override
    public Book updateBook(Book book) {
        booksDao.mergeEntity(book);
        return book;

    }

    @Override
    public Books getAllBooks() {
        return new BooksDao().getAllBooks();
    }

    @Override
    public Response downloadBookFile(Integer bookId) {
        Book book = booksDao.getBookById(bookId);
        if (book == null || book.getFileName() == null) {
            throw new BadRequestException("Cant find file for book with id " + bookId);
        } else {
            File file = fileServices.getFile(book);
            ResponseBuilder responseBuilder = Response.ok();
            responseBuilder.type(new MimetypesFileTypeMap().getContentType(file));
            responseBuilder.header("content-disposition", "attachment; filename = " + file.getName());
            responseBuilder.entity(file);
            return responseBuilder.build();
        }
    }

    @Override
    public Response uploadBookFile(Integer bookId, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
        Book book = booksDao.getBookById(bookId);
        if (book == null) {
            throw new BadRequestException("Cant find book with id " + bookId);
        } else {
            fileServices.deleteFile(book);
            String fileName = fileDetail.getFileName();
            book.setFileName(fileName);
            booksDao.mergeEntity(book);
            fileServices.saveFile(uploadedInputStream, fileName, book);
            return Response.status(200).entity("File was successfully saved").build();
        }
    }

    @Override
    public Response deleteBookFile(Integer bookId) {
        Book book = booksDao.getBookById(bookId);
        if(book!=null && book.getFileName()!=null){
        	throw new BadRequestException("Cant find book with id " + bookId);
        }else{        	
        	fileServices.deleteFullFilePath(book);
        	book.setFileName(null);
        	booksDao.mergeEntity(book);
        	return Response.status(200).entity("File was successfully deleted").build();
        }
    }

	public BooksDao getBooksDao() {
		return booksDao;
	}

	public void setBooksDao(BooksDao booksDao) {
		this.booksDao = booksDao;
	}

	public FileServices getFileServices() {
		return fileServices;
	}

	public void setFileServices(FileServices fileServices) {
		this.fileServices = fileServices;
	}
}
