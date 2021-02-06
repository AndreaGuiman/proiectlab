package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Clients;
import model.Users;
import service.ClientService;
import service.UserService;

import java.io.IOException;

public class SignupController {
    @FXML
    Button buttonSignup;
    @FXML
    Hyperlink hyperlinkLogin;
    @FXML
    TextField usernameField, firstName, lastName, email, telephoneNumber;
    @FXML
    PasswordField passwordField, cPasswordField;

    public void initialize(){
        buttonSignup.setDisable(true);
    }

    public void keyReleasedProperty(){
        buttonSignup.setDisable(isNullOrEmpty(usernameField.getText(), passwordField.getText(), cPasswordField.getText(),
                firstName.getText(), lastName.getText(), email.getText(), telephoneNumber.getText()) && (passwordField.getText().equals(cPasswordField.getText())));
    }

    public static boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if  (st==null || st.equals(""))
                return true;

        }
        return false;
    }

    public void signUpAction() throws IOException {
        Clients clients = new Clients(firstName.getText(), lastName.getText(), email.getText(), telephoneNumber.getText());

        ClientService clientService = new ClientService();
        UserService userService = new UserService();

        try{
            clientService.addClient(clients);
            Clients clients1 = clientService.findByFirstName(firstName.getText());
            Users users = new Users( clients1.getIdClient(), usernameField.getText(), passwordField.getText());
            userService.addUser(users);
        }catch (Exception e){
            e.printStackTrace();
        }

        buttonSignup.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientMenu.fxml"));
        Stage clientMenu = new Stage();
        clientMenu.setTitle("MENU");
        clientMenu.setScene(new Scene(root));
        clientMenu.show();
    }

    public void goToLogin() throws IOException {
        hyperlinkLogin.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/Login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("LOGIN");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }
}
