package personalwork;

//import java.io.*;
//import java.util.*;

public class TeachingSystem {

//    public static void main(String[] args) {
//        TeachingSystem system = new TeachingSystem();

        // 用于初始化所有列表
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
//            IOHelper.outputToConsole("请输入用户类型\n" + "0  管理员；1  学生；2  老师");
//            String command = IOHelper.inputFromConsole();
//            if (command.length() >= 2) { // 检验输入是否合法
//                IOHelper.outputToConsole("输入错误！");
//                break;
//            }
//            int cmd = Integer.parseInt(command);
//            switch (cmd) {// 跳转到不同操作页面
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
//                IOHelper.outputToConsole("输入错误!");
//            }
//
//        }
//    }
    
//    public void operation(String userType, String id,String password){
//        switch (userType) {// 跳转到不同操作页面
//      case "管理员":
//          managerOperation();
//          break;
//      case "教师":
//          studentOperation();
//          break;
//      case "学生":
//          teacherOperation();
//          break;
//      default:
//         IOHelper.outputToConsole("输入错误!");
//        }
//        
//    }
//
//    public void managerOperation() {
//        /*
//         * 方法名：managerOperation 方法描述：用于管理员操作
//         */
//        SystemManager manager = new SystemManager();// 创建一个空的SystemManager
//
//        IOHelper.outputToConsole("管理员");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("请输入指令：");
//            IOHelper.outputToConsole("Login 用户名  [密码]\n"
//                    + "ChangePassword 用户名  [密码]\n" + "Delete [数据表名称]编号\n"
//                    + "Show [数据表名称]\n" + "Exit");
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) { // Exit:退出管理员界面
//                manager.setLoginOrNot(false);// 设置登录状态为否
//                return;
//            }
//
//            if (cmd.equals("Login")) {// Login:登录操作
//                /*
//                 * 从SystemManager类的login方法中获得登录后的用户（valid user or null）
//                 */
//
//                SystemManager s = manager.login(command);
//                if (s != null) {// 登录成功
//                    manager = s;// 将当前操作对象设为登录的用户 进行下一次操作
//                    continue;
//                } else {// 登录不成功 重新输入
//                    continue;
//                }
//
//            } else {// 命令不为Login 进行其他操作
//                manager.process(cmd, command);
//            }
//
//        }
//
//    }
//
//    public void studentOperation() {
//        /*
//         * 方法名：studentOperation 方法描述：用于管理员操作
//         */
//        Student student = new Student();// 创建一个空的Student对象
//
//        IOHelper.outputToConsole("学生");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("请输入指令：");
//            IOHelper.outputToConsole("Register student [学号] [密码] [用户名] [年级]\n"
//                    + "Login student [学号]  [密码]\n"
//                    + "ChangePassword [学号]  [密码]\n" + "Show mycourseList\n"
//                    + "Select co" + "urse\n" + "Quit course [课程号]\n"
//                    + "Show score [课程号]\n" + "Exit");
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) {// Exit:退出学生操作界面
//                student.setLoginOrNot(false);// 设置登录状态为否
//                return;
//            }
//
//            if (cmd.equals("Login")) {// Login:登录操作
//                /*
//                 * 从Student类的login方法中获得登录后的用户（valid user or null）
//                 */
//
//                Student s = student.login(command);
//                if (s != null) {// 登录成功
//                    student = s;// 将当前操作对象设为登录的用户 进行下一次操作
//                    continue;
//                } else { // 登录不成功 重新输入
//                    continue;
//                }
//
//            } else {// 进行其他操作
//                student.process(cmd, command);
//                ObjectAndFile.updateStudent(student);// 进行一次操作后 更新数据表中相关对象的信息
//            }
//        }
//
//    }
//
//    public void teacherOperation() {
//        /*
//         * 方法名：teacherOperation 方法描述：用于老师操作
//         */
//        Teacher teacher = new Teacher();// 创建一个空的Teacher对象
//
//        IOHelper.outputToConsole("老师");
//        IOHelper.outputToConsole("-----------------------------------------");
//
//        while (true) {
//            IOHelper.outputToConsole("请输入指令：");
//            IOHelper.outputToConsole("Register teacher [工号] [密码] [用户名]\n"
//                    + "ChangePassword [工号]  [密码]\n"
//                    + "Login teacher [工号]  [密码]\n" + "Publish\n"
//                    + "Show course [课程号]\n" + "Update course [课程号]\n"
//                    + "Show student [课程号]\n" + "Record score [课程号]\n" + "Exit");
//
//            String command = IOHelper.inputFromConsole();
//            String[] split = command.split(" ");
//            String cmd = split[0];
//
//            if (cmd.equals("Exit")) { // Exit: 退出教师操作界面
//                teacher.setLoginOrNot(false);// 设置登录状态为否
//                return;
//            }
//            if (cmd.equals("Login")) { // Login:登录操作
//                /*
//                 * 从Teacher类的login方法中获得登录后的用户（valid user or null）
//                 */
//
//                Teacher t = Teacher.login(command);
//                if (t != null) { // 登录成功
//                    teacher = t; // 将当前操作对象设为登录的用户 进行下一次操作
//                    continue;
//                } else {
//                    continue; // 登录不成功 重新输入
//                }
//            } else { // 进行其他操作
//                teacher.process(cmd, command);
//                ObjectAndFile.updateTeacher(teacher);// 进行一次操作后 更新数据表中相关对象的信息
//            }
//        }
//
//    }
 }
