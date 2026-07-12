package com.spms.main;
import com.spms.util.FileUtil;
import com.spms.service.StudentService;
import com.spms.model.Student;
import com.spms.service.StudentServiceImpl;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        if(!Login.checkLogin())
        {
            return;
        }
        FileUtil.createFile();
       ArrayList<Student> s1 = new ArrayList<>();
       Scanner sc = new Scanner(System.in);
       StudentService service = new StudentServiceImpl(s1,sc);
       while (true)
       {
           System.out.println("\n===== Student Placement Management System =====");
           System.out.println("1. Add Student");
           System.out.println("2. View Student");
           System.out.println("3. Search Student");
           System.out.println("4. Update Placements");
           System.out.println("5. Delete Students");
           System.out.println("6. Dashboard");
           System.out.println("7. Search Student by Name ");
           System.out.println("8. View Placed Student");
           System.out.println("9. View Unplaced Student");
           System.out.println("10. View Students By Company");
           System.out.println("11. Sort Students By CGPA");
           System.out.println("12. Placement Percentage");
           System.out.println("13. Student who get the Highest Package");
           System.out.println("14. Student who get the Lowest Package");
           System.out.println("15. Search Students by minimum CGPA");
           System.out.println("16. Export Student Report(CSV)");
           System.out.println("17. Exit");
           System.out.println("Enter your choice:");
           int choice = sc.nextInt();
           switch (choice)
               {
               case 1:
                   service.addStudent();
                   //System.out.println("Add Student selected");
                   //Student student = new Student();
                   //System.out.println("Enter Student ID:");
                   //student.setId(sc.nextInt());
                   //sc.nextLine();
                   //System.out.println("Enter Student Name:");
                   //student.setName(sc.next());
                   //System.out.println("Enter Student Email:");
                   //student.setEmail(sc.next());
                   //System.out.println("Enter Student Course:");
                   //student.setCourse(sc.next());
                   //System.out.println("Enter Student CGPA:");
                   //student.setCgpa(sc.nextDouble());
                   //s1.add(student);
                   //System.out.println("Student added successfully!");
                   break;
                   case 2:
                       service.viewStudent();
                       //System.out.println("View Student selected");
                       //if(s1.isEmpty())
                         //  System.out.println("Student list is empty!");
                       //else {
                         //  System.out.println("\n=====Student List ======");
                           //for (Student s : s1)
                           //{
                             //  System.out.println("ID: " + s.getId());
                               //System.out.println("Name: " + s.getName());
                               //System.out.println("Email: " + s.getEmail());
                               //System.out.println("Course: " + s.getCourse());
                               //System.out.println("CGPA: " + s.getCgpa());
                               //System.out.println("Placed :" + s.isPlaced());
                               //if (s.isPlaced()) {
                                 //  System.out.println("Company :" + s.getCompanyName());
                                   //System.out.println("Package : " + s.getPackageOffered() + "LPA");
                              // }
                           //}
                       //}
                       break;
                           case 3:
                               service.searchStudent();
                               //System.out.println("Enter Student ID to search:");
                               //int search = sc.nextInt();
                               //boolean found = false;
                               //for (Student s: s1)
                               //{
                                 //  if(s.getId() == search)
                                   //{
                                     //  System.out.println("Student found successfully!");
                                       //System.out.println("Student ID: " + s.getId());
                                       //System.out.println("Student Name: " + s.getName());
                                       //System.out.println("Student Email: " + s.getEmail());
                                       //System.out.println("Student Course: " + s.getCourse());
                                       //System.out.println("Student CGPA: " + s.getCgpa());
                                       //found = true;
                                       //break;
                                   //}
                               //}
                               //if(!found)
                                 //  System.out.println("Student ID not found!");
                               break;
                               case 4:
                                   service.updatePlacement();
                                  // System.out.println("Update Student selected");

                               //System.out.println("Enter Student ID to update:");
                                   //int id = sc.nextInt();
                                   //sc.nextLine();
                                  // boolean updated = false;
                                   //for (Student s: s1)
                                   //{
                                     //  if(s.getId() == id)
                                       //{
                                         //  System.out.print("Placed? (true/false): ");
                                           //s.setPlaced(sc.nextBoolean());
                                           //sc.nextLine();
                                          // if(s.isPlaced())
                                           //{
                                             //  System.out.println("Company Name: ");
                                               //s.setCompanyName(sc.nextLine());
                                               //System.out.println("Package Offered(LPA):");
                                               //s.setPackageOffered(sc.nextDouble());
                                           //}
                                           //updated = true;
                                           //System.out.println("Placement details updated successfully!");
                                       //    break;
                                     //  }
                                   //}
                                   //if(!updated)
                                     //  System.out.println("Student ID not found!");
                                   break;
                   case 5:
                       service.deleteStudent();
                       //System.out.println("Delete Student selected");
                       //System.out.println("Enter Student ID to delete:");
                       //int delete = sc.nextInt();
                       //boolean deleted = false;
                       //for (int i = 0; i < s1.size(); i++)
                       //{
                         //  if(s1.get(i).getId() == delete)
                           //    {
                             //      s1.remove(i);
                               //    deleted = true;
                               //System.out.println("Student deleted successfully!");
                               //break;
                               //}
                      // }
                       //if(!deleted)
                         //  System.out.println("Student ID not found!");
                       break;
                   case 6:
                           service.dashboard();
                           break;
                           case 7:
                               service.searchStudentByName();
                               break;
                               case 8:
                                   service.viewPlacedStudent();
                                   break;
                                   case 9:
                                       service.viewUnplacedStudent();
                                       break;
                                        case 10:
                                            service.viewStudentByCompany();
                                            break;
                                              case 11:
                                                  service.sortStudentByCGPA();
                                                  break;
                                                   case 12:
                                                       service.placementPercentage();
                                                       break;
                   case 13:
                       service.highestPackage();
                       break;
                   case 14:
                       service.lowestPackage();
                       break;
                       case 15:
                           service.seachStudentByCGPA();
                           break;
                           case 16:
                               service.exportToCSV();
                               break;
                                          case 17:
                                              System.out.println("Thank you for using Student Placement Management System.");
                                              System.exit(0);
                                              break;
                               default:
                                   System.out.println("Invalid choice");
               }
       }
}
}
