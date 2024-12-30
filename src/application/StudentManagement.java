package application;

import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    private static List<StudentRecords> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    public static void addStudent(StudentRecords studentRecords) {
        studentList.add(studentRecords);
        totalStudents++;
    }

    public static StudentRecords findStudentById(String id) {
        for (StudentRecords studentRecords : studentList) {
            if (studentRecords.getId().equals(id)) {
                return studentRecords;
            }
        }
        return null;
    }

    public static void updateStudent(String id, String name, int age, String grade) {
        StudentRecords studentRecords = findStudentById(id);
        if (studentRecords != null) {
            studentRecords.setName(name);
            studentRecords.setAge(age);
            studentRecords.setGrade(grade);
        }
    }

    public static List<StudentRecords> getAllStudents() {
        return studentList;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }
}
