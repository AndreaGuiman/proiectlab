package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Books;
import model.Clients;
import model.Requests;
import model.Users;
import service.BookService;
import service.ClientService;
import service.RequestService;
import service.UserService;
import wrapper.ClientAccountWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientAccountController {
    @FXML
    Hyperlink mainMenu;
    @FXML
    Label username, firstName, lastName, email,telNo;
    @FXML
    TableView<ClientAccountWrapper> tableRequests;
    @FXML
    TableColumn<ClientAccountWrapper, String> bookNameColumn, authorNameColumn;
    @FXML
    TableColumn<ClientAccountWrapper, Date> dateBorrowed, dateReturn;

    public void initialize() throws Exception {
        Clients clients = getClient();
        Users users = getUser();
        username.setText(users.getUsername());
        firstName.setText(clients.getFirstNameClient());
        lastName.setText(clients.getLastNameClient());
        email.setText(clients.getEmailClient());
        telNo.setText(clients.getTelephoneNumber());


        List<Requests> requestsList = getAllRequest();
        List<Books> bookList = getAllBooks();
        List<ClientAccountWrapper> clientAccountWrapperList = new ArrayList<>();

        for(Requests requestIterator: requestsList) {
            if (requestIterator.getIdClient() == clients.getIdClient()) {
                for(Books booksIterator: bookList){
                    if(booksIterator.getIdBook() == requestIterator.getIdBook()){
                        ClientAccountWrapper clientAccountWrapper = new ClientAccountWrapper(booksIterator, requestIterator);
                        clientAccountWrapperList.add(clientAccountWrapper);
                    }
                }
            }
        }

        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        authorNameColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        dateBorrowed.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dateReturn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        tableRequests.setItems(FXCollections.observableArrayList(clientAccountWrapperList));

        invoice(clientAccountWrapperList);
    }

    public void invoice(List<ClientAccountWrapper> clientAccountWrapperList) throws InterruptedException {
        for(ClientAccountWrapper clientAccountWrapperIterator: clientAccountWrapperList){
            if(clientAccountWrapperIterator.getEndDate().compareTo(Date.valueOf(LocalDate.now())) == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("A expirat termenul(" + clientAccountWrapperIterator.getEndDate() + ") de returnare a cartii " +
                        clientAccountWrapperIterator.getBookName() + " scrisa de catre " + clientAccountWrapperIterator.getAuthorName());
                alert.setTitle("NOTIFICARE RETURNARE CARTE");
                alert.setHeaderText(null);
                alert.show();
            }
        }
    }

    public void openBook() throws IOException {
        if(tableRequests.getSelectionModel().getSelectedItem() != null){
            try(PrintWriter writer = new PrintWriter(("src/session/BookSession.txt"))){
                writer.println(tableRequests.getSelectionModel().getSelectedItem().getBookName());
            }catch (Exception e) {
                e.printStackTrace();
            }
            String bookName = tableRequests.getSelectionModel().getSelectedItem().getBookName();
            //System.out.println(tableRequests.getSelectionModel().getSelectedItem());
            Parent root = FXMLLoader.load(getClass().getResource("../scene/BookReview.fxml"));
            Stage bookReview = new Stage();
            bookReview.setScene(new Scene(root));
            bookReview.setTitle(bookName);
            bookReview.show();
        }
    }

    public List<Requests> getAllRequest(){
        RequestService requestService= new RequestService();
        return requestService.getAllRequests();

    }

    public List<Books> getAllBooks(){
        BookService bookService= new BookService();
        return bookService.getAllBooks();
    }

    public void goToMainMenu() throws IOException {
        mainMenu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientMenu.fxml"));
        Stage clientMenu = new Stage();
        clientMenu.setScene(new Scene(root));
        clientMenu.setTitle("CLIENT MENU");
        clientMenu.show();
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

    public Users getUser() throws Exception{
        UserService userService = new UserService();
        return userService.findByUsername(getUsernameClient());
    }
}
