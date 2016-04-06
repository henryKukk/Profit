package request;

import dataBase.DataBaseManager;
import net.sf.xsshtmlfilter.HTMLFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import user.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.InputMismatchException;

/**
 * Created by henry on 5.04.16.
 */

@Controller
public class CustomerController {


    @RequestMapping(value="/getall", method= RequestMethod.GET)
    public @ResponseBody String showCustomers() {
        try {
            DataBaseManager dbm = new DataBaseManager();
            JSONArray results = dbm.getAllUsers();
            return String.valueOf(results);
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR: Error when trying to connect to database";
        }
    }
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public @ResponseBody String addCustomer(HttpServletRequest request) {
        HTMLFilter cleaner = new HTMLFilter();
        User customer = null;
        String firstName;
        String lastName;
        String password;
        String username;
        String dob;
        try {
            firstName = cleaner.filter(request.getParameter("first_name"));
            lastName = cleaner.filter(request.getParameter("last_name"));
            username = cleaner.filter(request.getParameter("username"));
            password = cleaner.filter(request.getParameter("password"));
            dob = cleaner.filter(request.getParameter("dob"));
            customer = new User();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPassword(password);
            customer.setUserName(username);
            customer.setDateStamp(dob);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "ERROR: Not all required fields given";
        } catch (InputMismatchException inputError) {
            inputError.printStackTrace();
            return "ERROR: Input is not correct";
        }

        try {
            DataBaseManager dbm = new DataBaseManager();
            dbm.addUser(customer);
            return "Successfully added customer";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "ERROR: Failed adding customer";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public @ResponseBody String editCustomer(HttpServletRequest request) {
        HTMLFilter cleaner = new HTMLFilter();
        User customer = null;
        String firstName;
        String lastName;
        int uid;
        String username;
        String password;
        String dob;
        try {
            firstName = cleaner.filter(request.getParameter("first_name"));
            lastName = cleaner.filter(request.getParameter("last_name"));
            uid = Integer.parseInt(request.getParameter("uid"));
            username = cleaner.filter(request.getParameter("username"));
            password = cleaner.filter(request.getParameter("password"));
            dob = cleaner.filter(request.getParameter("dob"));
            customer = new User();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setUID(uid);
            customer.setDateStamp(dob);
            customer.setUserName(username);
            customer.setPassword(password);
        } catch (NullPointerException e) {
            return "ERROR: Not all required fields given";
        } catch (NumberFormatException numberError) {
            return "ERROR: Error when trying to parse UID";
        } catch (InputMismatchException inputError) {
            return "ERROR: Error in input";
        }

        try {
            DataBaseManager dbm = new DataBaseManager();
            dbm.editUser(customer);
            return "User edit success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error while trying to edit user in DB";
        }
    }

    @RequestMapping(value="/getcustomer", method=RequestMethod.GET)
    public @ResponseBody String getUserData(HttpServletRequest request) {
        String uid = "";

        try {
            uid = (String) request.getParameter("userToEdit");
            JSONObject user = null;
            DataBaseManager dbm = new DataBaseManager();
            user = dbm.getUserData(Integer.parseInt(uid));
            return user.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR: Error while trying to get user from DB";
        } catch (NumberFormatException numberError) {
            return "ERROR: UID is not a number";
        } catch (NullPointerException nullpointer) {
            return "ERROR: UID not given";
        }
    }
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String userData(HttpServletRequest request) {
        String uid = request.getParameter("userToDelete");
        try {
            DataBaseManager dbm = new DataBaseManager();
            dbm.deleteUser(Integer.parseInt(uid));
            return "User delete success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR: Error while trying to add user to DB";
        }
    }
    }

