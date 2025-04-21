package com.hexaware.util;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
    @SuppressWarnings("exports")
	public static Connection getConnection() {
        String connStr = DBPropertyUtil.getPropertyString("resources/db.properties");
        try {
            return DriverManager.getConnection(connStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
