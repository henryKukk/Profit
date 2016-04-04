package request;

import dataBase.DataBaseFactory;
import dataBase.DataBaseManager;
import org.json.JSONObject;
import user.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import net.sf.xsshtmlfilter.HTMLFilter;

public class EditUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = (String) request.getParameter("userToEdit");
        JSONObject user = null;
        try {
            DataBaseManager dbm = DataBaseFactory.getDataBase();
            user = dbm.getUserData(Integer.parseInt(uid));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(user.toString());
    }
}
