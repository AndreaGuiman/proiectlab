package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Clients;
import model.Managers;
import model.Users;
import service.ClientService;
import service.ManagerService;
import service.UserService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ManagerMenuController {
    @FXML
    Hyperlink hyperlinkafisareClienti, hyperlinkafisareCarti, hyperlinkafisareCereri, hyperlinkadaugaCarte;
    @FXML
    Button buttonLogOut;
    @FXML
    Label managerName, managerUsername;

    public void initialize() throws Exception {
        Managers managers = getManager();
        Users users = getUser();

        managerName.setText(managers.getFirstNameManager() + " " + managers.getLastNameManager());
        managerUsername.setText(users.getUsername());
    }

    public void showClients() throws IOException {
        hyperlinkafisareClienti.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ShowClients.fxml"));
        Stage afClients = new Stage();
        afClients.setScene(new Scene(root));
        afClients.setTitle("SHOW CLIENTS");
        afClients.show();
    }

    public void showBooks() throws IOException {
        hyperlinkafisareCarti.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ShowBooks.fxml"));
        Stage afBooks = new Stage();
        afBooks.setScene(new Scene(root));
        afBooks.setTitle("SHOW BOOKS");
        afBooks.show();
    }

    public void showRequest() throws IOException {
        hyperlinkafisareCereri.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ShowRequests.fxml"));
        Stage afRequest = new Stage();
        afRequest.setScene(new Scene(root));
        afRequest.setTitle("SHOW REQUESTS");
        afRequest.show();
    }

    public void addBook() throws IOException {
        hyperlinkadaugaCarte.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/AddBook.fxml"));
        Stage addB = new Stage();
        addB.setScene(new Scene(root));
        addB.setTitle("ADD BOOK");
        addB.show();
    }

    public void logOutAction() throws IOException {
        buttonLogOut.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/Login.fxml"));
        Stage login = new Stage();
        login.setScene(new Scene(root));
        login.setTitle("LOGIN");
        login.show();
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
