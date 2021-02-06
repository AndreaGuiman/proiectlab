package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import service.UserService;
import java.io.IOException;

public class LoginController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button buttonLogin;
    @FXML
    Hyperlink hyperlinkSignup;

    public void initialize(){
        buttonLogin.setDisable(true);
    }

    public void keyReleasedProperty(){
        buttonLogin.setDisable(isNullOrEmpty(username.getText(), password.getText()));
    }

    public static boolean isNullOrEmpty(String... strArr) {
        for (String st : strArr) {
            if  (st==null || st.equals(""))
                return true;

        }
        return false;
    }

    public void loginAction() throws IOException {
        boolean userFound = false;
        UserService userService = new UserService();
        if(userService.findByUsername(username.getText()) != null){
            Users users = userService.findByUsername(username.getText());
            if(users.getPassword().equals(password.getText())){
                //login
                if(users.getIdClient() != null){
                    //intram pe meniu de client
                    buttonLogin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../scene/ClientMenu.fxml"));
                    Stage clientMenu = new Stage();
                    clientMenu.setTitle("MENU");
                    clientMenu.setScene(new Scene(root));
                    clientMenu.show();
                }
                if(users.getIdManager() != null){
                    //intram pe meniu de manager
                    buttonLogin.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("../scene/ManagerMenu.fxml"));
                    Stage ManagerMenu = new Stage();
                    ManagerMenu.setTitle("MANAGER MENU");
                    ManagerMenu.setScene(new Scene(root));
                    ManagerMenu.show();
                }
                userFound = true;
            }
        }
        if(!userFound){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Numele sau parola este gresita, sau utilizatorul nu se afla in baza de date");
            alert.setTitle("Opss!");
            alert.setHeaderText(null);
            alert.show();
        }
    }

    public void signupAction() throws IOException {
        hyperlinkSignup.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../scene/Signup.fxml"));
        Stage signUp = new Stage();
        signUp.setTitle("SIGN UP");
        signUp.setScene(new Scene(root));
        signUp.show();
    }
}
