package user;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class User {
    private String firstName = "";
    private String lastName = "";
    private int UID;
    private String dateStamp;
    private final String DATE_PATTERN = "MM/dd/yyyy";
    private String password;
    private String userName;
    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp (String dateStamp) throws InputMismatchException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        try {
            format.parse(dateStamp);
        } catch (ParseException parseError) {
            throw new InputMismatchException();
        }
        this.dateStamp = dateStamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InputMismatchException {
        if (password.length() < 6 || password.length() > 50) {
            throw new InputMismatchException();
        }
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws InputMismatchException{
        if (userName.length() < 3 || userName.length() > 50) {
            throw new InputMismatchException();
        }
        this.userName = userName;
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

    public void setLastName(String lastName) throws InputMismatchException {
        if (lastName.length() < 3 || lastName.length() > 50) {
            throw new InputMismatchException();
        }
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) throws InputMismatchException {
        if (firstName.length() < 3 || firstName.length() > 50) {
            throw new InputMismatchException();
        }
        this.firstName = firstName;
    }
}
