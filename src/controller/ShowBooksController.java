package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Books;
import model.Managers;
import model.Users;
import service.BookService;
import service.ManagerService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ShowBooksController {
    @FXML
    Hyperlink hyperlinkMainMenu;
    @FXML
    TableView<Books> tableBooks;
    @FXML
    TableColumn<Books, String> bookNameColumn, bookGenreColumn, bookAuthorColumn;
    @FXML
    TableColumn<Books, Integer> bookRatingColumn, bookStockColumn;
    @FXML
    Label managerName, managerUsername;

    public void initialize() throws Exception {
        Managers managers = getManager();
        Users users = getUser();

        managerName.setText(managers.getFirstNameManager() + " " + managers.getLastNameManager());
        managerUsername.setText(users.getUsername());

        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        bookGenreColumn.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        bookRatingColumn.setCellValueFactory(new PropertyValueFactory<>("bookRating"));
        bookStockColumn.setCellValueFactory(new PropertyValueFactory<>("stoc"));

        ObservableList<Books> booksObservableList = FXCollections.observableArrayList(getAllBooks());
        tableBooks.setItems(booksObservableList);
    }


    public List<Books> getAllBooks(){
        BookService bookService = new BookService();
        return bookService.getAllBooks();
    }

    public void goToMainMenu() throws IOException {
        hyperlinkMainMenu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
        Stage managerMenu = new Stage();
        managerMenu.setScene(new Scene(root));
        managerMenu.setTitle("MANAGER MENU");
        managerMenu.show();
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
