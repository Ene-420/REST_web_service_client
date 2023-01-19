/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bigse
 */
public class HomePageController implements Initializable{
    public void registerButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/register/registerPage.fxml"));
        Stage registerStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene registerPageScene = new Scene(root);
        registerStage.setScene(registerPageScene);
        registerStage.show();

    }
    
    @FXML
    public void loginButton(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/login/loginPage.fxml"));
        Stage loginStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene loginPageScene = new Scene(root);
        loginStage.setScene(loginPageScene);
        loginStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
