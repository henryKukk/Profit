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
            dataBase = DataBaseFactory.getDataBase();
        } catch (SQLException e) {
            System.err.println("Error with database " + e.getMessage());
        }
    }

    @Test
    public void testAddUser() throws Exception {
        User customer = new User("Henry", "Kukk");
        customer.setPassword("Test");
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
        User kasutaja = new User("Henry", "Kukk");
        kasutaja.setPassword("Test");
        kasutaja.setDateStamp("19/02/2015");
        dataBase.addUser(kasutaja);
        User editedUser = new User("Testing", "One");
        editedUser.setPassword("test");
        editedUser.setDateStamp("1/1/2015");
        editedUser.setUID(1);
        dataBase.editUser(editedUser);
        JSONObject userJSON = new JSONObject();
        userJSON.put("uid", 1);
        userJSON.put("firstname", "Testing");
        userJSON.put("lastname", "One");
        userJSON.put("password", "098F6BCD4621D373CADE4E832627B4F6");
        userJSON.put("dob", "1/1/2015");
        assertEquals(userJSON.toString(), dataBase.getUserData(1).toString());
    }

    @Test
    public void testDeleteUser() throws Exception {
        dataBase.deleteUser(1);
        assertEquals(1, dataBase.getAllUsers().length());
    }


}