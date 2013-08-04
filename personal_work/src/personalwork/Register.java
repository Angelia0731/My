package personalwork;

import java.io.File;
import java.util.List;

public class Register {
	public String command;
	int userType = 0;

	public Register(int userType, String command) {
		this.userType = userType;
		this.command = command;
	}

	public static void studentRegister(String command) {
		/*
		 * ��������studentRegister
		 * ��������������ѧ����ע�����
		 */
		String[] split = command.split(" ");
		String str1 = split[2];
		String str2 = split[3];
		String str3 = split[4];
		String str4 = split[5];

		if (str1.length() <= 2 || str2.length() <= 2 || str3.length() <= 2
				|| str4.length() <= 2||!(split[1].equals("student"))) {
			IOHelper.outputToConsole("�������");
		} else {
			int studentNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);
			String studentName = str3.substring(1, str3.length()-1);
			String studentDegree = str4.substring(1, str4.length() - 1);
			List<Student> studentList = ObjectAndFile.loadStudentFile();

			for (Student s : studentList) {//����student���ݱ� ��֤�Ƿ��Ѿ�����ָ��ѧ���˻�
				if (studentNo == s.getStudentNo()) {//ָ��ѧ���ѱ�ע�� �˳���ǰ����
					IOHelper.outputToConsole("�Ѵ��ڸ��˻�");
					return;
				}
			}

			studentList.add(new Student(studentNo, password, studentName,
					studentDegree));//ͨ������  ���ظ�ѧ�� �����ݱ�������µ�ѧ��
			ObjectAndFile.writeStudentToFile(studentList, new File(
					"studentList.txt"));//�������ݱ�
			IOHelper.outputToConsole("ע��ɹ������¼��");
		}
	}

	public static void teacherRegister(String command) {
		/*
		 * ��������teacherRegister
		 * ����������������ʦ��ע�����
		 */
		String[] split = command.split(" ");
		String str1 = split[2];
		String str2 = split[3];
		String str3 = split[4];

		if (str1.length() <= 2 || str2.length() <= 2||str3.length()<=2||!(split[1].equals("teacher"))) {
			IOHelper.outputToConsole("�������");
		} else {
			int teacherNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);
			String teacherName = str3.substring(1, str3.length() - 1);
			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();//���ؽ�ʦ���ݱ�
			
			for (Teacher s : teacherList) {//������ʦ���ݱ� ��֤�Ƿ��Ѿ�����ָ�������˻�
				if (teacherNo == s.getTeacherNo()) {//ָ�������Ѿ����� �˳���ǰ����
					IOHelper.outputToConsole("�Ѵ��ڸ��˻�");
					return;
				}
			}
			teacherList.add(new Teacher(teacherNo, password, teacherName));//ͨ������ ���ظ����� �����ݱ��м����µ�teacher
			ObjectAndFile.writeTeacherToFile(teacherList, new File(
					"teacherList.txt"));//����teacher���ݱ�
			IOHelper.outputToConsole("ע��ɹ������¼��");

		}

	}

}
