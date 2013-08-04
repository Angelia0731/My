package personalwork;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Teacher implements Serializable, Comparable<Teacher> {
	private int teacherNo;
	private String password;
	private String teacherName;
	private static boolean loginOrNot = false;

	public Teacher() {

	}

	public Teacher(int teacherNumber, String password, String teacherName) {
		this.teacherNo = teacherNumber;
		this.password = password;
		this.teacherName = teacherName;

	}

	// public Teacher(String teacherName, int teacherNo) {
	// this.teacherNo = teacherNo;
	// this.teacherName = teacherName;
	//
	// }

	// public static void main(String[] args) {
	// Teacher teacher1 = new Teacher(1, "000000");
	// teacher1.setTeacherName("张小三");
	// Teacher teacher2 = new Teacher(2, "000000");
	// teacher2.setTeacherName("李小四");
	// List<Teacher> teacherList = new ArrayList<Teacher>();
	// teacherList.add(teacher1);
	// teacherList.add(teacher2);
	//
	// ObjectAndFile.writeTeacherToFile(teacherList, new File(
	// "teacherList.txt"));
	//
	// }

	public void process(String cmd, String command) {
		/*
		 * 方法名：process 方法描述：用于teacher 除了Login外的具体操作
		 */
		switch (cmd) {
		case "Register":
			register(command);// register操作直接进行 无需登录
			break;
		case "ChangePassword":
			if (loginOrNot) {// 先判断是否已经登录
				changePassword(command);
				loginOrNot = false;
				break;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;
			}
		case "Publish":
			if (loginOrNot) {
				publish();
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
		case "Update":
			if (loginOrNot) {
				update(command);
				break;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;

			}
		case "Record":
			if (loginOrNot) {
				record(command);
				break;
			} else {
				IOHelper.outputToConsole("请先登录!");
				break;

			}

		default:
			IOHelper.outputToConsole("输入错误！");
			break;
		}
	}

	public void register(String command) {
		/*
		 * 方法名：register 方法描述：teacher的Register操作 调用Register类的teacherRegister方法
		 */
		Register.teacherRegister(command);

	}

//	public static Teacher login(String command) {
//		/*
//		 * 方法名：login 方法描述teacher的Login操作 调用LoginIn类的teacherLoginIn方法
//		 * 
//		 * @return 返回一个Teacher对象（ valid or null）
//		 */
//		return LoginIn.teacherLoginIn(command);
//
//	}

	public void changePassword(String command) {
		/*
		 * 方法名：changePassword 方法描述：用于Teacher 修改密码操作
		 */

		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str1.length() <= 2 || str2.length() <= 2) {// 判断输入是否为空
			IOHelper.outputToConsole("输入错误！请重新输入");
		} else {
			int teacherNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);

			if (!(teacherNo == this.getTeacherNo())) {// 只能修改当前用户 否则操作不合法
				IOHelper.outputToConsole("输入错误！请重新输入");
				return;
			} else {
				this.setPassword(password);// 验证通过 修改密码
				IOHelper.outputToConsole("修改成功！请重新登录！");
				return;
			}

		}
	}

	public void publish() {
		/*
		 * 方法名；publish 方法描述：用于teacher 发布课程操作
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();// 加载课程数据表

		while (true) {
			boolean existOrNot = false; // 判断课程是否已经存在

			IOHelper.outputToConsole("请输入课程编号");
			String courseNo = IOHelper.inputFromConsole();

			if (courseNo == null || courseNo.length() == 0) {// 输入不合法 重新输入
				IOHelper.outputToConsole("输入错误！请重新输入");
				continue;
			} else { // 输入合法
				int current_course_no = Integer.parseInt(courseNo);// 获得要发布课程的课程号

				for (Course s : courseList) {// 遍历已有的课程列表 查询是否存在
					if (current_course_no == s.getCourseNo()) {
						IOHelper.outputToConsole("已存在该编号的课程!请重新输入");
						existOrNot = true; // 若已经存在 退出遍历
						break;
					}
				}
				if (existOrNot) // 若已经存在 重新输入
					continue;
				else {

					Course course = new Course();// 创建一个临时Course对象

					course.setCourseNo(courseNo);
					IOHelper.outputToConsole("请输入课程名称：");
					String courseName = IOHelper.inputFromConsole();
					course.setCourseName(courseName);

					IOHelper.outputToConsole("请输入课时：");
					String courseLength = IOHelper.inputFromConsole();
					course.setCourseLength(courseLength);

					IOHelper.outputToConsole("请输入上课时间：");
					String courseTime = IOHelper.inputFromConsole();
					course.setTime(courseTime);

					IOHelper.outputToConsole("请输入上课地点：");
					String place = IOHelper.inputFromConsole();
					course.setPlace(place);

					IOHelper.outputToConsole("请输入学分数：");
					String credit = IOHelper.inputFromConsole();
					course.setCredit(Integer.parseInt(credit));

					IOHelper.outputToConsole("请输入是否为必修课（是或否）：");
					course.setCompulsoryOrNot(IOHelper.inputFromConsole());

					IOHelper.outputToConsole("请输入助教学号.");
					course.setAssistant(IOHelper.inputFromConsole());

					course.setTeacher(this); // 设置course的任课教师为当前操作的老师

					courseList.add(course);// 在courseList中加入当前课程

					ObjectAndFile.writeCourseToFile(courseList, new File(
							"courseList.txt"));// 写入更新后的courseList

					IOHelper.outputToConsole("已成功添加!");
					return;
				}
			}
		}

	}

	public void show(String command) {
		/*
		 * 方法名：show 方法描述：用于teacher的show course/show student操作 
		 *  show course [**]展示所有课程 show course [*] 展示当前用户发布的课程 show course [课程号] 展示指定课程号课程信息
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();// 加载课程数据表

		String[] split = command.split(" ");
		String cmd = split[1];

		switch (cmd) {
		case "course":
			String str1 = split[2];
			String consultType = str1.substring(1, str1.length() - 1);

			if (str1 == null || str1.length() <= 2) {// 判断输入课程号是否为空
				IOHelper.outputToConsole("输入错误！");
				return;
			} else {
				if (consultType.equals("**")) {// 输入为[**]
					ShowList.showCourseList();// 调用ShowList类的showCourseList方法
												// 展示所有课程
					return;
				}
				if (consultType.equals("*")) {// 输入为[*]
					for (Course c : courseList) {// 遍历courseList
						if (c.getTeacher().getTeacherNo() == this.teacherNo) {// 验证当前课程的任课教师是否为当前操作用户
																				// 验证通过则输出该课程信息
							IOHelper.outputToConsole("课程号:" + c.getCourseNo()
									+ "  课程名:" + c.getCourseName()
									+ "  任课教师编号:"
									+ c.getTeacher().getTeacherNo() + "  姓名:"
									+ c.getTeacher().getTeacherName()
									+ "  助教学号:" + c.getAssistant() + "  上课地点:"
									+ c.getPlace() + "  上课时间:" + c.getTime()
									+ "  课时:" + c.getCourseLength() + "  学分:"
									+ c.getCredit() + " "
									+ c.getCompulsoryOrNot());
						}
					}
					return;
				} else {//查看指定课程号的课程信息
					int consult_course_no = Integer.parseInt(consultType);
					for (Course c : courseList) {//遍历课程数据表  查询指定课程信息并输出
						if (consult_course_no == c.getCourseNo()) {
							IOHelper.outputToConsole("课程号:" + c.getCourseNo()
									+ "  课程名:" + c.getCourseName()
									+ "  任课教师编号:"
									+ c.getTeacher().getTeacherNo() + "  姓名:"
									+ c.getTeacher().getTeacherName()
									+ "  助教学号:" + c.getAssistant() + "  上课地点:"
									+ c.getPlace() + "  上课时间:" + c.getTime()
									+ "  课时:" + c.getCourseLength() + "  学分:"
									+ c.getCredit() + " "
									+ c.getCompulsoryOrNot());
							return; //找到指定课程  即退出当前方法

						}
					}
					IOHelper.outputToConsole("不存在该课程！");//遍历结束没有找到相关课程 提示不存在该课程
					return;//退出当前方法
				}
			}

		case "student":
			String str2 = split[2];
			
			if (str2 == null || str2.length() <= 2) {//判断输入是否为空
				IOHelper.outputToConsole("输入错误！");
				return;
			} else {
				int courseNo = Integer.parseInt(str2.substring(1,
						str2.length() - 1));
				for (Course c : courseList) {//遍历课程数据表 查询指定课程号的课程
					if (c.getCourseNo() == courseNo) {//定位指定课程 遍历其studentList 输出学生信息
						List<Student> studentList = c.getStudent();
						for (Student s : studentList) {
							IOHelper.outputToConsole("学号:  " + s.getStudentNo()
									+ "  姓名:" + s.getStudentName() + "  年级:"
									+ s.getstudentDegree());
						}
						return;//定位到指定课程后 即退出当前方法
					}
				}
				IOHelper.outputToConsole("不存在该课程！请重新输入！");//遍历结束没有找到指定编号的课程 提示不存在该课程
				return;
			}
		default:
			IOHelper.outputToConsole("输入错误！");//输入不合法
            return;
		}
	}

	public void update(String command) {
		/*
		 * 方法名：update
		 * 方法描述：用于老师的更新课程信息操作
		 */

		List<Course> courseList = ObjectAndFile.loadCourseFile();//加载课程数据表

		String[] split = command.split(" ");
		String str1=split[1];
		String str2 = split[2];
		
		if(!str1.equals("course")){//判断输入是否吻合
			IOHelper.outputToConsole("输入错误！");
			return;
			}

		if (str2.length() <= 2) {//判断输入课程号是否为空
			IOHelper.outputToConsole("输入错误！");
			return;
		} else {
			int courseNo = Integer.parseInt(str2.substring(1, str2.length() - 1));
			
			for (int i = 0; i < courseList.size(); i++) {//遍历课程数据表
				Course c = courseList.get(i);
				if (c.getCourseNo() == courseNo) {//定位到指定课程
					if (c.getTeacher().getTeacherNo() == this.teacherNo) {//验证当前课程任课教师是否是当前操作用户 验证通过则进行update课程操作
						IOHelper.outputToConsole("课程号:" + c.getCourseNo()
								+ "  课程名:" + c.getCourseName() + "  任课教师编号:"
								+ c.getTeacher().getTeacherNo() + "  姓名:"
								+ c.getTeacher().getTeacherName() + "  助教学号:"
								+ c.getAssistant() + "  上课地点:" + c.getPlace()
								+ "  上课时间:" + c.getTime() + "  课时:"
								+ c.getCourseLength() + "  学分:" + c.getCredit()
								+ " " + c.getCompulsoryOrNot());
						
						Course course = new Course();//创建一个空的临时Course对象

						IOHelper.outputToConsole("请输入新的课程编号：");
						String course_No = IOHelper.inputFromConsole();
						course.setCourseNo(course_No);
						IOHelper.outputToConsole("请输入新的课程名称：");
						String courseName = IOHelper.inputFromConsole();
						course.setCourseName(courseName);

						IOHelper.outputToConsole("请输入新的课时：");
						String courseLength = IOHelper.inputFromConsole();
						course.setCourseLength(courseLength);

						IOHelper.outputToConsole("请输入新的上课时间：");
						String courseTime = IOHelper.inputFromConsole();
						course.setTime(courseTime);

						IOHelper.outputToConsole("请输入新的上课地点：");
						String place = IOHelper.inputFromConsole();
						course.setPlace(place);

						IOHelper.outputToConsole("请输入新的学分数：");
						String credit = IOHelper.inputFromConsole();
						course.setCredit(Integer.parseInt(credit));

						IOHelper.outputToConsole("请输入是否为必修课（是或否）：");
						course.setCompulsoryOrNot(IOHelper.inputFromConsole());

						IOHelper.outputToConsole("请输入助教学号.");
						course.setAssistant(IOHelper.inputFromConsole());

						course.setTeacher(this);

						if (Integer.parseInt(course_No) == courseNo) {
							c = course;//如果修改后的课程课程编号没有改变 直接用新的课程替换原来的课程
							ObjectAndFile.writeCourseToFile(courseList,
									new File("courseList.txt"));
							IOHelper.outputToConsole("更新成功！");
							return;

						} else {//修改了课程编号
							for (Course s : courseList) {//遍历课程数据表 进行重复性验证
								int current_course_no = Integer
										.parseInt(course_No);
								if (s.getCourseNo() == current_course_no) {
									IOHelper.outputToConsole("已存在该编号的课程!更新失败");
									return; //课程号重复 更新失败 退出当前方法
								}
							}
							courseList.remove(i);//经过遍历没有重复课程号  在数据表中移除原课程 添加新课程
							courseList.add(course);
							ObjectAndFile.writeCourseToFile(courseList,
									new File("courseList.txt"));//更新数据表
							IOHelper.outputToConsole("更新成功！");
							return;
						}

					} else {
						IOHelper.outputToConsole("不存在该编号的你创建的课程！无权限修改！");
						return;
					}
				}
			}
			IOHelper.outputToConsole("不存在该课程！");//经过遍历课程数据表 没有找到指定课程号的课程
			return;
		}

	}

	public void record(String command) {
		/*
		 * 方法名：record
		 * 方法描述：用于老师记录课程成绩操作
		 */

		String[] split = command.split(" ");
		String str1=split[1];
		String str2 = split[2];

		if (!(str1.equals("score"))) {//判断输入是否吻合
			IOHelper.outputToConsole("输入错误！");
			return;
		}
		if (str2 == null || str2.length() <= 2) {//判读输入课程号是否为空
			IOHelper.outputToConsole("输入错误！");
			return;
		} else {
			int courseNo = Integer
					.parseInt(str2.substring(1, str2.length() - 1));
			
			List<Course> courseList = ObjectAndFile.loadCourseFile();//加载课程数据表
			for (Course c : courseList) {//遍历课程数据表 定位到指定编号的课程
				if (c.getCourseNo() == courseNo) {
					if (c.getTeacher().getTeacherNo() == this.getTeacherNo()) {//验证是否有权限进行成绩录入操作
						c.updateScore();//验证通过 调用Course的更新课程成绩方法
						return;
					} else {
						IOHelper.outputToConsole("无权限！");//验证不通过 提示无权限 退出当前方法
						return;
					}
				}
			}
			IOHelper.outputToConsole("不存在该课程！");//遍历结束 不存在指定课程号的课程
			return;

		}

	}

	public int getTeacherNo() {
		return teacherNo;
	}

	public String getPassword() {
		return password;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setLoginOrNot(boolean b) {
		loginOrNot = b;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Teacher o) {
		// TODO Auto-generated method stub
		if (o.getTeacherNo() > this.getTeacherNo()) {
			return -1;
		} else
			return 1;
	}

}
