package request;

import dataBase.DataBaseFactory;
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

/**
 * Created by henry on 5.04.16.
 */

@Controller
public class CustomerController {


    @RequestMapping(value="/getall", method= RequestMethod.GET)
    public @ResponseBody String showCustomers() {
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            JSONArray results = dbm.getAllUsers();
            return String.valueOf(results);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Nothign";
    }
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public @ResponseBody String addCustomer(HttpServletRequest request) {
        HTMLFilter cleaner = new HTMLFilter();

        String firstName = cleaner.filter(request.getParameter("first_name"));
        String lastName = cleaner.filter(request.getParameter("last_name"));
        String passWord = cleaner.filter(request.getParameter("password"));
        String username = cleaner.filter(request.getParameter("username"));
        String timestamp = cleaner.filter(request.getParameter("dob"));
        User user = new User(firstName, lastName);
        user.setPassword(passWord);
        user.setUserName(username);
        user.setDateStamp(timestamp);
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            dbm.addUser(user);
            return "Successfully added customer";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Failed adding customer";
    }

    @RequestMapping(value="/edit", method=RequestMethod.POST)
    public @ResponseBody String editCustomer(HttpServletRequest request) {
        HTMLFilter cleaner = new HTMLFilter();
        String firstName = cleaner.filter(request.getParameter("first_name"));
        String lastName = cleaner.filter(request.getParameter("last_name"));
        int uid = Integer.parseInt(request.getParameter("uid"));
        String username = cleaner.filter(request.getParameter("username"));
        String password = cleaner.filter(request.getParameter("password"));
        String dob = cleaner.filter(request.getParameter("dob"));
        User user = new User(firstName, lastName);
        user.setUID(uid);
        user.setDateStamp(dob);
        user.setUserName(username);
        user.setPassword(password);
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            dbm.editUser(user);
            return "User edit success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User edit failed";
    }

    @RequestMapping(value="/getcustomer", method=RequestMethod.GET)
    public @ResponseBody String getUserData(HttpServletRequest request) {
        String uid = (String) request.getParameter("userToEdit");
        JSONObject user = null;
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            user = dbm.getUserData(Integer.parseInt(uid));
            return user.toString();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error while getting user";
    }
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String userData(HttpServletRequest request) {
        String uid = request.getParameter("userToDelete");
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            dbm.deleteUser(Integer.parseInt(uid));
            return "User delete success";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Faieled to delete user";
    }
    }

