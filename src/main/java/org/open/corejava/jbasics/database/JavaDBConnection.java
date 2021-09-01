package org.open.corejava.jbasics.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JavaDBConnection {
    public static Connection conn = null;
    public Statement stmt;
    public ResultSet rs;

    private static Connection setDBConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mmt", "root", "");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<AirportModel> getAirports() throws SQLException {
        List<AirportModel> result = new ArrayList<AirportModel>();
        conn = JavaDBConnection.setDBConnection();

        try {
            String query = "Select * from MMT";
            AirportModel model = null;

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                model = new AirportModel();
                model.setCode(rs.getString("code"));
                model.setLat(rs.getFloat("lat"));
                model.setLon(rs.getFloat("lon"));
                model.setName(rs.getString("name"));
                model.setRating(rs.getFloat("rating"));
                model.setCity(rs.getString("city"));
                model.setCity(rs.getString("state"));
                model.setCountry(rs.getString("country"));
                model.setTz(rs.getString("tz"));
                model.setType(rs.getString("type"));
                model.setUrl(rs.getString("url"));
                model.setElev(rs.getString("elev"));
                model.setDirectFlights(rs.getString("direct_flights"));

                result.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();

            if (conn != null)
                conn.close();
        }

        return result;
    }
}
