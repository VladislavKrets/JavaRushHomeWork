package com.javarush.test.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students;
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
        students = new ArrayList<>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student a : students) {
            if (a.getAverageGrade() == averageGrade) return a;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        double count = 0;
        for (Student a : students) {
            if (count < a.getAverageGrade()) count = a.getAverageGrade();
        }
        for (Student a : students) {
            if (count == a.getAverageGrade()) return a;
        }
        return null;
    }

    public Student getStudentWithMinAverageGrade() {
        //TODO:
        double count = 0;
        for (Student a : students) {
            if (count < a.getAverageGrade()) count = a.getAverageGrade();
        }
        for (Student a : students) {
            if (count > a.getAverageGrade()) count = a.getAverageGrade();
        }
        for (Student a : students) {
            if (count == a.getAverageGrade()) return a;
        }
        return null;
    }
    public void expel(Student student) {
        students.remove(student);
    }
}
