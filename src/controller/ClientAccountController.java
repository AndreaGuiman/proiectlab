package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Clients;
import model.Users;
import service.ClientService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClientAccountController {
    @FXML
    Hyperlink mainMenu;
    @FXML
    Label username, firstName, lastName, email,telNo;

    public void initialize() throws Exception {
        Clients clients = getClient();
        Users users = getUser();
        username.setText(users.getUsername());
        firstName.setText(clients.getFirstNameClient());
        lastName.setText(clients.getLastNameClient());
        email.setText(clients.getEmailClient());
        telNo.setText(clients.getTelephoneNumber());
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
