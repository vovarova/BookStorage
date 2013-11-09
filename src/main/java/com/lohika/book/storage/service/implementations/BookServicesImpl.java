package com.lohika.book.storage.service.implementations;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.lohika.book.storage.api.BookServices;
import com.lohika.book.storage.dao.BooksDao;
import com.lohika.book.storage.model.Book;
import com.lohika.book.storage.model.Books;
import com.lohika.book.storage.service.implementations.file.FileManager;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.*;

@Path("/book")
public class BookServicesImpl implements BookServices {
private BooksDao booksDao=new BooksDao();
private FileManager fileManager=FileManager.getInstance();
@Override
    public Book getBookById(Integer id) {
        Book book = booksDao.getBookById(id);
        return book;
    }

    @Override
    public void deleteBookById(Integer id) {
        Book book = booksDao.getBookById(id);
        if (book != null) {
            booksDao.removeEntity(book);
            String fileLocation = book.getFileLocation();
            if(fileLocation!=null){
				fileManager.deleteFile(fileLocation);
			}
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
    	if(book==null || book.getFileLocation()==null){  		
    		throw new BadRequestException("Cant find file for book with id "+bookId);    		
    	}else{    		
    		File file = fileManager.getFile(book.getFileLocation());    		
    		ResponseBuilder responseBuilder = Response.ok();
    		responseBuilder.type(new MimetypesFileTypeMap().getContentType(file));
    		responseBuilder.header("content-disposition","attachment; filename = "+file.getName());
    		responseBuilder.entity(file);
    		return responseBuilder.build();
    	}
    }

    @Override
    public Response uploadBookFile(Integer bookId, InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
        Book book = booksDao.getBookById(bookId);
        if(book==null){
        	throw new BadRequestException("Cant find book with id "+bookId);
        }else{
        	String fileLocation = book.getFileLocation();
			if(fileLocation!=null && !fileLocation.isEmpty()){
				fileManager.deleteFile(fileLocation);
			}
			String fileName = fileDetail.getFileName();
			book.setFileLocation(fileName);
			booksDao.mergeEntity(book);
			fileManager.saveFile(uploadedInputStream,fileName);
			return Response.status(200).entity("File was successfully saved").build();
        }
    }
}
