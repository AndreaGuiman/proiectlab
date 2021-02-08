package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Books;
import model.Clients;
import model.Requests;
import model.Users;
import service.BookService;
import service.ClientService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class BookOptionController {
    @FXML
    Label bookName, authorName, bookGenre, bookRating, bookStock;
    public void initialize(){
        //System.out.println(getBook());
        Books books = getBook();
        bookName.setText(books.getBookName());
        authorName.setText(books.getAuthorName());
        bookGenre.setText(books.getBookGenre());
        bookRating.setText(books.getBookRating().toString());
        bookStock.setText(books.getStoc().toString());
    }

    public void reserveAction() throws Exception {
        Books books = getBook();
        Clients clients = getClient();
        Requests requests = new Requests();

        Date date = Date.valueOf(LocalDate.now());
        LocalDate dateForReturn  = LocalDate.now().plus(2, ChronoUnit.WEEKS);
        Date dateForReturnSql = Date.valueOf(dateForReturn);
        requests.setIdBook(books.getIdBook());
        requests.setIdClient(clients.getIdClient());
        requests.setStartDate(date);
        requests.setEndDate(dateForReturnSql);
        System.out.println(requests);
    }

    public String getBookNameFromFile(){
        String bookName = null;
        try(BufferedReader br = new BufferedReader(new FileReader("src/session/BookSession.txt"))){
            String bookNameTmp;
            while((bookNameTmp = br.readLine()) != null)
                bookName = bookNameTmp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bookName;
    }

    public Books getBook(){
        BookService bookService = new BookService();
        return bookService.findByBookName(getBookNameFromFile());
    }


    public String getUsernameClient(){
        String username = null;
        try(BufferedReader br = new BufferedReader(new FileReader("src/session/Session.txt"))){
            String usernameTmp;
            while((usernameTmp = br.readLine()) != null)
                username = usernameTmp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return username;
    }

    public Clients getClient() throws Exception {
        String usernameTmp = getUsernameClient();
        UserService userService = new UserService();
        Users users = userService.findByUsername(usernameTmp);
        ClientService clientService = new ClientService();
        return clientService.findById(users.getIdClient());
    }
}
