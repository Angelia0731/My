package personalwork;

import java.io.*;
import java.util.*;

/*类名称：ObjectToFile
 *类描述：此类用于将序列化对象写入文件
 *记得等等改的时候要判断是更新还是重新写入！！！
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
		 * 方法名：writeManagerInfoToFile 方法描述：用于写入新的管理员信息
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
		 * 方法名：readManagerInfoFromFile 方法描述：用于读取管理员信息
		 * 
		 * @return 返回一个有管理员信息ArrayList对象
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

		return list;// 直接返回ArrayList对象
	}


	public static void writeStudentToFile(List<Student> userList, File file) {
		/*
		 * 方法名：writeStudentToFile方法描述：将Student对象写入文件
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
		 * 方法名：loadFile方法描述：用于加载学生数据表文件
		 * 
		 * @return 返回装有Student对象的List
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
		 * 方法名：updateStudent
		 * 方法描述：用于更新指定学生信息
		 */
		List<Student> studentList = ObjectAndFile.loadStudentFile();
		for (int i = 0; i < studentList.size(); i++) {//边遍历边删除最好用迭代器  此处remove后直接break 无影响
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
		 * 方法名：writeTeacherToFile 方法描述：用于将Teacher对象写入文件
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
		 * 方法名：loadFile方法描述：用于加载教师数据表文件
		 * 
		 * @return 用于返回装有Teacher对象的List
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
		 * 方法名：updateTeacher
		 * 方法描述：用于更新指定老师信息
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
		 * 方法名：writeCourseToFile 方法描述：用于将Course对象写入文件
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
		 * 方法名：loadFile方法描述：用于加载课程数据表文件
		 * 
		 * @return 返回装有Course对象的List
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
		 * 方法名：updateCourse
		 * 方法描述：用于更新指定课程信息
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
		 * 方法名：writeCourseSelectionToFile 方法描述：用于将选课记录写入文件
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
		 * 方法名：loadFile方法描述：用于加载 选课记录数据表文件
		 * 
		 * @return 返回一个带有CourseSelection对象的List
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
