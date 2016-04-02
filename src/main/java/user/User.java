package user;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by henry on 17.03.16.
 */
public class User {
    private String firstName = "";
    private String lastName = "";
    private int UID;
    private String dateStamp;


    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp (String dateStam) {
        this.dateStamp = dateStam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String password;
    private String userName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getUID() {
        return UID;
    }
}
