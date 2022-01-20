package by.alex.kasperovich.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlJdbcUtil {
    public static final String JDBC_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_datebase";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PASSWORD = "password";

    private static Connection connection = null;
    private static Statement statement = null;

    public static Connection getConnection() {
        return getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static Connection getConnection(String jdbcUrl,String  jdbcUser, String jdbcPassword) {
        try {
            if (connection == null || connection.isClosed()) {
                //1. Check if jdbc driver is exist
                if (!checkDriver()){
                    throw new SQLException("driver not found");
                }

                try {
                    connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    private static boolean checkDriver() {
        try {
            Class.forName(JDBC_MYSQL_DRIVER);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Statement getStatement() {

        try {
            if (statement == null || statement.isClosed()) {
                connection = getConnection();
                try {
                    statement = connection.createStatement();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

    public static void closeStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void closeStatementAndConnection(){
        closeStatement();
        closeConnection();
    }
}
