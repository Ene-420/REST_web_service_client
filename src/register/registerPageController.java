/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package register;

import client.ClientBooking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class registerPageController implements Initializable{
    
    
    @FXML
    private TextField regFirstNameField;

    @FXML
    private TextField regLastNameField;
    
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
    
    
    public void registerButton(ActionEvent event) throws IOException{
        ClientBooking client = new ClientBooking();
        boolean nextPage;
        
        User newUser = new User();
        if(regFirstNameField.getText().isEmpty() || regLastNameField.getText().isEmpty()){
            Message.setText("Fill All Spaces");
        }
        else{
        newUser.setFirstName(regFirstNameField.getText());
        newUser.setLastName(regLastNameField.getText());
        
        Response response = client.registerUser(newUser);
        
        if(response.getStatus() != 201){
            nextPage = false;
            Message.setText("Error: User Already Exists");
            
        }
        else{
        String id = response.readEntity(String.class);
        
        Message.setText(id);
        nextPage = true;
        client.close();
        }

        if(nextPage){
            Parent root = FXMLLoader.load(getClass().getResource("/trips/tripsPage.fxml"));
            Stage tripsPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene tripsScene = new Scene(root);
            tripsPageStage.setScene(tripsScene);
            tripsPageStage.setTitle(Message.getText());
            tripsPageStage.show();
        }
        
        }
    }
}
