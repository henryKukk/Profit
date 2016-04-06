package dataBase;




import java.sql.*;

public class H2MemoryDatabase {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    public H2MemoryDatabase() throws SQLException {
        Connection connection = getDBConnetcion();
        Statement stmt = null;
        ResultSet rs = connection.getMetaData().getTables(null, null, "CUSTOMER", null);
        try {
            if (!(rs.next())) {
                stmt = connection.createStatement();
                stmt.execute("CREATE TABLE CUSTOMER(id int primary key auto_increment, first_name varchar(50), last_name varchar(50), dob VARCHAR(50), username varchar(50), password varchar(50))");
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }

    }


    public Connection getDBConnetcion() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            System.out.println(e.getMessage());


        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("ConnectionERROR");
            System.err.println(e.getMessage());
        }
        return dbConnection;
    }


}
