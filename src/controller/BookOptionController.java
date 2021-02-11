package controller;

import exception.IncorrectFileNameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Books;
import model.Clients;
import model.Requests;
import model.Users;
import service.BookService;
import service.ClientService;
import service.RequestService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookOptionController {
    @FXML
    Label bookName, authorName, bookGenre, bookRating, bookStock;
    @FXML
    Button buttonReserve;
    @FXML
    Hyperlink hyperlinkPaginaInapoi;

    public void initialize(){
        Books books = getBook();
        if(books.getStoc() == 0){
            buttonReserve.setDisable(true);
        }
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
        RequestService requestService = new RequestService();
        requestService.addRequest(requests);

        try {
            buttonReserve.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../scene/CLIENT MENU.fxml"));
            Stage clientMenu = new Stage();
            clientMenu.setScene(new Scene(root));
            clientMenu.setTitle("CLIENT MENU");
            clientMenu.show();
        }catch (IncorrectFileNameException e){
            e.getMessage();
        }
    }

    public void inapoiAction() throws IOException {
        try {
            hyperlinkPaginaInapoi.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientMenu.fxml"));
            Stage paginaInapoi = new Stage();
            paginaInapoi.setTitle("CLIENT MENU");
            paginaInapoi.setScene(new Scene(root));
            paginaInapoi.show();
        }catch (IncorrectFileNameException e){
            e.getMessage();
        }
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
