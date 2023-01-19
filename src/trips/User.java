/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trips;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Bigse
 */


public class User {
    
    
    private String firstName;
    private String lastName;
    private int UserID;

    public User() {
    }
    
       public User(String firstName, int UserID){
        this.UserID= UserID;
        this.firstName = firstName;
    }
    

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
    
}
