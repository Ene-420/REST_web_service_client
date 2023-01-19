package trips;

import client.ClientBooking;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javax.ws.rs.core.Response;

public class bookTripController implements Initializable {
    

    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tripsPage.fxml"));
        Stage backPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        backPage.setScene(homeScene);
        backPage.show();
    }

    @FXML
    TextField userID;
    @FXML
    TextField location;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    Label Message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    
    public void bookTrip(ActionEvent event){
        ClientBooking client = new ClientBooking();
        if(userID.getText().isEmpty()||location.getText().isEmpty()|| startDate.getValue() == null){
            Message.setText("Fill All Required Boxes");
            
        }
        else{
           TripBookingInfo enterTrip = new TripBookingInfo(); 
           
            enterTrip.setUserID(Integer.valueOf(userID.getText()));
            enterTrip.setLocation(location.getText());
            LocalDate sDate = startDate.getValue();
            enterTrip.setStartDate(sDate.toString());
            LocalDate eDate = endDate.getValue();
            enterTrip.setEndDate(eDate.toString());
        
        Response response = client.createNewTrip(enterTrip, userID.getText());
        String clientResponse = response.readEntity(String.class);
        
        if(response.getStatus() != 201){
            Message.setText(clientResponse);
        }
        
        Message.setText(clientResponse);
        userID.clear();
        location.clear();
        startDate.setValue(null);
        endDate.setValue(null);
        client.close();
        }
        
        
    }
}
