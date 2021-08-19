package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        final String URL = "jdbc:mysql://localhost:3306/my_schema?autoReconnect=true&useSSL=false&serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера!");
        }
        return connection;
    }
}
