package personalwork;

//import java.io.*;
//import java.util.*;

public class TeachingSystem {

//    public static void main(String[] args) {
//        TeachingSystem system = new TeachingSystem();

        // ���ڳ�ʼ�������б�
        // List<Course> courseList = new ArrayList<Course>();
        // ObjectAndFile.writeCourseToFile(courseList, new
        // File("courseList.txt"));
        //
        // List<Student> studentList = new ArrayList<Student>();
        // ObjectAndFile.writeStudentToFile(studentList, new File(
        // "studentList.txt"));
        //
        // List<Teacher> teacherList = new ArrayList<Teacher>();
        // ObjectAndFile.writeTeacherToFile(teacherList, new File(
        // "teacherList.txt"));
        //
        // List<CourseSelection> courseSelectionList = new
        // ArrayList<CourseSelection>();
        // ObjectAndFile.writeCourseSelectionToFile(courseSelectionList, new
        // File(
        // "courseSelectionList.txt"));

//        while (true) {
//            IOHelper.outputToConsole("�������û�����\n" + "0  ����Ա��1  ѧ����2  ��ʦ");
//            String command = IOHelper.inputFromConsole();
//            if (command.length() >= 2) { // ���������Ƿ�Ϸ�
//                IOHelper.outputToConsole("�������");
//                break;
//            }
//            int cmd = Integer.parseInt(command);
//            switch (cmd) {// ��ת����ͬ����ҳ��
//            case 0:
//                system.managerOperation();
//                break;
//            case 1:
//                system.studentOperation();
//                break;
//            case 2:
//                system.teacherOperation();
//                break;
//            default:
//                IOHelper.outputToConsole("�������!");
//            }
//
//        }
//    }
    
//    public void operation(String userType, String id,String password){
//        switch (userType) {// ��ת����ͬ����ҳ��
//      case "����Ա":
//          managerOperation();
//          break;
//      case "��ʦ":
//          studentOperation();
//          break;
//      case "ѧ��":
//          teacherOperation();
//          break;
//      default:
//         IOHelper.outputToConsole("�������!");
//        }
//        
//    }
//
//    public void managerOperation() {
//        /*
//         * ��������managerOperation �������������ڹ���Ա����
//         */
//        SystemManager manager = new SystemManager();// ����һ���յ�SystemManager
//
//        IOHelper.outputToConsole("����Ա");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("������ָ�");
//            IOHelper.outputToConsole("Login �û���  [����]\n"
//                    + "ChangePassword �û���  [����]\n" + "Delete [���ݱ�����]���\n"
//                    + "Show [���ݱ�����]\n" + "Exit");
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) { // Exit:�˳�����Ա����
//                manager.setLoginOrNot(false);// ���õ�¼״̬Ϊ��
//                return;
//            }
//
//            if (cmd.equals("Login")) {// Login:��¼����
//                /*
//                 * ��SystemManager���login�����л�õ�¼����û���valid user or null��
//                 */
//
//                SystemManager s = manager.login(command);
//                if (s != null) {// ��¼�ɹ�
//                    manager = s;// ����ǰ����������Ϊ��¼���û� ������һ�β���
//                    continue;
//                } else {// ��¼���ɹ� ��������
//                    continue;
//                }
//
//            } else {// ���ΪLogin ������������
//                manager.process(cmd, command);
//            }
//
//        }
//
//    }
//
//    public void studentOperation() {
//        /*
//         * ��������studentOperation �������������ڹ���Ա����
//         */
//        Student student = new Student();// ����һ���յ�Student����
//
//        IOHelper.outputToConsole("ѧ��");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("������ָ�");
//            IOHelper.outputToConsole("Register student [ѧ��] [����] [�û���] [�꼶]\n"
//                    + "Login student [ѧ��]  [����]\n"
//                    + "ChangePassword [ѧ��]  [����]\n" + "Show mycourseList\n"
//                    + "Select co" + "urse\n" + "Quit course [�γ̺�]\n"
//                    + "Show score [�γ̺�]\n" + "Exit");
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) {// Exit:�˳�ѧ����������
//                student.setLoginOrNot(false);// ���õ�¼״̬Ϊ��
//                return;
//            }
//
//            if (cmd.equals("Login")) {// Login:��¼����
//                /*
//                 * ��Student���login�����л�õ�¼����û���valid user or null��
//                 */
//
//                Student s = student.login(command);
//                if (s != null) {// ��¼�ɹ�
//                    student = s;// ����ǰ����������Ϊ��¼���û� ������һ�β���
//                    continue;
//                } else { // ��¼���ɹ� ��������
//                    continue;
//                }
//
//            } else {// ������������
//                student.process(cmd, command);
//                ObjectAndFile.updateStudent(student);// ����һ�β����� �������ݱ�����ض������Ϣ
//            }
//        }
//
//    }
//
//    public void teacherOperation() {
//        /*
//         * ��������teacherOperation ����������������ʦ����
//         */
//        Teacher teacher = new Teacher();// ����һ���յ�Teacher����
//
//        IOHelper.outputToConsole("��ʦ");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("������ָ�");
//            IOHelper.outputToConsole("Register teacher [����] [����] [�û���]\n"
//                    + "ChangePassword [����]  [����]\n"
//                    + "Login teacher [����]  [����]\n" + "Publish\n"
//                    + "Show course [�γ̺�]\n" + "Update course [�γ̺�]\n"
//                    + "Show student [�γ̺�]\n" + "Record score [�γ̺�]\n" + "Exit");
//
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) { // Exit: �˳���ʦ��������
//                teacher.setLoginOrNot(false);// ���õ�¼״̬Ϊ��
//                return;
//            }
//            if (cmd.equals("Login")) { // Login:��¼����
//                /*
//                 * ��Teacher���login�����л�õ�¼����û���valid user or null��
//                 */
//
//                Teacher t = Teacher.login(command);
//                if (t != null) { // ��¼�ɹ�
//                    teacher = t; // ����ǰ����������Ϊ��¼���û� ������һ�β���
//                    continue;
//                } else {
//                    continue; // ��¼���ɹ� ��������
//                }
//            } else { // ������������
//                teacher.process(cmd, command);
//                ObjectAndFile.updateTeacher(teacher);// ����һ�β����� �������ݱ�����ض������Ϣ
//            }
//        }
//
//    }
 }
