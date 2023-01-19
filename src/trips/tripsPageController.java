package trips;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class tripsPageController {
    
    @FXML
    Label userLabel;
    
    public void onEntering(MouseEvent event){
        Stage tripsPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String id = tripsPage.getTitle();
        if(!id.isEmpty()){
        userLabel.setText("Hello "  + id);
        }
    }
    public void bookTripButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("bookTripPage.fxml"));
        Stage bookTripPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene bookTripScene = new Scene(root);
        
        bookTripPage.setScene(bookTripScene);
        bookTripPage.show();


    }

    public void viewTripButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("viewTripsPage.fxml"));
        Stage viewTripPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene bookTripScene = new Scene(root);
        viewTripPage.setScene(bookTripScene);
        viewTripPage.show();


    }

    public void queryTripButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("queryTripPage.fxml"));
        Stage queryTripPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene queryTripScene = new Scene(root);
        queryTripPage.setScene(queryTripScene);
        queryTripPage.show();
    }
    
    public void logOutButton(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/home/HomePage.fxml"));
        Stage homePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homePage.setTitle(null);
        Scene homeScene = new Scene(root);
        homePage.setScene(homeScene);
        homePage.show();
    }
}
