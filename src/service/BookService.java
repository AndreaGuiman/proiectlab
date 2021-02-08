package service;

import dao.BookDao;
import model.Books;

import javax.persistence.Persistence;
import java.util.List;

public class BookService {
    private BookDao bookDao;

    public BookService(){
        try{
            bookDao = new BookDao(Persistence.createEntityManagerFactory("proiectmip"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Books> getAllBooks(){
        return bookDao.findAll();
    }

    public Books findByBookName(String bookName){
        return bookDao.findByBookName(bookName);
    }
}
