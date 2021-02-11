package controller;

import exception.IncorrectFileNameException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Books;
import model.Clients;
import model.Reviews;
import model.Users;
import service.BookService;
import service.ClientService;
import service.ReviewService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookReviewController {
    @FXML
    Button buttonReview;
    @FXML
    Hyperlink hyperlinkMeniu;
    @FXML
    TextArea textArea;
    @FXML
    TextField rating;
    @FXML
    Label bookName, authorName;

    public void initialize(){
        buttonReview.setDisable(true);

        Books books = getBook();
        bookName.setText(books.getBookName());
        authorName.setText(books.getAuthorName());

    }

    public void keyReleasedProperty(){
        buttonReview.setDisable(isNullOrEmpty(textArea.getText(), rating.getText()));
    }

    public static boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if  (st==null || st.equals(""))
                return true;

        }
        return false;
    }

    public void reviewAction() throws Exception {
        Books books = getBook();
        Clients clients = getClient();
        boolean reviewOK = false;

        if(textArea.getText().length() < 45 && (Integer.parseInt(rating.getText()) > 0  && Integer.parseInt(rating.getText()) < 6)){
            Reviews reviews = new Reviews(textArea.getText(), books.getIdBook(), clients.getIdClient(), Integer.parseInt(rating.getText()));
            ReviewService reviewService = new ReviewService();
            reviewService.addReview(reviews);
            reviewOK = true;
        }else {
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Textul depaseste limita de caractere sau ratingul cartii nu a fost introdus corect. Textul trebuie sa nu depaseasca 45 de caractere," +
                        " iar numarul trebuie ales intre 1 si 5!");
                alert.setTitle("OPPS!");
                alert.setHeaderText(null);
                alert.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (reviewOK){
            try {
                buttonReview.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientAccount.fxml"));
                Stage clientAccount = new Stage();
                clientAccount.setScene(new Scene(root));
                clientAccount.setTitle("CLIENT ACCOUNT");
                clientAccount.show();
            }
            catch (IncorrectFileNameException e){
                e.getMessage();
            }
        }
    }

    public void goToMenu() throws IOException {
        hyperlinkMeniu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientMenu.fxml"));
        Stage clientMenu = new Stage();
        clientMenu.setScene(new Scene(root));
        clientMenu.setTitle("CLIENT MENU");
        clientMenu.show();
    }

    public String getBookNameFromFile(){
        String bookName = null;
        try(BufferedReader br = new BufferedReader(new FileReader("src/session/BookSession.txt"))){
            String bookNametmp;
            while((bookNametmp = br.readLine()) != null)
                bookName = bookNametmp;
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
