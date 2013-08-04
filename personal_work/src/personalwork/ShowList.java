package personalwork;

import java.util.Collections;
import java.util.List;

public class ShowList {
	
	public static void showStudentList() {
		/*
		 * 方法名：showStudentList
		 * 方法描述：用于遍历student数据表 并依次输出各项信息
		 */
		List<Student> studentList = ObjectAndFile.loadStudentFile();
		Collections.sort(studentList); // 给文件中的Student对象排序
		
		for (Student i : studentList) {
			IOHelper.outputToConsole("学号:  " + i.getStudentNo() + "  姓名:"
					+ i.getStudentName() + "  年级:" + i.getstudentDegree());
		}
	}

	public static void showCourseList() {
		/*
		 * 方法名：showCourseList
		 * 方法描述：用于遍历course数据表 并依次输出各项信息
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();
		Collections.sort(courseList);// 给文件中的Course对象排序
		
		for (Course c : courseList) {
			IOHelper.outputToConsole("课程号:" + c.getCourseNo() + "  课程名:"
					+ c.getCourseName() + "  任课教师编号:"
					+ c.getTeacher().getTeacherNo() + "  姓名:"
					+ c.getTeacher().getTeacherName() + "  助教学号:"
					+ c.getAssistant() + "  上课地点:" + c.getPlace() + "  上课时间:"
					+ c.getTime() + "  课时:" + c.getCourseLength() + "  学分:"
					+ c.getCredit() + " " + c.getCompulsoryOrNot());
		}
	}

	public static void showTeacherList() {
		/*
		 * 方法名：showTeacherList
		 * 方法描述：用于遍历teacher数据表 并依次输出各项信息
		 */
		List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();
		Collections.sort(teacherList);//给文件中的Teacher对象排序
		
		for (Teacher i : teacherList) {
			IOHelper.outputToConsole("教师编号:  " + i.getTeacherNo() + "  教师姓名:"
					+ i.getTeacherName());
		}
	}

	public static void showCourseSelectionList() {
		/*
		 * 方法名：showCourseSelectionList
		 * 方法描述：用于遍历CourseSelection数据表 并依次输出各项信息
		 */
		List<CourseSelection> courseSelectionList = ObjectAndFile
				.loadCourseSelectionFile();
		Collections.sort(courseSelectionList);//给文件中的CourseSelection对象排序
		
		for (int i = 0; i < courseSelectionList.size(); i++) {
			CourseSelection c = courseSelectionList.get(i);
			IOHelper.outputToConsole("编号:" + i + "  课程号:" + c.getCourseNo()
					+ "  学生编号:" + c.getStudentNo());
		}
	}

}
