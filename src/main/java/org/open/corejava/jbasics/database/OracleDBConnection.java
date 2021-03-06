package org.open.corejava.jbasics.database;

import java.sql.*;

public class OracleDBConnection {
    public static Connection conn = null;
    public Statement stmt;
    public ResultSet rs;

    public static Connection setDBConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>", "user", "password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
