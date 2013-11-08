package com.lohika.book.storage.dao;

import com.lohika.book.storage.model.BookFile;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author: vroman
 * Declare BookFiles Dao functionality
 */
public class BookFileDao extends  BaseDao<BookFile> {
     public BookFile getBookFileByBookId(Integer bookId){
         TypedQuery<BookFile> createQuery = getEntityManager().createQuery("select bf from BookFile bf where bf.book.id=?1", BookFile.class);
         List<BookFile> resultList = createQuery.getResultList();
         if(resultList==null || resultList.isEmpty()){
             return null;
         }else{
             return resultList.get(0);
         }
     }

}
