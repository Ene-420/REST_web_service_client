/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import client.ClientBooking;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.core.Response;
import trips.User;

/**
 *
 * @author Bigse
 */
public class loginPageController implements Initializable{
    

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField userIdField;
    
    @FXML
    private Label Message;
    
        public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/HomePage.fxml"));
        Stage backPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        backPage.setScene(homeScene);
        backPage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void loginButton(ActionEvent event) throws IOException {
        ClientBooking client = new ClientBooking();
        User user = new User();
        boolean nextPage = false;

        if(firstNameField.getText().isEmpty() || userIdField.getText().isEmpty()){
            Message.setText("Fill All Spaces");
            nextPage = false;
        }
        else{

            user.setFirstName(firstNameField.getText());
            user.setUserID(Integer.valueOf(userIdField.getText()));


            Response response = client.loginUser(user, userIdField.getText());

            if(response.getStatus() != 201){
                Message.setText(response.readEntity(String.class));
                nextPage = false;
            }
            
            else{
                Message.setText("Welcome " + firstNameField.getText());
                nextPage = true;
                client.close();
            }
        }
        
        


        if(nextPage){
            Parent root = FXMLLoader.load(getClass().getResource("/trips/tripsPage.fxml"));
            Stage tripsPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene tripsScene = new Scene(root);
            tripsPageStage.setTitle(userIdField.getText());
            tripsPageStage.setScene(tripsScene);
            tripsPageStage.show();
        }
    }
    
}
