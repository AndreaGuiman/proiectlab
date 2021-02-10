package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Books;
import model.Users;
import service.BookService;
import service.ClientService;
import service.UserService;

import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../scene/Login.fxml"));
        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args) {
//        UserService userService = new UserService();
//        List<Users> usersList = userService.getAllUsers();
//        System.out.println(usersList);
//        BookService bookService = new BookService();
//        System.out.println(bookService.getAllBooks());
//        Books books = bookService.getAllBooks().get(0);
//        System.out.println(books.getBookName() + books.getBookGenre());
//
//        ClientService clientService= new ClientService();
//        System.out.println(clientService.getAllClients());
//        ClientService clientService = new ClientService();
//        System.out.println(clientService.getAllClients());
//        BookService bookService = new BookService();
//        System.out.println(bookService.getAllBooks());
        launch(args);

    }
}
