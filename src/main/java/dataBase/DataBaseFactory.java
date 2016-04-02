package dataBase;

import java.sql.SQLException;

/**
 * Created by henry on 17.03.16.
 */
public class DataBaseFactory {
    private static DataBaseManager dataBaseManager = null;
    public static DataBaseManager getDataBase() throws SQLException {
        if (dataBaseManager != null) {
            return dataBaseManager;
        } else {
            dataBaseManager = new DataBaseManager();
            return dataBaseManager;
        }

    }
}
