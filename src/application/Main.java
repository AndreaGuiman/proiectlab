package application;

import exception.IncorrectFileNameException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../scene/Login.fxml"));
            primaryStage.setTitle("LOGIN");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (FileNotFoundException e) {
            throw new IncorrectFileNameException();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
