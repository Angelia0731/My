package personalwork;

import java.io.*;
import java.util.*;

/*�����ƣ�ObjectToFile
 *���������������ڽ����л�����д���ļ�
 *�ǵõȵȸĵ�ʱ��Ҫ�ж��Ǹ��»�������д�룡����
 */
public class ObjectAndFile {

	List<Object> userList;
	File file;

	public ObjectAndFile() {
	}

	public ObjectAndFile(List<Object> userList, File file) {
		this.userList = userList;
		this.file = file;
	}
	public static void writeManagerInfoToFile(String name, String code,
			String filename) {
		/*
		 * ��������writeManagerInfoToFile ��������������д���µĹ���Ա��Ϣ
		 */
		try {

			FileWriter writer = new FileWriter(filename, false);
			writer.write(name + "\n" + code + "\n");
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static ArrayList<String> readManagerInfoFromFile(String filename) {
		/*
		 * ��������readManagerInfoFromFile �������������ڶ�ȡ����Ա��Ϣ
		 * 
		 * @return ����һ���й���Ա��ϢArrayList����
		 */

		ArrayList<String> list = new ArrayList<String>();

		try {
			File f = new File(filename);
			BufferedReader br1 = new BufferedReader(new FileReader(f));
			String line;

			while ((line = br1.readLine()) != null) {
				list.add(line);
			}
			br1.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return list;// ֱ�ӷ���ArrayList����
	}


	public static void writeStudentToFile(List<Student> userList, File file) {
		/*
		 * ��������writeStudentToFile������������Student����д���ļ�
		 */
		for (int i = 0; i < userList.size() - 1; i++) {
			for (int j = i + 1; j < userList.size(); j++) {
				if (userList.get(j).getStudentNo() == userList.get(i)
						.getStudentNo())
					userList.remove(j);
			}
		}
		ObjectOutputStream oos;
		try {
			if (!file.exists())
				file.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Student> loadStudentFile() {
		/*
		 * ��������loadFile�������������ڼ���ѧ�����ݱ��ļ�
		 * 
		 * @return ����װ��Student�����List
		 */
		List<Student> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("studentList.txt"));
			list = (List<Student>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void updateStudent(Student s) {
		/*
		 * ��������updateStudent
		 * �������������ڸ���ָ��ѧ����Ϣ
		 */
		List<Student> studentList = ObjectAndFile.loadStudentFile();
		for (int i = 0; i < studentList.size(); i++) {//�߱�����ɾ������õ�����  �˴�remove��ֱ��break ��Ӱ��
			if (s.getStudentNo() == studentList.get(i).getStudentNo()) {
				studentList.remove(i);
				studentList.add(s);
				break;
			}
		}
		ObjectAndFile.writeStudentToFile(studentList, new File(
				"studentList.txt"));

	}

	public static void writeTeacherToFile(List<Teacher> userList, File file) {
		/*
		 * ��������writeTeacherToFile �������������ڽ�Teacher����д���ļ�
		 */
		for (int i = 0; i < userList.size() - 1; i++) {
			for (int j = i + 1; j < userList.size(); j++) {
				if (userList.get(j).getTeacherNo() == userList.get(i)
						.getTeacherNo())
					userList.remove(j);
			}
		}
		ObjectOutputStream oos;
		try {
			if (!file.exists())
				file.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Teacher> loadTeacherFile() {
		/*
		 * ��������loadFile�������������ڼ��ؽ�ʦ���ݱ��ļ�
		 * 
		 * @return ���ڷ���װ��Teacher�����List
		 */
		List<Teacher> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("teacherList.txt"));
			list = (List<Teacher>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void updateTeacher(Teacher t) {
		/*
		 * ��������updateTeacher
		 * �������������ڸ���ָ����ʦ��Ϣ
		 */
		List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();
		for (int i = 0; i < teacherList.size(); i++) {
			if (t.getTeacherNo() == teacherList.get(i).getTeacherNo()) {
				teacherList.remove(i);
				teacherList.add(t);
				break;
			}
		}
		ObjectAndFile.writeTeacherToFile(teacherList, new File(
				"teacherList.txt"));

	}

	public static void writeCourseToFile(List<Course> userList, File file) {
		/*
		 * ��������writeCourseToFile �������������ڽ�Course����д���ļ�
		 */
		for (int i = 0; i < userList.size() - 1; i++) {
			for (int j = i + 1; j < userList.size(); j++) {
				if (userList.get(j).getCourseNo() == userList.get(i)
						.getCourseNo())
					userList.remove(j);
			}
		}
		ObjectOutputStream oos;
		try {
			if (!file.exists())
				file.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Course> loadCourseFile() {
		/*
		 * ��������loadFile�������������ڼ��ؿγ����ݱ��ļ�
		 * 
		 * @return ����װ��Course�����List
		 */
		List<Course> list = new ArrayList<Course>();
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("courseList.txt"));
			list = (List<Course>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public static void updateCourse(Course c) {
		/*
		 * ��������updateCourse
		 * �������������ڸ���ָ���γ���Ϣ
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();
		for (int i = 0; i < courseList.size(); i++) {
			if (c.getCourseNo() == courseList.get(i).getCourseNo()) {
				courseList.remove(i);
				courseList.add(c);
				break;
			}

		}
		ObjectAndFile.writeCourseToFile(courseList, new File("courseList.txt"));

	}

	public static void writeCourseSelectionToFile(
			List<CourseSelection> userList, File file) {
		/*
		 * ��������writeCourseSelectionToFile �������������ڽ�ѡ�μ�¼д���ļ�
		 */
		for (int i = 0; i < userList.size() - 1; i++) {
			for (int j = i + 1; j < userList.size(); j++) {
				if ((userList.get(j).getStudentNo() == userList.get(i)
						.getStudentNo())
						&& (userList.get(j).getCourseNo() == userList.get(i)
								.getCourseNo()))
					userList.remove(j);
			}
		}
		ObjectOutputStream oos;
		try {
			if (!file.exists())
				file.createNewFile();
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<CourseSelection> loadCourseSelectionFile() {
		/*
		 * ��������loadFile�������������ڼ��� ѡ�μ�¼���ݱ��ļ�
		 * 
		 * @return ����һ������CourseSelection�����List
		 */
		List<CourseSelection> list = null;
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(
					"courseSelectionList.txt"));
			list = (List<CourseSelection>) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

}
