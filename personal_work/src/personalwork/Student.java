package personalwork;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Student implements Serializable, Comparable<Student> {
	private String studentName;
	private int studentNo;
	private String password;
	private String studentDegree;
	private boolean loginOrNot = false;
	public List<Course> myCourse = new ArrayList<Course>();
	private List<Score> scoreList = new ArrayList<Score>();

	public Student(int studentNumber, String password, String studentName,
			String studentDegree) {
		this.studentNo = studentNumber;
		this.password = password;
		this.studentName = studentName;
		this.studentDegree = studentDegree;
	}

	public Student() {

	}

	// public static void main(String[] args) {
	// Student student1 = new Student(121250057, "19940731");
	// student1.setStudentDegree("本一");
	// student1.setStudentName("张三");
	// Student student2 = new Student(121250000, "000000");
	// student2.setStudentDegree("本一");
	// student2.setStudentName("李四");
	// List<Student> studentList = new ArrayList<Student>();
	// studentList.add(student1);
	// studentList.add(student2);
	// ObjectAndFile.writeStudentToFile(studentList, new File(
	// "studentList.txt"));
	//
	// }

	public void process(String cmd, String command) {
		/*
		 * 方法名：process方法描述：用于student的具体操作
		 */
		switch (cmd) {
		case "Register":
			register(command);
			return;
		case "Login":
			login(command);
			return;
		case "ChangePassword":
			if (loginOrNot) {// 判断是否已登录
				changePassword(command);
				loginOrNot = false;
				return;
			} else {
				IOHelper.outputToConsole("请先登录!");
				return;
			}
		case "Show":
			if (loginOrNot) {
				show(command);
				return;
			} else {
				IOHelper.outputToConsole("请先登录!");
				return;
			}
		case "Select":
			if (loginOrNot) {
				select(command);
				return;
			} else {
				IOHelper.outputToConsole("请先登录!");
				return;
			}
		case "Quit":
			if (loginOrNot) {
				quit(command);
				return;
			} else {
				IOHelper.outputToConsole("请先登录!");
				return;
			}
		default:
			IOHelper.outputToConsole("输入错误！");
		}
	}

	public void register(String command) {
		/*
		 * 方法名：register 方法描述：用于student注册操作 调用Register的studentRegister方法 Register
		 * student [学号] [密码] [用户名] [年级]
		 */
		Register.studentRegister(command);

	}

	public Student login(String command) {
		/*
		 * 方法名：login 方法描述：用于student登录操作 调用LoginIn类的studentLoginIn方法 Login
		 * student [学号] [密码]
		 * 
		 * @return student对象（valid user or null）
		 */
		return LoginIn.studentLoginIn(command);

	}

	public void changePassword(String command) {
		/*
		 * 方法名：changePassword 方法描述：用于student修改密码操作 ChangePassword [学号] [密码]
		 */

		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str1.length() <= 2 || str2.length() <= 2) {// 判断输入是否为空
			IOHelper.outputToConsole("输入错误！请重新输入");
		} else {
			int studentNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);

			if (!(studentNo == this.getStudentNo())) {// 验证输入的学号是否正确 是否有权限修改
				IOHelper.outputToConsole("输入错误！请重新输入");
				return;
			} else {// 验证通过
				this.setPassword(password);
				IOHelper.outputToConsole("修改成功！请重新登录！");
				return;
			}

		}
	}

	public void show(String command) {
		/*
		 * 方法名：show 方法描述：用于student show mycourseList 和 show score [课程号] 操作
		 */
		String[] split = command.split(" ");
		if (split.length == 2) {// show mycourseList
			if (!split[1].equals("mycourseList")) {// 验证输入是否吻合
				IOHelper.outputToConsole("输入错误！");
				return;
			}
			for (Course c : myCourse) {// 遍历当前student的course数据表 依次输出
				IOHelper.outputToConsole("课程号:" + c.getCourseNo() + "  课程名:"
						+ c.getCourseName() + "  任课教师编号:"
						+ c.getTeacher().getTeacherNo() + "  姓名:"
						+ c.getTeacher().getTeacherName() + "  助教学号:"
						+ c.getAssistant() + "  上课地点:" + c.getPlace()
						+ "  上课时间:" + c.getTime() + "  课时:"
						+ c.getCourseLength() + "  学分:" + c.getCredit() + " "
						+ c.getCompulsoryOrNot());
			}
			return;

		} else {// show score [课程号]
			if (!split[1].equals("score")) {// 判断输入是否吻合
				IOHelper.outputToConsole("输入错误！");
				return;
			}

			String str = split[2];
			if (str == null || str.length() <= 2) {// 判断输入是否合法
				IOHelper.outputToConsole("输入错误！");
				return;
			} else {
				int courseNo = Integer.parseInt(str.substring(1,
						str.length() - 1));
				for (Score s : scoreList) {// 遍历当前student的score数据表 定位指定课程
											// 并输出该课程成绩
					if (s.getCourse().getCourseNo() == courseNo) {
						IOHelper.outputToConsole(s.getCourse().getCourseName()
								+ " " + s.getScore());
						return;
					}
				}
				IOHelper.outputToConsole("不存在该课程！");// 遍历结束 不存在指定课程编号的成绩
				return;
			}
		}
	}

	public void select(String command) {
		/*
		 * 方法名：select 方法描述：用于student选课操作 Select course
		 */
		String[] split = command.split(" ");
		if (!split[1].equals("course") || !(split.length == 2)) {// 判断输入是否吻合
																	// 是否为空
			IOHelper.outputToConsole("输入错误！");
			return;
		}

		List<Course> courseList = ObjectAndFile.loadCourseFile();// 加载课程选课记录数据表
		List<CourseSelection> courseSelectionList = ObjectAndFile
				.loadCourseSelectionFile();

		IOHelper.outputToConsole("所有课程如下：");// 首先输出所有课程
		ShowList.showCourseList();

		IOHelper.outputToConsole("请输入要选择的课程编号 [课程号]");
		String str = IOHelper.inputFromConsole();

		if (str.length() <= 2) {// 判断输入课程号是否为空
			IOHelper.outputToConsole("输入错误！");
		} else {
			int courseNo = Integer.parseInt(str.substring(1, str.length() - 1));
			for (Course c : myCourse) {// 遍历当前已选课程数据表 验证是否已经选择指定课程
				if (c.getCourseNo() == courseNo) {
					IOHelper.outputToConsole("已选该课程！");
					return;// 若已经选择 提示已选择退出当前方法
				}
			}
			for (Course c : courseList) {// 遍历所有课程数据表 定位到指定课程
				if (c.getCourseNo() == courseNo) {
					myCourse.add(c); // 在已选课程中加入指定课程
					courseSelectionList.add(new CourseSelection(// 生成选课记录
							c.getCourseNo(), this.getStudentNo()));

					c.addStudent(this);// 在指定课程的学生列表中添加当前学生

					ObjectAndFile.updateCourse(c);// 更新课程 选课记录数据表
					ObjectAndFile.writeCourseSelectionToFile(
							courseSelectionList, new File(
									"courseSelectionList.txt"));
					IOHelper.outputToConsole("选课成功！");
					return;
				}

			}
			IOHelper.outputToConsole("不存在该课程！请重新输入！");// 遍历结束 不存在指定课程号的课程
			return;
		}
	}

	public void quit(String command) {
		/*
		 * 方法名：quit 方法描述：用于student退选课程 Quit course [课程号]
		 */
		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str2.length() <= 2||!(str1.equals("course"))) {// 判断输入是否吻合 是否为空
			IOHelper.outputToConsole("输入错误！");
		} else {
			int courseNo = Integer.parseInt(str2.substring(1, str2.length() - 1));

			for (int i = 0; i < myCourse.size(); i++) {// 遍历已选课程数据表 定位到指定编号课程
				if (myCourse.get(i).getCourseNo() == courseNo) {
					myCourse.remove(i);// 移除指定课程
					CourseSelection.deleteSelection(this.getStudentNo(), courseNo);// 调用CourseSelection类的delete方法移除该条选课记录
					IOHelper.outputToConsole("退选成功！");
					return;
				}
			}
			IOHelper.outputToConsole("不存在该课程 请重新输入！");//遍历结束 无指定课程编号课程
		}
	}

	public int getStudentNo() {
		return studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getPassword() {
		return password;
	}

	public String getstudentDegree() {
		return studentDegree;
	}

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setStudentName(String name) {
		this.studentName = name;
	}

	public void setStudentDegree(String degree) {
		this.studentDegree = degree;
	}

	public void setLoginOrNot(boolean b) {
		this.loginOrNot = b;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Student o) {
		if (o.studentNo > this.studentNo) {
			return -1;
		} else
			return 1;

	}

}
