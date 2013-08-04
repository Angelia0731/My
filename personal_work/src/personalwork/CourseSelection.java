package personalwork;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class CourseSelection implements Serializable,
		Comparable<CourseSelection> {
	int courseNo;
	int studentNo;

	// public CourseSelection(int recordNumber, int courseNo, int studentNo) {
	// this.recordNumber = recordNumber;
	// this.courseNo = courseNo;
	// this.studentNo = studentNo;
	// }

	public CourseSelection(int courseNo, int studentNo) {
		this.courseNo = courseNo;
		this.studentNo = studentNo;
	}

	// public static void main(String[] args) {
	// CourseSelection s1=new CourseSelection(0,1,1);
	// List<CourseSelection> courseSelectionList=new
	// ArrayList<CourseSelection>();
	// courseSelectionList.add(s1);
	// ObjectAndFile.writeCourseSelectionToFile(courseSelectionList, new File(
	// "courseSelectionList.txt"));
	//
	// }

	public static void deleteSelection(int studentNo, int courseNo) {
		/*
		 * 方法名：delete 方法描述：用于删除选课记录
		 */
		List<CourseSelection> courseSelectionList = ObjectAndFile// 加载选课记录数据表
				.loadCourseSelectionFile();

		for (int i = 0; i < courseSelectionList.size(); i++) {// 遍历数据表 删除指定选课记录
			CourseSelection s = courseSelectionList.get(i);
			if (s.getStudentNo() == studentNo && s.getCourseNo() == courseNo) {
				courseSelectionList.remove(i);
				ObjectAndFile.writeCourseSelectionToFile(courseSelectionList,
						new File("courseSelectionList.txt"));
				return;
			}

		}

	}

	public int getCourseNo() {
		return courseNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	@Override
	public int compareTo(CourseSelection o) {
		// TODO Auto-generated method stub
		if (o.getCourseNo() > this.getCourseNo())
			return -1;
		else
			return 1;
	}

}
