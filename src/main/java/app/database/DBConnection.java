package app.database;

import java.sql.*;

public class DBConnection {

    private Connection connection;

    public DBConnection(String baza,String login,String haslo) {
        try {
            connection = DriverManager.getConnection(baza, login, haslo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isConnected(){
        try {
            return connection.isValid(1000);
        } catch (SQLException throwables) {

            return false;
        }
    }

    public PreparedStatement prepareStatement(String query){
        try {
            return connection.prepareStatement(query);
        } catch (SQLException throwables) {
            throw new RuntimeException("Coś jest nie tak z bazą");
        }
    }

    public PreparedStatement prepareStatementWithKeys(String s) {
        try {
            return connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throw new RuntimeException("Coś jest nie tak z bazą");
        }
    }
}
