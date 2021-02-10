package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import service.*;
import wrapper.RequestWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShowRequestsController {
    @FXML
    Hyperlink hyperlinkMainMenu;
    @FXML
    Label managerName, managerUsername;
    @FXML
    TableView<RequestWrapper> tableRequests;
    @FXML
    TableColumn<RequestWrapper, String> firstNameClientColumn, lastNameClientColumn, emailColumn, bookTitleColumn;
    @FXML
    TableColumn<RequestWrapper, Date> dateBorrowed, dateReturn;
    @FXML
    Button buttonCauta;
    @FXML
    ChoiceBox<String> selecteazaCarte;


    List<Requests> requestsList;
    List<RequestWrapper> requestWrapperList = new ArrayList<>();

    public void initialize() throws Exception {
        try {
            Managers managers = getManager();
            Users users = getUser();
            managerName.setText(managers.getFirstNameManager() + " " + managers.getLastNameManager());
            managerUsername.setText(users.getUsername());
        }catch (Exception e){
            e.printStackTrace();
        }

       requestsList = getAllRequests();
       List<Books> booksList =getAllBooks();
       System.out.println(booksList);
       List<Clients> clientsList = getAllClients();

       for(Requests requestsIterator: requestsList){
           for (Books booksIterator: booksList){
               if(requestsIterator.getIdBook() == booksIterator.getIdBook()){
                   for(Clients clientsIterator: clientsList){
                       if(requestsIterator.getIdClient() == clientsIterator.getIdClient()){
                           RequestWrapper requestWrapper = new RequestWrapper(clientsIterator, booksIterator, requestsIterator);

                           requestWrapperList.add(requestWrapper);
                       }
                   }
               }
           }
       }

        System.out.println(requestWrapperList);

       firstNameClientColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameClient"));
       lastNameClientColumn.setCellValueFactory(new PropertyValueFactory<>("lastNameClient"));
       emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailClient"));
       bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
       dateBorrowed.setCellValueFactory(new PropertyValueFactory<>("startDate"));
       dateReturn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        setDefaultItems();
        setChoiceBox();

    }

    public void setDefaultItems(){
        tableRequests.setItems(FXCollections.observableArrayList(requestWrapperList));
    }

    public void setItems(List<RequestWrapper> requestWrapperList){
        tableRequests.setItems(FXCollections.observableArrayList(requestWrapperList));
    }

    public void setChoiceBox(){
        List<String> bookNames = new ArrayList<>();
        for(RequestWrapper requestWrapperIterator: requestWrapperList){
            if(!bookNames.contains(requestWrapperIterator.getBookName()))
                bookNames.add(requestWrapperIterator.getBookName());
        }
        selecteazaCarte.setItems(FXCollections.observableArrayList(bookNames));
    }

    public void searchAction(){
        List<RequestWrapper> requestSelection = new ArrayList<>();
        for(RequestWrapper requestWrapperIterator: requestWrapperList){
            if( requestWrapperIterator.getBookName().equals(selecteazaCarte.getValue())){
                requestSelection.add(requestWrapperIterator);
            }
        }
        setItems(requestSelection);
    }

    public void goToMainMenu() throws IOException {
        hyperlinkMainMenu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
        Stage managerMenu = new Stage();
        managerMenu.setScene(new Scene(root));
        managerMenu.setTitle("MANAGER MENU");
        managerMenu.show();
    }

    public List<Requests> getAllRequests(){
        RequestService requestService = new RequestService();
        return requestService.getAllRequests();
    }

    public List<Books> getAllBooks(){
        BookService bookService = new BookService();
        return bookService.getAllBooks();
//        List<Books> booksList = bookService.getAllBooks();
//        List<Books> booksRequestsList = new ArrayList<>();
//        for(Requests requestsIterator: requestsList){
//            for(Books booksIterator: booksList){
//                if(requestsIterator.getIdBook() == booksIterator.getIdBook()){
//                    booksRequestsList.
//                }
//            }
//        }
    }

    public List<Clients> getAllClients(){
        ClientService clientService = new ClientService();
        return clientService.getAllClients();
    }

    public String getUsernameManager(){
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

    public Managers getManager() throws Exception {
        String usernameTmp = getUsernameManager();
        UserService userService = new UserService();
        Users users = userService.findByUsername(usernameTmp);
        ManagerService managerService = new ManagerService();
        return managerService.findById(users.getIdManager());
    }

    public Users getUser() throws Exception{
        UserService userService = new UserService();
        return userService.findByUsername(getUsernameManager());
    }
}
