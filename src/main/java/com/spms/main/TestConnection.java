package com.spms.main;

import com.spms.util.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            System.out.println("Connected to MySQL Successful");
        }
        else {
            System.out.println("Connected to MySQL Failed");
        }
    }
}
