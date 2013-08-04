package personalwork;

import java.io.File;
import java.util.List;

public class Register {
	public String command;
	int userType = 0;

	public Register(int userType, String command) {
		this.userType = userType;
		this.command = command;
	}

	public static void studentRegister(String command) {
		/*
		 * 方法名：studentRegister
		 * 方法描述：用于学生的注册操作
		 */
		String[] split = command.split(" ");
		String str1 = split[2];
		String str2 = split[3];
		String str3 = split[4];
		String str4 = split[5];

		if (str1.length() <= 2 || str2.length() <= 2 || str3.length() <= 2
				|| str4.length() <= 2||!(split[1].equals("student"))) {
			IOHelper.outputToConsole("输入错误！");
		} else {
			int studentNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);
			String studentName = str3.substring(1, str3.length()-1);
			String studentDegree = str4.substring(1, str4.length() - 1);
			List<Student> studentList = ObjectAndFile.loadStudentFile();

			for (Student s : studentList) {//遍历student数据表 验证是否已经存在指定学号账户
				if (studentNo == s.getStudentNo()) {//指定学号已被注册 退出当前方法
					IOHelper.outputToConsole("已存在该账户");
					return;
				}
			}

			studentList.add(new Student(studentNo, password, studentName,
					studentDegree));//通过遍历  无重复学号 在数据表中添加新的学生
			ObjectAndFile.writeStudentToFile(studentList, new File(
					"studentList.txt"));//更新数据表
			IOHelper.outputToConsole("注册成功！请登录。");
		}
	}

	public static void teacherRegister(String command) {
		/*
		 * 方法名：teacherRegister
		 * 方法描述：用于老师的注册操作
		 */
		String[] split = command.split(" ");
		String str1 = split[2];
		String str2 = split[3];
		String str3 = split[4];

		if (str1.length() <= 2 || str2.length() <= 2||str3.length()<=2||!(split[1].equals("teacher"))) {
			IOHelper.outputToConsole("输入错误！");
		} else {
			int teacherNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);
			String teacherName = str3.substring(1, str3.length() - 1);
			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();//加载教师数据表
			
			for (Teacher s : teacherList) {//遍历教师数据表 验证是否已经存在指定工号账户
				if (teacherNo == s.getTeacherNo()) {//指定工号已经存在 退出当前方法
					IOHelper.outputToConsole("已存在该账户");
					return;
				}
			}
			teacherList.add(new Teacher(teacherNo, password, teacherName));//通过遍历 无重复工号 在数据表中加入新的teacher
			ObjectAndFile.writeTeacherToFile(teacherList, new File(
					"teacherList.txt"));//更新teacher数据表
			IOHelper.outputToConsole("注册成功！请登录。");

		}

	}

}
