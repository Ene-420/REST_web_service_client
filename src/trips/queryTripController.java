package trips;

import client.ClientBooking;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javax.ws.rs.core.Response;

public class queryTripController implements Initializable {
    
    List<Weather> weatherList = new ArrayList<>();
    ObservableList  obList;
     
    @FXML
    private TableView<Weather> weatherTable;

    @FXML
    private TableColumn<Weather, Double> avgTemp;

    @FXML
    private TableColumn<Weather, String> conditions;

    @FXML
    private TableColumn<Weather, String> date;

    @FXML
    private TableColumn<Weather, String> descriptions;

    @FXML
    private TableColumn<Weather, Double> maxTemp;

    @FXML
    private TableColumn<Weather, Double> minTemp;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField location;

    @FXML
    private DatePicker startDate;
    
    @FXML
    private Label Message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avgTemp.setCellValueFactory(new PropertyValueFactory<Weather, Double>("avgTemp"));
        minTemp.setCellValueFactory(new PropertyValueFactory<Weather, Double>("minTemp"));
        maxTemp.setCellValueFactory(new PropertyValueFactory<Weather, Double>("maxTemp"));
        date.setCellValueFactory(new PropertyValueFactory<Weather, String>("dateTime"));
        descriptions.setCellValueFactory(new PropertyValueFactory<Weather, String>("description"));
        conditions.setCellValueFactory(new PropertyValueFactory<Weather, String>("conditions"));

    }

    public void backButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tripsPage.fxml"));
        Stage backPage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(root);
        backPage.setScene(homeScene);
        backPage.show();
    }
    
    public void onAction(ActionEvent event){
        ClientBooking client = new ClientBooking();
        
        if(location.getText().isEmpty() || startDate.getValue() == null){
            Message.setText("Fill All Spaces");
            //nextPage = false;
            System.out.println("Fill all Spaces");
        }
        else{

        Response response = client.getWeatherInformation(location.getText(),startDate.getValue().toString(), endDate.getValue().toString() );
        String read = response.readEntity(String.class);
        if(read.contains("No Weather Details available")){
            Message.setText(read);
            client.close();
        }
        else{
            
            Type type =  new TypeToken<List<Weather>>(){}.getType();
            weatherList = new Gson().fromJson(read, type);
        
            obList = FXCollections.observableList(weatherList);
            weatherTable.setItems(obList);
            client.close();
            }
        }
    }
}
