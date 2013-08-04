package personalwork;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Course implements Serializable, Comparable<Course> {
	private int courseNo;
	private String courseName;
	private int courseLength;
	private String isCompulsory;
	private int credit;
	private Teacher teacher;
	private String time;
	private String place;
	private int assistantNo;
	private List<Student> studentList = new ArrayList<Student>();

	public Course() {

	}

	// public Course(int courseNumber, String courseName,int courseLength,String
	// isCompulsory,int credit,String time,String place,Teacher teacher,int
	// assistantNo) {
	// this.courseNo = courseNumber;
	// this.courseName = courseName;
	// this.courseLength=courseLength;
	// this.isCompulsory=isCompulsory;
	// this.credit=credit;
	// this.time=time;
	// this.place=place;
	// this.teacher=teacher;
	// this.assistantNo=assistantNo;
	// }

	// public static void main(String[] args) {
	//
	// Course course=new Course(1, "软件工程",3,"必修课",3,"周二上午3到4节","仙二403",new
	// Teacher("张小三",1),1);
	// List<Course> courseList = new ArrayList<Course>();
	// courseList.add(course);
	// ObjectAndFile.writeCourseToFile(courseList, new File("courseList.txt"));
	// }

	public void updateScore() {
		/*
		 * 方法名：updateScore 方法名：供Teacher类调用 录入课程成绩
		 */
		for (Student s : studentList) {// 遍历当前课程的学生列表 输出学生信息 录入课程成绩
			IOHelper.outputToConsole(s.getStudentNo() + " "
					+ s.getStudentName());
			IOHelper.outputToConsole("请输入该学生" + this.courseName + "课的成绩：");

			String score = IOHelper.inputFromConsole();// 据输入的score信息创建Score对象
			Score temp = new Score(this, score);

			s.getScoreList().add(temp);// 在当前学生的score列表中加入当前score对象
			ObjectAndFile.updateStudent(s);// 更新学生数据表
		}
		IOHelper.outputToConsole("录入成功！");

	}

	public void setCourseNo(String courseNo) {
		this.courseNo = Integer.parseInt(courseNo);
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseLength(String CourseLength) {
		this.courseLength = Integer.parseInt(CourseLength);
	}

	public void setCompulsoryOrNot(String isCompulsory) {
		String result = "必修课";
		if (isCompulsory.equals("否"))
			result = "选修课";
		this.isCompulsory = result;

	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setAssistant(String assistantNo) {
		this.assistantNo = Integer.parseInt(assistantNo);
	}

	public void setTeacher(Teacher t) {
		this.teacher = t;
	}

	public void addStudent(Student s) {
		/*方法名：addStudent
		 * 方法描述：在学生列表中添加学生
		 * 
		 */
		for (Student temp : studentList) {//遍历学生列表 若已经存在该学生 退出当前犯法
			if (temp.getStudentNo() == s.getStudentNo()) {
				return;
			}
		}
		this.studentList.add(s);//遍历结束 无重复学生 加入学生信息
	}

	public int getCourseNo() {
		return courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public int getCourseLength() {
		return courseLength;
	}

	public String getCompulsoryOrNot() {
		return isCompulsory;

	}

	public int getCredit() {
		return credit;
	}

	public String getTime() {
		return time;
	}

	public String getPlace() {
		return place;
	}

	public int getAssistant() {
		return assistantNo;
	}

	public List<Student> getStudent() {
		return studentList;
	}

	@Override
	public int compareTo(Course o) {
		// TODO Auto-generated method stub
		if (o.courseNo > this.courseNo)
			return -1;
		else
			return 1;
	}

}
