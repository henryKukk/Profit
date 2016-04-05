/*
DEPRECATED!!!!



package request;

import dataBase.DataBaseManager;
import dataBase.H2MemoryDatabase;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;


public class GetUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DataBaseManager dbm = new DataBaseManager();
            JSONArray results = dbm.getAllUsers();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(results.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
*/
