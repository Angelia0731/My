package personalwork;

import java.util.Collections;
import java.util.List;

public class ShowList {
	
	public static void showStudentList() {
		/*
		 * ��������showStudentList
		 * �������������ڱ���student���ݱ� ���������������Ϣ
		 */
		List<Student> studentList = ObjectAndFile.loadStudentFile();
		Collections.sort(studentList); // ���ļ��е�Student��������
		
		for (Student i : studentList) {
			IOHelper.outputToConsole("ѧ��:  " + i.getStudentNo() + "  ����:"
					+ i.getStudentName() + "  �꼶:" + i.getstudentDegree());
		}
	}

	public static void showCourseList() {
		/*
		 * ��������showCourseList
		 * �������������ڱ���course���ݱ� ���������������Ϣ
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();
		Collections.sort(courseList);// ���ļ��е�Course��������
		
		for (Course c : courseList) {
			IOHelper.outputToConsole("�γ̺�:" + c.getCourseNo() + "  �γ���:"
					+ c.getCourseName() + "  �ον�ʦ���:"
					+ c.getTeacher().getTeacherNo() + "  ����:"
					+ c.getTeacher().getTeacherName() + "  ����ѧ��:"
					+ c.getAssistant() + "  �Ͽεص�:" + c.getPlace() + "  �Ͽ�ʱ��:"
					+ c.getTime() + "  ��ʱ:" + c.getCourseLength() + "  ѧ��:"
					+ c.getCredit() + " " + c.getCompulsoryOrNot());
		}
	}

	public static void showTeacherList() {
		/*
		 * ��������showTeacherList
		 * �������������ڱ���teacher���ݱ� ���������������Ϣ
		 */
		List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();
		Collections.sort(teacherList);//���ļ��е�Teacher��������
		
		for (Teacher i : teacherList) {
			IOHelper.outputToConsole("��ʦ���:  " + i.getTeacherNo() + "  ��ʦ����:"
					+ i.getTeacherName());
		}
	}

	public static void showCourseSelectionList() {
		/*
		 * ��������showCourseSelectionList
		 * �������������ڱ���CourseSelection���ݱ� ���������������Ϣ
		 */
		List<CourseSelection> courseSelectionList = ObjectAndFile
				.loadCourseSelectionFile();
		Collections.sort(courseSelectionList);//���ļ��е�CourseSelection��������
		
		for (int i = 0; i < courseSelectionList.size(); i++) {
			CourseSelection c = courseSelectionList.get(i);
			IOHelper.outputToConsole("���:" + i + "  �γ̺�:" + c.getCourseNo()
					+ "  ѧ�����:" + c.getStudentNo());
		}
	}

}
