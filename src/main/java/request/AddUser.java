package request;

import dataBase.DataBaseFactory;
import dataBase.DataBaseManager;
import dataBase.H2MemoryDatabase;
import net.sf.xsshtmlfilter.HTMLFilter;
import user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by henry on 12.03.16.
 */
public class AddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
