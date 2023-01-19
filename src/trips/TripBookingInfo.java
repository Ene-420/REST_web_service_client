package trips;

public class TripBookingInfo {
    private int userID;
    private int tripID;
    private String location;
    private String startDate;
    private String endDate;
    

    public TripBookingInfo() {
    }

    
    public TripBookingInfo(int userID, int tripID, String location, String endDate, String startDate) {
        this.userID = userID;
        this.tripID = tripID;
        this.location = location;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    
    public int getUserID() {
        return userID;
    }

    public int getTripID() {
        return tripID;
    }

    public String getLocation() {
        return location;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }
}
