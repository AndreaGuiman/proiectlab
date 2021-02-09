package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.List;

public class ShowClientsController {
    @FXML
    Hyperlink hyperlinkMainMenu;
    @FXML
    TableView<Clients> tableClients;
    @FXML
    TableColumn<Clients, String> firstNameColumn, lastNameColumn, telNoColumn, emailColumn;
    @FXML
    Label managerName, managerUsername;

    public void initialize() throws Exception {
        Managers managers = getManager();
        Users users = getUser();

        managerName.setText(managers.getFirstNameManager() + " " + managers.getLastNameManager());
        managerUsername.setText(users.getUsername());

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameClient"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastNameClient"));
        telNoColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailClient"));

        ObservableList<Clients> clientsObservableList = FXCollections.observableArrayList(getAllClients());
        tableClients.setItems(clientsObservableList);
    }

    public void goToMainMenu() throws IOException {
        hyperlinkMainMenu.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
        Stage managerMenu = new Stage();
        managerMenu.setScene(new Scene(root));
        managerMenu.setTitle("MANAGER MENU");
        managerMenu.show();
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
