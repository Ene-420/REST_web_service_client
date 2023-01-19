/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package client;

import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import trips.TripBookingInfo;
import trips.User;
import trips.Weather;

/**
 * Jersey REST client generated for REST resource:TripBookingResource
 * [TripBooking]<br>
 * USAGE:
 * <pre>
 *        ClientBooking client = new ClientBooking();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Bigse
 */
public class ClientBooking {



        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/TripBookingService/webresources";

        public ClientBooking() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("TripBooking");
        }

        public Response loginUser(User requestEntity, String userID) throws ClientErrorException {
            return webTarget.path(java.text.MessageFormat.format("Login/{0}", new Object[]{userID})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(requestEntity), Response.class);
        }

        public Response updateTrip(Object requestEntity, String userID, String tripID) throws ClientErrorException {
            return webTarget.path(java.text.MessageFormat.format("Update/{0}/{1}", new Object[]{userID, tripID})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.json(requestEntity), Response.class);
        }

        public <T> T getAllUserTrips(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public Response getWeatherInformation(String location, String startDate, String endDate) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path("location").queryParam("location", location).queryParam("startDate", startDate).queryParam("endDate", endDate);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Response.class);
        }

        public Response registerUser(User requestEntity) throws ClientErrorException {
            return webTarget.path("Register").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(requestEntity), Response.class);
        }

        public Response createNewTrip(TripBookingInfo requestEntity, String userID) throws ClientErrorException {
            return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{userID})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.json(requestEntity), Response.class);
        }

        public Response getTrips( String userID) throws ClientErrorException {
            WebTarget resource = webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{userID}));
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(Response.class);
            //return trips;
        }

        public Response deleteTrip(String userID, String tripID) throws ClientErrorException {
            return webTarget.path(java.text.MessageFormat.format("Delete/{0}/{1}", new Object[]{userID, tripID})).request().delete(Response.class);
        }

        public void close() {
            client.close();
        }
    

    
}
