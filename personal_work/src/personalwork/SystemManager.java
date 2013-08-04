package personalwork;

import java.io.*;
import java.util.*;

/*类名称：SystemManager
 *类描述：SystemManager类用于管理员
 * 
 */

public class SystemManager {
	@SuppressWarnings("unused")
    private String currentManagerName; // 初始的管理员账号和密码均为admin
	private String currentManagerCode;
	private String filename = "managerInfoList.txt";
	private boolean loginOrNot = false;

	
	public SystemManager() {

	}

	public SystemManager(String currentManagerName, String currentManagerCode) {
		this.currentManagerName = currentManagerName;
		this.currentManagerCode = currentManagerCode;

	}

	
	public void process(String cmd, String command) {
		/*
		 * 方法名：process 方法描述：用于跳转到具体的管理员操作
		 */
		switch (cmd) {
		case "Login":
			login(command);
			break;
		case "ChangePassword":
			if (loginOrNot) { // 判断是否已经登录
				changePassword(command);
				break;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;
			}
		case "Show":
			if (loginOrNot) {
				show(command);
				break;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;
			}
		case "Delete":
			if (loginOrNot) {
				delete(command);
				return;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;
			}
		default: {
			System.out.println("输入不合法！");
			break;
		}
		}
	}

	public SystemManager login(String command) {
		/* 方法名：login 方法描述：用于管理员的登录操作 */
		 return LoginIn.managerLoginIn(command);
	}

	public void changePassword(String command) {
		/*
		 * 方法名：changePassword 方法描述：用于管理员的密码修改操作
		 */
		String[] split = command.split(" ");
		int length = split[2].length();
		String newManagerCode = split[2].substring(1, length - 1);

		if (split[1].equals("admin")) { // 管理员用户名始终为初始用户名
			if (newManagerCode.length() < 5) { // 限制密码长度必须大于等于4
				System.out.println("密码长度过短！");
				return;
			} else {
				currentManagerCode = newManagerCode;
				loginOrNot=false;
				ObjectAndFile.writeManagerInfoToFile("admin", currentManagerCode,
						filename);// 写入新的管理员信息
				System.out.println("密码修改成功！");
				return;
			}
		} else {
			System.out.println("输入错误！");
			return;
		}

	}

	public void show(String command) {
		/*
		 * 方法名：show 方法描述：用于管理员的查看列表功能
		 */
		String[] split = command.split(" ");
		int length = split[1].length();
		String listToShow = split[1].substring(1, length - 1);
		
		switch (listToShow) {
		case "studentList":
			ShowList.showStudentList();
			break;
		case "teacherList":
			ShowList.showTeacherList();
			break;
		case "courseList":
			ShowList.showCourseList();
			break;
		case "courseSelectionList":
			ShowList.showCourseSelectionList();
			break;
		default:
			IOHelper.outputToConsole("输入错误！");

		}

	}

	public void delete(String command) {
		/*
		 * 方法名：delete方法描述：用于管理员的删除信息功能
		 */
		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];
		String listToDelete = str1.substring(1, str1.length() - 1);
		int recordToDelete = Integer.parseInt(str2.substring(1,
				str2.length() - 1)); // 获取要删除的记录编号
		switch (listToDelete) {
		case "studentList": {
			List<Student> studentList = ObjectAndFile.loadStudentFile(); // 加载学生记录数据表

			for (int i = 0; i < studentList.size(); i++) {
				if (studentList.get(i).getStudentNo() == recordToDelete) {
					studentList.remove(i);
					ObjectAndFile.writeStudentToFile(studentList, new File(
							"studentList.txt"));
					IOHelper.outputToConsole("删除成功！");
					return;
				}
			}
			IOHelper.outputToConsole("没有找到相关记录！");

			break;
		}
		case "teacherList": {

			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();

			for (int i = 0; i < teacherList.size(); i++) {
				if (teacherList.get(i).getTeacherNo() == recordToDelete) {
					teacherList.remove(i);
					ObjectAndFile.writeTeacherToFile(teacherList, new File(
							"teacherList.txt"));
					IOHelper.outputToConsole("删除成功！");
					return;
				}
			}
			IOHelper.outputToConsole("没有找到相关记录！");

			break;

		}
		case "courseList": {
			List<Course> courseList = ObjectAndFile.loadCourseFile();
			for (int i = 0; i < courseList.size(); i++) {
				if (courseList.get(i).getCourseNo() == recordToDelete) {
					courseList.remove(i);
					ObjectAndFile.writeCourseToFile(courseList, new File(
							"courseList.txt"));
					IOHelper.outputToConsole("删除成功！");
					return;
				}
			}
			IOHelper.outputToConsole("没有找到相关记录！");
			break;
		}
		case "courseSelectionList": {
			List<CourseSelection> courseSelectionList = ObjectAndFile
					.loadCourseSelectionFile();
			for (int i = 0; i < courseSelectionList.size(); i++) {
				if (i==recordToDelete) {
					courseSelectionList.remove(i);
					ObjectAndFile.writeCourseSelectionToFile(
							courseSelectionList, new File(
									"courseSelectionList.txt"));
					IOHelper.outputToConsole("删除成功！");
					return;
				}
			}
			IOHelper.outputToConsole("没有找到相关记录！");
			break;
		}

		}

	}

	public void setLoginOrNot(boolean b) {
		loginOrNot = b;
	}
}
