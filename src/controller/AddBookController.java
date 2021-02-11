package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Books;
import service.BookService;

import java.io.IOException;

public class AddBookController {
    @FXML
    TextField numeCarte, genCarte, numeAutor;
    @FXML
    Hyperlink hyperlinkMainMenu;
    @FXML
    Button buttonAdaugaCarte;

    public void initialize(){
        buttonAdaugaCarte.setDisable(true);
    }

    public void keyReleasedProperty(){
        buttonAdaugaCarte.setDisable(isNullOrEmpty(numeCarte.getText(), genCarte.getText(), numeAutor.getText()));
    }

    public static boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if  (st==null || st.equals(""))
                return true;

        }
        return false;
    }

    public void addBookAction() throws IOException {
        BookService bookService = new BookService();
        if(bookService.findByBookName(numeCarte.getText()) != null){
            Books books = bookService.findByBookName(numeCarte.getText());
            if(books.getAuthorName().equals(numeAutor.getText())){
                books.setStoc((books.getStoc()+1));
                System.out.println(books);
                bookService.updateBook(books);
            }
        }
        else{
            Books newBook = new Books(numeCarte.getText(), genCarte.getText(), numeAutor.getText(), 1);
            bookService.createBook(newBook);
        }
        buttonAdaugaCarte.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
        Stage managerMenuStage = new Stage();
        managerMenuStage.setTitle("MANAGER MENU");
        managerMenuStage.setScene(new Scene(root));
        managerMenuStage.show();

        //System.out.println(newBook);
    }


    public void goToManagerMenu() throws IOException {
        hyperlinkMainMenu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
        Stage managerMenuStage = new Stage();
        managerMenuStage.setTitle("MANAGER MENU");
        managerMenuStage.setScene(new Scene(root));
        managerMenuStage.show();
    }
}
