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
	// student1.setStudentDegree("��һ");
	// student1.setStudentName("����");
	// Student student2 = new Student(121250000, "000000");
	// student2.setStudentDegree("��һ");
	// student2.setStudentName("����");
	// List<Student> studentList = new ArrayList<Student>();
	// studentList.add(student1);
	// studentList.add(student2);
	// ObjectAndFile.writeStudentToFile(studentList, new File(
	// "studentList.txt"));
	//
	// }

	public void process(String cmd, String command) {
		/*
		 * ��������process��������������student�ľ������
		 */
		switch (cmd) {
		case "Register":
			register(command);
			return;
		case "Login":
			login(command);
			return;
		case "ChangePassword":
			if (loginOrNot) {// �ж��Ƿ��ѵ�¼
				changePassword(command);
				loginOrNot = false;
				return;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				return;
			}
		case "Show":
			if (loginOrNot) {
				show(command);
				return;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				return;
			}
		case "Select":
			if (loginOrNot) {
				select(command);
				return;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				return;
			}
		case "Quit":
			if (loginOrNot) {
				quit(command);
				return;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				return;
			}
		default:
			IOHelper.outputToConsole("�������");
		}
	}

	public void register(String command) {
		/*
		 * ��������register ��������������studentע����� ����Register��studentRegister���� Register
		 * student [ѧ��] [����] [�û���] [�꼶]
		 */
		Register.studentRegister(command);

	}

	public Student login(String command) {
		/*
		 * ��������login ��������������student��¼���� ����LoginIn���studentLoginIn���� Login
		 * student [ѧ��] [����]
		 * 
		 * @return student����valid user or null��
		 */
		return LoginIn.studentLoginIn(command);

	}

	public void changePassword(String command) {
		/*
		 * ��������changePassword ��������������student�޸�������� ChangePassword [ѧ��] [����]
		 */

		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str1.length() <= 2 || str2.length() <= 2) {// �ж������Ƿ�Ϊ��
			IOHelper.outputToConsole("�����������������");
		} else {
			int studentNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);

			if (!(studentNo == this.getStudentNo())) {// ��֤�����ѧ���Ƿ���ȷ �Ƿ���Ȩ���޸�
				IOHelper.outputToConsole("�����������������");
				return;
			} else {// ��֤ͨ��
				this.setPassword(password);
				IOHelper.outputToConsole("�޸ĳɹ��������µ�¼��");
				return;
			}

		}
	}

	public void show(String command) {
		/*
		 * ��������show ��������������student show mycourseList �� show score [�γ̺�] ����
		 */
		String[] split = command.split(" ");
		if (split.length == 2) {// show mycourseList
			if (!split[1].equals("mycourseList")) {// ��֤�����Ƿ��Ǻ�
				IOHelper.outputToConsole("�������");
				return;
			}
			for (Course c : myCourse) {// ������ǰstudent��course���ݱ� �������
				IOHelper.outputToConsole("�γ̺�:" + c.getCourseNo() + "  �γ���:"
						+ c.getCourseName() + "  �ον�ʦ���:"
						+ c.getTeacher().getTeacherNo() + "  ����:"
						+ c.getTeacher().getTeacherName() + "  ����ѧ��:"
						+ c.getAssistant() + "  �Ͽεص�:" + c.getPlace()
						+ "  �Ͽ�ʱ��:" + c.getTime() + "  ��ʱ:"
						+ c.getCourseLength() + "  ѧ��:" + c.getCredit() + " "
						+ c.getCompulsoryOrNot());
			}
			return;

		} else {// show score [�γ̺�]
			if (!split[1].equals("score")) {// �ж������Ƿ��Ǻ�
				IOHelper.outputToConsole("�������");
				return;
			}

			String str = split[2];
			if (str == null || str.length() <= 2) {// �ж������Ƿ�Ϸ�
				IOHelper.outputToConsole("�������");
				return;
			} else {
				int courseNo = Integer.parseInt(str.substring(1,
						str.length() - 1));
				for (Score s : scoreList) {// ������ǰstudent��score���ݱ� ��λָ���γ�
											// ������ÿγ̳ɼ�
					if (s.getCourse().getCourseNo() == courseNo) {
						IOHelper.outputToConsole(s.getCourse().getCourseName()
								+ " " + s.getScore());
						return;
					}
				}
				IOHelper.outputToConsole("�����ڸÿγ̣�");// �������� ������ָ���γ̱�ŵĳɼ�
				return;
			}
		}
	}

	public void select(String command) {
		/*
		 * ��������select ��������������studentѡ�β��� Select course
		 */
		String[] split = command.split(" ");
		if (!split[1].equals("course") || !(split.length == 2)) {// �ж������Ƿ��Ǻ�
																	// �Ƿ�Ϊ��
			IOHelper.outputToConsole("�������");
			return;
		}

		List<Course> courseList = ObjectAndFile.loadCourseFile();// ���ؿγ�ѡ�μ�¼���ݱ�
		List<CourseSelection> courseSelectionList = ObjectAndFile
				.loadCourseSelectionFile();

		IOHelper.outputToConsole("���пγ����£�");// ����������пγ�
		ShowList.showCourseList();

		IOHelper.outputToConsole("������Ҫѡ��Ŀγ̱�� [�γ̺�]");
		String str = IOHelper.inputFromConsole();

		if (str.length() <= 2) {// �ж�����γ̺��Ƿ�Ϊ��
			IOHelper.outputToConsole("�������");
		} else {
			int courseNo = Integer.parseInt(str.substring(1, str.length() - 1));
			for (Course c : myCourse) {// ������ǰ��ѡ�γ����ݱ� ��֤�Ƿ��Ѿ�ѡ��ָ���γ�
				if (c.getCourseNo() == courseNo) {
					IOHelper.outputToConsole("��ѡ�ÿγ̣�");
					return;// ���Ѿ�ѡ�� ��ʾ��ѡ���˳���ǰ����
				}
			}
			for (Course c : courseList) {// �������пγ����ݱ� ��λ��ָ���γ�
				if (c.getCourseNo() == courseNo) {
					myCourse.add(c); // ����ѡ�γ��м���ָ���γ�
					courseSelectionList.add(new CourseSelection(// ����ѡ�μ�¼
							c.getCourseNo(), this.getStudentNo()));

					c.addStudent(this);// ��ָ���γ̵�ѧ���б�����ӵ�ǰѧ��

					ObjectAndFile.updateCourse(c);// ���¿γ� ѡ�μ�¼���ݱ�
					ObjectAndFile.writeCourseSelectionToFile(
							courseSelectionList, new File(
									"courseSelectionList.txt"));
					IOHelper.outputToConsole("ѡ�γɹ���");
					return;
				}

			}
			IOHelper.outputToConsole("�����ڸÿγ̣����������룡");// �������� ������ָ���γ̺ŵĿγ�
			return;
		}
	}

	public void quit(String command) {
		/*
		 * ��������quit ��������������student��ѡ�γ� Quit course [�γ̺�]
		 */
		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str2.length() <= 2||!(str1.equals("course"))) {// �ж������Ƿ��Ǻ� �Ƿ�Ϊ��
			IOHelper.outputToConsole("�������");
		} else {
			int courseNo = Integer.parseInt(str2.substring(1, str2.length() - 1));

			for (int i = 0; i < myCourse.size(); i++) {// ������ѡ�γ����ݱ� ��λ��ָ����ſγ�
				if (myCourse.get(i).getCourseNo() == courseNo) {
					myCourse.remove(i);// �Ƴ�ָ���γ�
					CourseSelection.deleteSelection(this.getStudentNo(), courseNo);// ����CourseSelection���delete�����Ƴ�����ѡ�μ�¼
					IOHelper.outputToConsole("��ѡ�ɹ���");
					return;
				}
			}
			IOHelper.outputToConsole("�����ڸÿγ� ���������룡");//�������� ��ָ���γ̱�ſγ�
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
