package dataBase;

import org.json.JSONArray;
import org.json.JSONObject;
import user.User;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DataBaseManager {
    public static H2MemoryDatabase dataBase = null;

    public DataBaseManager() throws SQLException {
        if (dataBase == null) {
            dataBase = new H2MemoryDatabase();
        }
    }

    public void deleteUser(int uid) {
        Connection connection = dataBase.getDBConnetcion();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM CUSTOMER WHERE id=?");
            stmt.setInt(1, uid);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void addUser(User customer) throws SQLException {
        Connection connection = dataBase.getDBConnetcion();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String userName = customer.getUserName();
        String password = customer.getPassword();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String timestamp = customer.getDateStamp();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CUSTOMER(first_name, last_name, dob, username, password) VALUES(?, ? ,? ,?, ?)");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, timestamp);
            statement.setString(4, userName);
            statement.setString(5, password);
            statement.execute();
            statement.close();
            connection.commit();
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        } finally {
            connection.close();
        }

    }

    public JSONArray getAllUsers() throws SQLException {
        Connection connection = dataBase.getDBConnetcion();
        JSONArray users = new JSONArray();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CUSTOMER");
            while (rs.next()) {
                JSONObject user = new JSONObject();
                user.put("firstName", rs.getString("first_name"));
                user.put("lastName", rs.getString("last_name"));
                user.put("id", rs.getInt("id"));
                user.put("username", rs.getString("username"));
                user.put("password", rs.getString("password"));
                user.put("dob", rs.getString("dob"));
                users.put(user);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return users;
    }

    public JSONObject getUserData(int uid) {
        Connection connection = dataBase.getDBConnetcion();
        PreparedStatement stmt = null;
        User user = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE id=?");
            stmt.setInt(1, uid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUID(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDateStamp(rs.getString("dob"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject userJSON = new JSONObject();
        userJSON.put("firstname", user.getFirstName());
        userJSON.put("lastname", user.getLastName());
        userJSON.put("uid", user.getUID());
        userJSON.put("username", user.getUserName());
        userJSON.put("password", user.getPassword());
        userJSON.put("dob", user.getDateStamp());
        return userJSON;
    }

    public void editUser(User customer) {
        Connection connection = dataBase.getDBConnetcion();
        Statement stmt = null;
        String password = customer.getPassword();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            password = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            stmt = connection.createStatement();
            PreparedStatement prepState = connection.prepareStatement("UPDATE CUSTOMER SET first_name=?, last_name=?, dob=?, username=?, password=? WHERE id=?");
            prepState.setString(1, customer.getFirstName());
            prepState.setString(2, customer.getLastName());
            prepState.setString(3, customer.getDateStamp());
            prepState.setString(4, customer.getUserName());
            prepState.setString(5, password);
            prepState.setInt(6, customer.getUID());
            prepState.execute();
            prepState.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

