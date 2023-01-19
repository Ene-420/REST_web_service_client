package trips;

import client.ClientBooking;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import trips.TripBookingInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

public class viewTripsController implements Initializable {
    
    Stage viewTripsPage;
    
    List<TripBookingInfo> tripList = new ArrayList<>();
    ObservableList<TripBookingInfo> obList = FXCollections.observableArrayList();
     
    @FXML
    private DatePicker e_DateField;
        
    @FXML
    private DatePicker s_DateField;
    
    @FXML
    private TextField locationField;
    
    @FXML
    private TextField tripIdField;
          
    @FXML
    private TextField userIdField;

    @FXML
    private TableView<TripBookingInfo> tripsTable = new TableView<>();

    @FXML
    private TableColumn<TripBookingInfo, String> endDate;

    @FXML
    private TableColumn<TripBookingInfo, String> location;

    @FXML
    private TableColumn<TripBookingInfo, String> startDate;

    @FXML
    private TableColumn<TripBookingInfo, Integer> tripID;

    @FXML
    private TableColumn<TripBookingInfo, Integer> userID;
    
    @FXML
    private Label Message;

    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tripID.setCellValueFactory(new PropertyValueFactory<>("tripID"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        

    }
    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tripsPage.fxml"));
        Stage backPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        backPage.setScene(homeScene);
        backPage.show();
    }
    
    public void onEntery(MouseEvent event){
        ClientBooking client = new ClientBooking();
        Stage viewTripsPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String id = viewTripsPage.getTitle();
        userIdField.setText(id);
        Response response = client.getTrips( id);
        String trips =  response.readEntity(String.class);
        
        if(trips.contains("Error")){
            tripsTable.setItems(null);
            Message.setText("No Trips Available to View");
        }

        else{
        Type type =  new TypeToken<List<TripBookingInfo>>(){}.getType();
        tripList = new Gson().fromJson(trips, type);

        obList = FXCollections.observableList(tripList);
        tripsTable.setItems(obList);
        client.close();
        }
        
    }
    
    public void UpdateButon(ActionEvent event){
        ClientBooking client = new ClientBooking();
        TripBookingInfo tripUpdate = new TripBookingInfo();
        
        tripUpdate.setLocation(locationField.getText());
        tripUpdate.setTripID(Integer.valueOf(tripIdField.getText()));
        tripUpdate.setStartDate(s_DateField.getValue().toString());
        tripUpdate.setEndDate(e_DateField.getValue().toString());
        
        Response response = client.updateTrip(tripUpdate, userIdField.getText(), tripIdField.getText());
        
        String message = response.readEntity(String.class);
        
        Message.setText(message);
            
        client.close();
        
    }
    
    public void deleteButton(ActionEvent event){
        ClientBooking client = new ClientBooking();
        Response response = client.deleteTrip(userIdField.getText(), tripIdField.getText());
        
        String read = response.readEntity(String.class);
        Message.setText(read);
        
        client.close();
    }
    
    
}
