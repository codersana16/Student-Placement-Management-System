package com.spms.util;

import java.io.File;
import java.io.IOException;

public class FileUtil
{
    public static final String FILE_PATH = "data/students.txt";
    public static void createFile()
    {
        File file = new File(FILE_PATH);
        try
        {
            if (file.createNewFile())
            {
                System.out.println("students.txt created successfully.");
            }
            else {
                System.out.println("students.txt already exists.");
            }
        }
        catch (IOException e)
        {
            System.out.println("Error creating students.txt file: " + e.getMessage());
        }
    }
}
