package com.spms.main;

import java.util.Scanner;

public class Login {
    public static boolean checkLogin()
    {
        Scanner sc = new Scanner(System.in);
        String username = "admin";
        String password = "admin123";
        System.out.println("====== Admin Login ======");
        System.out.print("Enter username: ");
        username = sc.nextLine();
        System.out.print("Enter password: ");
        password = sc.nextLine();
        if (username.equals("admin") && password.equals("admin123"))
        {
            System.out.println("\nLogin Successful!\n");
            return true;
        }
        else
        {
            System.out.println("\nLogin Failed!\n");
            return false;
        }
    }
}
