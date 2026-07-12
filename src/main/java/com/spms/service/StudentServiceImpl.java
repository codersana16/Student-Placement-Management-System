package com.spms.service;

import com.spms.model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import com.spms.util.DBConnection;

//import static com.sun.tools.javac.jvm.ClassFile.Version.MAX;

public class StudentServiceImpl
implements StudentService {
    private ArrayList<Student> students;
    private Scanner sc;

    public StudentServiceImpl(ArrayList<Student> students, Scanner sc) {
        this.students = students;
        this.sc = sc;
    }

    @Override
    public void addStudent() {
        int id;
        while (true) {
            try {
                System.out.println("Enter Student ID: ");
                id = sc.nextInt();
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Student ID! Please enter numbers only.");
            }
            sc.nextLine(); //Clear invalid input
        }
        //}
        //sc.nextLine();
        //for(Student s: students)
        //{
        //if(s.getId() == id)
        //{
        // System.out.println("Student ID already exists");
        // return;
        //}
        //}
        Student student = new Student();
        student.setId(id);
        System.out.println("Enter Student Name:");
        student.setName(sc.next());
        System.out.println("Enter Student Email:");
        student.setEmail(sc.next());
        System.out.println("Enter Student Course:");
        student.setCourse(sc.next());
        double cgpa;
        while (true) {
            System.out.println("Enter GPA:");
            cgpa = sc.nextDouble();
            if (cgpa >= 0 && cgpa <= 10)
                break;
            System.out.println("Invalid CGPA! Please enter  value between 0 and 10");
        }
        student.setCgpa(cgpa);
        // students.add(student);
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO students(id,name,email,course,cgpa,placed,Company_name,package_offered) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getCourse());
            ps.setDouble(5, student.getCgpa());
            ps.setBoolean(6, false);
            ps.setString(7, "");
            ps.setDouble(8, 0);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student Added successfully to database.");
            else
                System.out.println("Student Added failed to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void viewStudent() {
        System.out.println("view student details");
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n===== Student Details =====");
            while (rs.next()) {
                System.out.println("ID:" + rs.getInt("id"));
                System.out.println("Name:" + rs.getString("name"));
                System.out.println("Email:" + rs.getString("email"));
                System.out.println("Course:" + rs.getString("course"));
                System.out.println("CGPA:" + rs.getDouble("cgpa"));
                System.out.println("PLaced:" + rs.getBoolean("placed"));
                System.out.println("Company:" + rs.getString("company_name"));
                System.out.println("Package:" + rs.getDouble("package_offered"));
                System.out.println("--------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        //  if(students.isEmpty())
        //{
        //  System.out.println("There are no students in the system!");
        //return;
        //}
        //System.out.println(("\n===== Student List ====\n"));
        ////for(Student s : students)
        //{
        //  System.out.println("ID: " + s.getId());
        //System.out.println("Name: " + s.getName());
        //System.out.println("Email: " + s.getEmail());
        //System.out.println("Course: " + s.getCourse());
        //System.out.println("CGPA: " + s.getCgpa());
        //System.out.println("Placed: "  + s.isPlaced());
        //if(s.isPlaced())
        //{
        //  System.out.println("Company: " + s.getCompanyName());
        //System.out.println("Package: " + s.getPackageOffered() + "LPA");
        //}
        //}
    }

    @Override
    public void searchStudent() {
        try {
            System.out.println("Enter Student ID:");
            int id = sc.nextInt();
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Id:" + rs.getInt("id"));
                System.out.println("Name:" + rs.getString("name"));
                System.out.println("Email:" + rs.getString("email"));
                System.out.println("Course:" + rs.getString("course"));
                System.out.println("CGPA:" + rs.getDouble("cgpa"));
                System.out.println("Placed:" + rs.getBoolean("placed"));
                System.out.println("Company:" + rs.getString("company_name"));
                System.out.println("Package:" + rs.getDouble("package_offered"));
            } else {
                System.out.println("Student ID Not Found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("Enter student ID to search:");
        //int id = sc.nextInt();
        //boolean found = false;
        //for(Student s : students)
        //{
        //  if(s.getId() == id)
        //{
        //  System.out.println("\n===== Student Found =====");
        //System.out.println("ID: " + s.getId());
        //System.out.println("Name: " + s.getName());
        //System.out.println("Email: " + s.getEmail());
        //System.out.println("Course: " + s.getCourse());
        //System.out.println("CGPA: " + s.getCgpa());
        //System.out.println("Placed: "  + s.isPlaced());
        //if(s.isPlaced())
        //{
        //  System.out.println("Company: " +  s.getCompanyName());
        //System.out.println("Package: " + s.getPackageOffered() + "LPA");
        // }
        //found = true;
        //break;
        //}
        //}
        //if (!found)
        //{
        //  System.out.println("Student not found!");
        //}
    }

    @Override
    public void updatePlacement() {
        try {
            System.out.println("Enter Student ID:");
            int id = sc.nextInt();
            sc.nextLine();
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("Student ID Not Found!");
                return;
            }
            System.out.print("Is student placed? (true/false): ");
            boolean placed = sc.nextBoolean();
            sc.nextLine();
            String company = "";
            double pkg = 0.0;
            if (placed) {
                System.out.println("Enter Company name:");
                company = sc.nextLine();
                System.out.println("Enter Package offered:");
                pkg = sc.nextDouble();
                sc.nextLine();
            }
            String updatesql = "UPDATE students SET placed = ?, company_name = ?, package_offered = ? WHERE id = ?";
            PreparedStatement pss = con.prepareStatement(updatesql);
            pss.setBoolean(1, placed);
            pss.setString(2, company);
            pss.setDouble(3, pkg);
            pss.setInt(4, id);
            int rows = pss.executeUpdate();
            if (rows > 0)
                System.out.println("Placement Updated successfully!");
            else
                System.out.println("Failed to update placement.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println("Enter student ID to update:");
        //int id = sc.nextInt();
        //boolean updated = false;
        //for (Student s : students)
        //  {
        // if(s.getId() == id)
        //{
        //  s.setPlaced(true);
        // sc.nextLine();
        //System.out.println("Enter Company Name: ");
        //s.setCompanyName(sc.nextLine());
        //System.out.println("Enter Package Offered (LPA): ");
        //s.setPackageOffered(sc.nextDouble());
        //System.out.println("Placement Updated Successfully!");
        //updated = true;
        // break;
        //}
        //}
        //if (!updated)
        //{
        //   System.out.println("Student not found!");
        //}
    }

    @Override
    public void deleteStudent() {
        try {
            System.out.println("Enter Student ID to delete:");
            int id = sc.nextInt();
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Student Deleted Successfully!");
            else
                System.out.println("Failed to delete student.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // System.out.println("Enter student ID to delete:");
    //int id = sc.nextInt();
    //boolean deleted = false;
    //for (int i = 0;i<students.size();i++)
    //{
    //  if(students.get(i).getId() == id)
    //{
    //  students.remove(i);
    //deleted = true;
    // System.out.println("Student deleted successfully!");
    // break;
    // }
    //}
    //if (!deleted)
    //{
    //  System.out.println("Student not found!");
    //}
    //}
    @Override
    public void dashboard() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String sql = "SELECT COUNT(*) AS total, " +
                    "SUM(CASE WHEN placed=1 THEN 1 ELSE 0 END) AS placed, " +
                    "SUM(CASE WHEN placed=0 THEN 1 ELSE 0 END) AS unplaced, " +
                    "MAX(package_offered) AS highest, " +
                    "AVG(package_offered) AS average_package " +
                    "FROM students";

            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println("\n========== DASHBOARD ==========");
                System.out.println("Total Students    : " + rs.getInt("total"));
                System.out.println("Placed Students   : " + rs.getInt("placed"));
                System.out.println("Unplaced Students : " + rs.getInt("unplaced"));
                System.out.println("Highest Package   : " + rs.getDouble("highest") + " LPA");
                System.out.println("Average Package   : " + rs.getDouble("average_package") + " LPA");
                System.out.println("================================");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void searchStudentByName()
    {
        try {
            System.out.println("Enter Student Name:");
            sc.nextLine();
            String name = sc.nextLine();

            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%" + name + "%");
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next())
            {
                found = true;
                System.out.println("------------------------------------");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Placed  :" + rs.getBoolean("placed"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered"));
            }
            if (!found)
                System.out.println("Student Name Not Found!");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void viewPlacedStudent()
    {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE placed = true";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next())
            {
                found = true;
                System.out.println("-----------------------------------");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Placed  :" + rs.getBoolean("placed"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered"));
            }
            if (!found)
                System.out.println("No Placed Students Found!");
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
    @Override
    public void viewUnplacedStudent()
    {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE placed = 0";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next())
                {
                found = true;
                    System.out.println("\n-----------------------------------");
                    System.out.println("Id  :" + rs.getInt("id"));
                    System.out.println("Name  :" + rs.getString("name"));
                    System.out.println("Email  :" + rs.getString("email"));
                    System.out.println("Course  :" + rs.getString("course"));
                    System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                    System.out.println("Placed  :" + rs.getBoolean("placed"));
                }
            if (!found)
                System.out.println("No Unplaced Students Found!");
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
    @Override
    public void viewStudentByCompany()
    {
        try {
            System.out.println("Enter Company Name:");
            sc.nextLine();
            String company = sc.nextLine();
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE company_name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"%" + company + "%");
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while(rs.next())
            {
                found = true;
                System.out.println("----------------------------------");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered"));
            }
            if (!found)
                System.out.println("No student found for this company.");
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
    @Override
    public void  sortStudentByCGPA()
    {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students ORDER BY cgpa DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next())
            {
                found = true;
                System.out.println("---------------------------------");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Placed  :" + rs.getBoolean("placed"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package :" + rs.getDouble("package_offered") + "LPA");
            }
            if(!found)
                System.out.println("No students found.");
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
    @Override
    public void placementPercentage()
    {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) AS total, " + "SUM(CASE WHEN placed=1 THEN 1 ELSE 0 END) AS placed" + "FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int total = rs.getInt("total");
                int placed = rs.getInt("placed");
                double percentage = (total ==0) ? 0 : (placed * 100) / total;
                System.out.println("\n============ Placement Report =============");
                System.out.println("Total Students :" + total);
                System.out.println("Placed Students :" + placed);
                System.out.println("Placement Percentage :" + percentage);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void highestPackage()
    {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students ORDER BY package_offered DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                System.out.println("\n===========Student who get the highest package =============");
                System.out.println("Id  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("CGPA"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered") + "LPA");
            }
            else
                System.out.println("No Student records found.");
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
    }
    @Override
    public void lowestPackage() {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE placed = 1 ORDER BY package_offered ASC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\n===========Student who get the lowest package =============");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Company  :" + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered") + "LPA");
            } else
                System.out.println("No Placed Student Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 @Override
 public void seachStudentByCGPA() {
        try {
            System.out.println("Enter minimum CGPA :");
            double cgpa = sc.nextDouble();
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students WHERE CGPA >= ? ORDER BY cgpa DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,cgpa);
            ResultSet rs = ps.executeQuery();
            boolean found = false;
            while (rs.next())
            {
                found = true;
                System.out.println("------------------------------");
                System.out.println("ID  :" + rs.getInt("id"));
                System.out.println("Name  :" + rs.getString("name"));
                System.out.println("Email  :" + rs.getString("email"));
                System.out.println("Course  :" + rs.getString("course"));
                System.out.println("CGPA  :" + rs.getDouble("cgpa"));
                System.out.println("Placed  :" + rs.getBoolean("placed"));
                System.out.println("Company  :"  + rs.getString("company_name"));
                System.out.println("Package  :" + rs.getDouble("package_offered") + "LPA");
            }
            if(!found)
                System.out.println("No students found with CGPA >= " + cgpa);
        }
        catch (SQLException e)
            {
            e.printStackTrace();
            }
 }
 @Override
   public void exportToCSV()
 {
     try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            FileWriter fw = new FileWriter("students_report.csv");
            fw.write("ID,Name,Email,Course,CGPA,Placed,Company,Package\n");
            while(rs.next())
            {
                fw.write(rs.getInt("id") + "," + rs.getString("name") + "," + rs.getString("email") + "," + rs.getString("course") + "," + rs.getDouble("cgpa") + "," + rs.getBoolean("placed") + "," + rs.getString("company_name") + "," + rs.getDouble("package_offered") + "\n" );
            }
            fw.close();
         System.out.println("Student report exported successfully.");
         System.out.println("File Name : students_report.csv");
     }
     catch (SQLException e)
         {
         e.printStackTrace();
         }
     catch (IOException e) {
         throw new RuntimeException(e);
     }
 }
}





























