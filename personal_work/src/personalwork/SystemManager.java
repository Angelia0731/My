package personalwork;

import java.io.*;
import java.util.*;

/*�����ƣ�SystemManager
 *��������SystemManager�����ڹ���Ա
 * 
 */

public class SystemManager {
	@SuppressWarnings("unused")
    private String currentManagerName; // ��ʼ�Ĺ���Ա�˺ź������Ϊadmin
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
		 * ��������process ����������������ת������Ĺ���Ա����
		 */
		switch (cmd) {
		case "Login":
			login(command);
			break;
		case "ChangePassword":
			if (loginOrNot) { // �ж��Ƿ��Ѿ���¼
				changePassword(command);
				break;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;
			}
		case "Show":
			if (loginOrNot) {
				show(command);
				break;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;
			}
		case "Delete":
			if (loginOrNot) {
				delete(command);
				return;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;
			}
		default: {
			System.out.println("���벻�Ϸ���");
			break;
		}
		}
	}

	public SystemManager login(String command) {
		/* ��������login �������������ڹ���Ա�ĵ�¼���� */
		 return LoginIn.managerLoginIn(command);
	}

	public void changePassword(String command) {
		/*
		 * ��������changePassword �������������ڹ���Ա�������޸Ĳ���
		 */
		String[] split = command.split(" ");
		int length = split[2].length();
		String newManagerCode = split[2].substring(1, length - 1);

		if (split[1].equals("admin")) { // ����Ա�û���ʼ��Ϊ��ʼ�û���
			if (newManagerCode.length() < 5) { // �������볤�ȱ�����ڵ���4
				System.out.println("���볤�ȹ��̣�");
				return;
			} else {
				currentManagerCode = newManagerCode;
				loginOrNot=false;
				ObjectAndFile.writeManagerInfoToFile("admin", currentManagerCode,
						filename);// д���µĹ���Ա��Ϣ
				System.out.println("�����޸ĳɹ���");
				return;
			}
		} else {
			System.out.println("�������");
			return;
		}

	}

	public void show(String command) {
		/*
		 * ��������show �������������ڹ���Ա�Ĳ鿴�б���
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
			IOHelper.outputToConsole("�������");

		}

	}

	public void delete(String command) {
		/*
		 * ��������delete�������������ڹ���Ա��ɾ����Ϣ����
		 */
		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];
		String listToDelete = str1.substring(1, str1.length() - 1);
		int recordToDelete = Integer.parseInt(str2.substring(1,
				str2.length() - 1)); // ��ȡҪɾ���ļ�¼���
		switch (listToDelete) {
		case "studentList": {
			List<Student> studentList = ObjectAndFile.loadStudentFile(); // ����ѧ����¼���ݱ�

			for (int i = 0; i < studentList.size(); i++) {
				if (studentList.get(i).getStudentNo() == recordToDelete) {
					studentList.remove(i);
					ObjectAndFile.writeStudentToFile(studentList, new File(
							"studentList.txt"));
					IOHelper.outputToConsole("ɾ���ɹ���");
					return;
				}
			}
			IOHelper.outputToConsole("û���ҵ���ؼ�¼��");

			break;
		}
		case "teacherList": {

			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();

			for (int i = 0; i < teacherList.size(); i++) {
				if (teacherList.get(i).getTeacherNo() == recordToDelete) {
					teacherList.remove(i);
					ObjectAndFile.writeTeacherToFile(teacherList, new File(
							"teacherList.txt"));
					IOHelper.outputToConsole("ɾ���ɹ���");
					return;
				}
			}
			IOHelper.outputToConsole("û���ҵ���ؼ�¼��");

			break;

		}
		case "courseList": {
			List<Course> courseList = ObjectAndFile.loadCourseFile();
			for (int i = 0; i < courseList.size(); i++) {
				if (courseList.get(i).getCourseNo() == recordToDelete) {
					courseList.remove(i);
					ObjectAndFile.writeCourseToFile(courseList, new File(
							"courseList.txt"));
					IOHelper.outputToConsole("ɾ���ɹ���");
					return;
				}
			}
			IOHelper.outputToConsole("û���ҵ���ؼ�¼��");
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
					IOHelper.outputToConsole("ɾ���ɹ���");
					return;
				}
			}
			IOHelper.outputToConsole("û���ҵ���ؼ�¼��");
			break;
		}

		}

	}

	public void setLoginOrNot(boolean b) {
		loginOrNot = b;
	}
}
