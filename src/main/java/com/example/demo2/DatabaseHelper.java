package com.example.demo2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper {
    private static final String DATABASE_URL = "jdbc:mysql://root:bakamoto@localhost:3306/locale";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
    public static void addEmployee(String firstName, String lastName, String email,String lang) throws SQLException {
        String sql = "INSERT INTO employee (languageCode, firstName, lastName,email) VALUES (?, ?, ?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,lang);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
        }
    }

}

