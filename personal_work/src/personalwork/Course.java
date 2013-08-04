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
	// Course course=new Course(1, "�������",3,"���޿�",3,"�ܶ�����3��4��","�ɶ�403",new
	// Teacher("��С��",1),1);
	// List<Course> courseList = new ArrayList<Course>();
	// courseList.add(course);
	// ObjectAndFile.writeCourseToFile(courseList, new File("courseList.txt"));
	// }

	public void updateScore() {
		/*
		 * ��������updateScore ����������Teacher����� ¼��γ̳ɼ�
		 */
		for (Student s : studentList) {// ������ǰ�γ̵�ѧ���б� ���ѧ����Ϣ ¼��γ̳ɼ�
			IOHelper.outputToConsole(s.getStudentNo() + " "
					+ s.getStudentName());
			IOHelper.outputToConsole("�������ѧ��" + this.courseName + "�εĳɼ���");

			String score = IOHelper.inputFromConsole();// �������score��Ϣ����Score����
			Score temp = new Score(this, score);

			s.getScoreList().add(temp);// �ڵ�ǰѧ����score�б��м��뵱ǰscore����
			ObjectAndFile.updateStudent(s);// ����ѧ�����ݱ�
		}
		IOHelper.outputToConsole("¼��ɹ���");

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
		String result = "���޿�";
		if (isCompulsory.equals("��"))
			result = "ѡ�޿�";
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
		/*��������addStudent
		 * ������������ѧ���б������ѧ��
		 * 
		 */
		for (Student temp : studentList) {//����ѧ���б� ���Ѿ����ڸ�ѧ�� �˳���ǰ����
			if (temp.getStudentNo() == s.getStudentNo()) {
				return;
			}
		}
		this.studentList.add(s);//�������� ���ظ�ѧ�� ����ѧ����Ϣ
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
