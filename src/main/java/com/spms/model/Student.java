package com.spms.model;

public class Student
{
    private int id;
    private String name;
    private String email;
    private String course;
    private double cgpa;
    private boolean placed;
    private String companyName;
    private double packageOffered;
    public Student(){

    }

    public Student(int id, String name, String email, String course, double cgpa)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.cgpa = cgpa;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getCourse()
    {
        return course;
    }
    public void setCourse(String course)
    {
        this.course = course;
    }
    public double getCgpa()
    {
        return cgpa;
    }
    public void setCgpa(double cgpa)
    {
        this.cgpa = cgpa;
    }
    public boolean isPlaced() {
        return placed;
    }
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    public double getPackageOffered()
    {
        return packageOffered;
    }
    public void setPackageOffered(double packageOffered)
    {
        this.packageOffered = packageOffered;
    }

    @Override
    public String toString()
    {
        return "Student{" + "id=" + id + ", name=" + name + ", email=" + email + ", course=" + course  + ", cgpa=" + cgpa + ",placed=" + placed + ", companyName=" + companyName + ", packageOffered=" + packageOffered +"  }";
    }
}
