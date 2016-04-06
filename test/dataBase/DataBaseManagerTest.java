package dataBase;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import user.User;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DataBaseManagerTest {
    DataBaseManager dataBase;
    @Before
    public void setUp() {
        try {
            dataBase = new DataBaseManager();
        } catch (SQLException e) {
            System.err.println("Error with database " + e.getMessage());
        }
    }

    @Test
    public void testAddUser() throws Exception {
        User customer = new User();
        customer.setFirstName("Henry");
        customer.setLastName("Kukk");
        customer.setPassword("Test12345");
        customer.setDateStamp("19/02/2015");
        dataBase.addUser(customer);
        assertEquals(2, dataBase.getAllUsers().length());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        assertEquals(2, dataBase.getAllUsers().length());
    }

    @Test
    public void testEditUser() throws Exception {
        User customer = new User();
        customer.setFirstName("Henry");
        customer.setLastName("Kukk");
        customer.setPassword("Test1234566");
        customer.setDateStamp("19/02/2015");
        dataBase.addUser(customer);
        User editedUser = new User();
        editedUser.setLastName("One");
        editedUser.setFirstName("TestingOne");
        editedUser.setUserName("usernameTest");
        editedUser.setPassword("test123456");
        editedUser.setDateStamp("1/1/2015");
        editedUser.setUID(1);
        dataBase.editUser(editedUser);
        JSONObject userJSON = new JSONObject();
        userJSON.put("uid", 1);
        userJSON.put("firstname", "TestingOne");
        userJSON.put("lastname", "One");
        userJSON.put("password", "47EC2DD791E31E2EF2076CAF64ED9B3D");
        userJSON.put("dob", "1/1/2015");
        userJSON.put("username", "usernameTest");
        assertEquals(userJSON.toString(), dataBase.getUserData(1).toString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        dataBase.deleteUser(1);
        assertEquals(1, dataBase.getAllUsers().length());
    }


}