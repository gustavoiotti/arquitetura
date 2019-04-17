//package br.edu.utfpr.dao;
package br.edu.utfpr.dao.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection {
    public Connection getConnection() {
        Connection connection = null;
        //Connection getConnection;

        try {
            connection = DriverManager.getConnection(getConnectionString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    @Override
    public String getConnectionString() {
        return "jdbc:derby:memory:database;create=true";
    }
}