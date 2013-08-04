package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import personalwork.Course;
import personalwork.CourseSelection;
import personalwork.ObjectAndFile;
import personalwork.Student;
import personalwork.Teacher;
import ui.StartPanel;
import ui.SystemFrame;

public class Main {
    /*
     * ����Ψһmain���
     */
    public static void main(String[] args) {
        // ���ڳ�ʼ�������б�
         List<Course> courseList = new ArrayList<Course>();
          
         ObjectAndFile.writeCourseToFile(courseList, new
         File("courseList.txt"));
        
         List<Student> studentList = new ArrayList<Student>();
         studentList.add(new Student(121250057,"000000","��С��","��һ"));
         ObjectAndFile.writeStudentToFile(studentList, new File(
         "studentList.txt"));
        
         List<Teacher> teacherList = new ArrayList<Teacher>();
         teacherList.add(new Teacher(1,"000000","��С��"));
         ObjectAndFile.writeTeacherToFile(teacherList, new File(
         "teacherList.txt"));
        
         List<CourseSelection> courseSelectionList = new
         ArrayList<CourseSelection>();
         ObjectAndFile.writeCourseSelectionToFile(courseSelectionList, new
         File(
         "courseSelectionList.txt"));
        
        SystemFrame systemFrame=new SystemFrame();
        StartPanel startPanel=new StartPanel(systemFrame);
        
        
    }


}
