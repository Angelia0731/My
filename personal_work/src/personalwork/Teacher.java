package personalwork;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class Teacher implements Serializable, Comparable<Teacher> {
	private int teacherNo;
	private String password;
	private String teacherName;
	private static boolean loginOrNot = false;

	public Teacher() {

	}

	public Teacher(int teacherNumber, String password, String teacherName) {
		this.teacherNo = teacherNumber;
		this.password = password;
		this.teacherName = teacherName;

	}

	// public Teacher(String teacherName, int teacherNo) {
	// this.teacherNo = teacherNo;
	// this.teacherName = teacherName;
	//
	// }

	// public static void main(String[] args) {
	// Teacher teacher1 = new Teacher(1, "000000");
	// teacher1.setTeacherName("��С��");
	// Teacher teacher2 = new Teacher(2, "000000");
	// teacher2.setTeacherName("��С��");
	// List<Teacher> teacherList = new ArrayList<Teacher>();
	// teacherList.add(teacher1);
	// teacherList.add(teacher2);
	//
	// ObjectAndFile.writeTeacherToFile(teacherList, new File(
	// "teacherList.txt"));
	//
	// }

	public void process(String cmd, String command) {
		/*
		 * ��������process ��������������teacher ����Login��ľ������
		 */
		switch (cmd) {
		case "Register":
			register(command);// register����ֱ�ӽ��� �����¼
			break;
		case "ChangePassword":
			if (loginOrNot) {// ���ж��Ƿ��Ѿ���¼
				changePassword(command);
				loginOrNot = false;
				break;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;
			}
		case "Publish":
			if (loginOrNot) {
				publish();
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
		case "Update":
			if (loginOrNot) {
				update(command);
				break;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;

			}
		case "Record":
			if (loginOrNot) {
				record(command);
				break;
			} else {
				IOHelper.outputToConsole("���ȵ�¼!");
				break;

			}

		default:
			IOHelper.outputToConsole("�������");
			break;
		}
	}

	public void register(String command) {
		/*
		 * ��������register ����������teacher��Register���� ����Register���teacherRegister����
		 */
		Register.teacherRegister(command);

	}

//	public static Teacher login(String command) {
//		/*
//		 * ��������login ��������teacher��Login���� ����LoginIn���teacherLoginIn����
//		 * 
//		 * @return ����һ��Teacher���� valid or null��
//		 */
//		return LoginIn.teacherLoginIn(command);
//
//	}

	public void changePassword(String command) {
		/*
		 * ��������changePassword ��������������Teacher �޸��������
		 */

		String[] split = command.split(" ");
		String str1 = split[1];
		String str2 = split[2];

		if (str1.length() <= 2 || str2.length() <= 2) {// �ж������Ƿ�Ϊ��
			IOHelper.outputToConsole("�����������������");
		} else {
			int teacherNo = Integer.parseInt(str1.substring(1,
					str1.length() - 1));
			String password = str2.substring(1, str2.length() - 1);

			if (!(teacherNo == this.getTeacherNo())) {// ֻ���޸ĵ�ǰ�û� ����������Ϸ�
				IOHelper.outputToConsole("�����������������");
				return;
			} else {
				this.setPassword(password);// ��֤ͨ�� �޸�����
				IOHelper.outputToConsole("�޸ĳɹ��������µ�¼��");
				return;
			}

		}
	}

	public void publish() {
		/*
		 * ��������publish ��������������teacher �����γ̲���
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();// ���ؿγ����ݱ�

		while (true) {
			boolean existOrNot = false; // �жϿγ��Ƿ��Ѿ�����

			IOHelper.outputToConsole("������γ̱��");
			String courseNo = IOHelper.inputFromConsole();

			if (courseNo == null || courseNo.length() == 0) {// ���벻�Ϸ� ��������
				IOHelper.outputToConsole("�����������������");
				continue;
			} else { // ����Ϸ�
				int current_course_no = Integer.parseInt(courseNo);// ���Ҫ�����γ̵Ŀγ̺�

				for (Course s : courseList) {// �������еĿγ��б� ��ѯ�Ƿ����
					if (current_course_no == s.getCourseNo()) {
						IOHelper.outputToConsole("�Ѵ��ڸñ�ŵĿγ�!����������");
						existOrNot = true; // ���Ѿ����� �˳�����
						break;
					}
				}
				if (existOrNot) // ���Ѿ����� ��������
					continue;
				else {

					Course course = new Course();// ����һ����ʱCourse����

					course.setCourseNo(courseNo);
					IOHelper.outputToConsole("������γ����ƣ�");
					String courseName = IOHelper.inputFromConsole();
					course.setCourseName(courseName);

					IOHelper.outputToConsole("�������ʱ��");
					String courseLength = IOHelper.inputFromConsole();
					course.setCourseLength(courseLength);

					IOHelper.outputToConsole("�������Ͽ�ʱ�䣺");
					String courseTime = IOHelper.inputFromConsole();
					course.setTime(courseTime);

					IOHelper.outputToConsole("�������Ͽεص㣺");
					String place = IOHelper.inputFromConsole();
					course.setPlace(place);

					IOHelper.outputToConsole("������ѧ������");
					String credit = IOHelper.inputFromConsole();
					course.setCredit(Integer.parseInt(credit));

					IOHelper.outputToConsole("�������Ƿ�Ϊ���޿Σ��ǻ�񣩣�");
					course.setCompulsoryOrNot(IOHelper.inputFromConsole());

					IOHelper.outputToConsole("����������ѧ��.");
					course.setAssistant(IOHelper.inputFromConsole());

					course.setTeacher(this); // ����course���ον�ʦΪ��ǰ��������ʦ

					courseList.add(course);// ��courseList�м��뵱ǰ�γ�

					ObjectAndFile.writeCourseToFile(courseList, new File(
							"courseList.txt"));// д����º��courseList

					IOHelper.outputToConsole("�ѳɹ����!");
					return;
				}
			}
		}

	}

	public void show(String command) {
		/*
		 * ��������show ��������������teacher��show course/show student���� 
		 *  show course [**]չʾ���пγ� show course [*] չʾ��ǰ�û������Ŀγ� show course [�γ̺�] չʾָ���γ̺ſγ���Ϣ
		 */
		List<Course> courseList = ObjectAndFile.loadCourseFile();// ���ؿγ����ݱ�

		String[] split = command.split(" ");
		String cmd = split[1];

		switch (cmd) {
		case "course":
			String str1 = split[2];
			String consultType = str1.substring(1, str1.length() - 1);

			if (str1 == null || str1.length() <= 2) {// �ж�����γ̺��Ƿ�Ϊ��
				IOHelper.outputToConsole("�������");
				return;
			} else {
				if (consultType.equals("**")) {// ����Ϊ[**]
					ShowList.showCourseList();// ����ShowList���showCourseList����
												// չʾ���пγ�
					return;
				}
				if (consultType.equals("*")) {// ����Ϊ[*]
					for (Course c : courseList) {// ����courseList
						if (c.getTeacher().getTeacherNo() == this.teacherNo) {// ��֤��ǰ�γ̵��ον�ʦ�Ƿ�Ϊ��ǰ�����û�
																				// ��֤ͨ��������ÿγ���Ϣ
							IOHelper.outputToConsole("�γ̺�:" + c.getCourseNo()
									+ "  �γ���:" + c.getCourseName()
									+ "  �ον�ʦ���:"
									+ c.getTeacher().getTeacherNo() + "  ����:"
									+ c.getTeacher().getTeacherName()
									+ "  ����ѧ��:" + c.getAssistant() + "  �Ͽεص�:"
									+ c.getPlace() + "  �Ͽ�ʱ��:" + c.getTime()
									+ "  ��ʱ:" + c.getCourseLength() + "  ѧ��:"
									+ c.getCredit() + " "
									+ c.getCompulsoryOrNot());
						}
					}
					return;
				} else {//�鿴ָ���γ̺ŵĿγ���Ϣ
					int consult_course_no = Integer.parseInt(consultType);
					for (Course c : courseList) {//�����γ����ݱ�  ��ѯָ���γ���Ϣ�����
						if (consult_course_no == c.getCourseNo()) {
							IOHelper.outputToConsole("�γ̺�:" + c.getCourseNo()
									+ "  �γ���:" + c.getCourseName()
									+ "  �ον�ʦ���:"
									+ c.getTeacher().getTeacherNo() + "  ����:"
									+ c.getTeacher().getTeacherName()
									+ "  ����ѧ��:" + c.getAssistant() + "  �Ͽεص�:"
									+ c.getPlace() + "  �Ͽ�ʱ��:" + c.getTime()
									+ "  ��ʱ:" + c.getCourseLength() + "  ѧ��:"
									+ c.getCredit() + " "
									+ c.getCompulsoryOrNot());
							return; //�ҵ�ָ���γ�  ���˳���ǰ����

						}
					}
					IOHelper.outputToConsole("�����ڸÿγ̣�");//��������û���ҵ���ؿγ� ��ʾ�����ڸÿγ�
					return;//�˳���ǰ����
				}
			}

		case "student":
			String str2 = split[2];
			
			if (str2 == null || str2.length() <= 2) {//�ж������Ƿ�Ϊ��
				IOHelper.outputToConsole("�������");
				return;
			} else {
				int courseNo = Integer.parseInt(str2.substring(1,
						str2.length() - 1));
				for (Course c : courseList) {//�����γ����ݱ� ��ѯָ���γ̺ŵĿγ�
					if (c.getCourseNo() == courseNo) {//��λָ���γ� ������studentList ���ѧ����Ϣ
						List<Student> studentList = c.getStudent();
						for (Student s : studentList) {
							IOHelper.outputToConsole("ѧ��:  " + s.getStudentNo()
									+ "  ����:" + s.getStudentName() + "  �꼶:"
									+ s.getstudentDegree());
						}
						return;//��λ��ָ���γ̺� ���˳���ǰ����
					}
				}
				IOHelper.outputToConsole("�����ڸÿγ̣����������룡");//��������û���ҵ�ָ����ŵĿγ� ��ʾ�����ڸÿγ�
				return;
			}
		default:
			IOHelper.outputToConsole("�������");//���벻�Ϸ�
            return;
		}
	}

	public void update(String command) {
		/*
		 * ��������update
		 * ����������������ʦ�ĸ��¿γ���Ϣ����
		 */

		List<Course> courseList = ObjectAndFile.loadCourseFile();//���ؿγ����ݱ�

		String[] split = command.split(" ");
		String str1=split[1];
		String str2 = split[2];
		
		if(!str1.equals("course")){//�ж������Ƿ��Ǻ�
			IOHelper.outputToConsole("�������");
			return;
			}

		if (str2.length() <= 2) {//�ж�����γ̺��Ƿ�Ϊ��
			IOHelper.outputToConsole("�������");
			return;
		} else {
			int courseNo = Integer.parseInt(str2.substring(1, str2.length() - 1));
			
			for (int i = 0; i < courseList.size(); i++) {//�����γ����ݱ�
				Course c = courseList.get(i);
				if (c.getCourseNo() == courseNo) {//��λ��ָ���γ�
					if (c.getTeacher().getTeacherNo() == this.teacherNo) {//��֤��ǰ�γ��ον�ʦ�Ƿ��ǵ�ǰ�����û� ��֤ͨ�������update�γ̲���
						IOHelper.outputToConsole("�γ̺�:" + c.getCourseNo()
								+ "  �γ���:" + c.getCourseName() + "  �ον�ʦ���:"
								+ c.getTeacher().getTeacherNo() + "  ����:"
								+ c.getTeacher().getTeacherName() + "  ����ѧ��:"
								+ c.getAssistant() + "  �Ͽεص�:" + c.getPlace()
								+ "  �Ͽ�ʱ��:" + c.getTime() + "  ��ʱ:"
								+ c.getCourseLength() + "  ѧ��:" + c.getCredit()
								+ " " + c.getCompulsoryOrNot());
						
						Course course = new Course();//����һ���յ���ʱCourse����

						IOHelper.outputToConsole("�������µĿγ̱�ţ�");
						String course_No = IOHelper.inputFromConsole();
						course.setCourseNo(course_No);
						IOHelper.outputToConsole("�������µĿγ����ƣ�");
						String courseName = IOHelper.inputFromConsole();
						course.setCourseName(courseName);

						IOHelper.outputToConsole("�������µĿ�ʱ��");
						String courseLength = IOHelper.inputFromConsole();
						course.setCourseLength(courseLength);

						IOHelper.outputToConsole("�������µ��Ͽ�ʱ�䣺");
						String courseTime = IOHelper.inputFromConsole();
						course.setTime(courseTime);

						IOHelper.outputToConsole("�������µ��Ͽεص㣺");
						String place = IOHelper.inputFromConsole();
						course.setPlace(place);

						IOHelper.outputToConsole("�������µ�ѧ������");
						String credit = IOHelper.inputFromConsole();
						course.setCredit(Integer.parseInt(credit));

						IOHelper.outputToConsole("�������Ƿ�Ϊ���޿Σ��ǻ�񣩣�");
						course.setCompulsoryOrNot(IOHelper.inputFromConsole());

						IOHelper.outputToConsole("����������ѧ��.");
						course.setAssistant(IOHelper.inputFromConsole());

						course.setTeacher(this);

						if (Integer.parseInt(course_No) == courseNo) {
							c = course;//����޸ĺ�Ŀγ̿γ̱��û�иı� ֱ�����µĿγ��滻ԭ���Ŀγ�
							ObjectAndFile.writeCourseToFile(courseList,
									new File("courseList.txt"));
							IOHelper.outputToConsole("���³ɹ���");
							return;

						} else {//�޸��˿γ̱��
							for (Course s : courseList) {//�����γ����ݱ� �����ظ�����֤
								int current_course_no = Integer
										.parseInt(course_No);
								if (s.getCourseNo() == current_course_no) {
									IOHelper.outputToConsole("�Ѵ��ڸñ�ŵĿγ�!����ʧ��");
									return; //�γ̺��ظ� ����ʧ�� �˳���ǰ����
								}
							}
							courseList.remove(i);//��������û���ظ��γ̺�  �����ݱ����Ƴ�ԭ�γ� ����¿γ�
							courseList.add(course);
							ObjectAndFile.writeCourseToFile(courseList,
									new File("courseList.txt"));//�������ݱ�
							IOHelper.outputToConsole("���³ɹ���");
							return;
						}

					} else {
						IOHelper.outputToConsole("�����ڸñ�ŵ��㴴���Ŀγ̣���Ȩ���޸ģ�");
						return;
					}
				}
			}
			IOHelper.outputToConsole("�����ڸÿγ̣�");//���������γ����ݱ� û���ҵ�ָ���γ̺ŵĿγ�
			return;
		}

	}

	public void record(String command) {
		/*
		 * ��������record
		 * ����������������ʦ��¼�γ̳ɼ�����
		 */

		String[] split = command.split(" ");
		String str1=split[1];
		String str2 = split[2];

		if (!(str1.equals("score"))) {//�ж������Ƿ��Ǻ�
			IOHelper.outputToConsole("�������");
			return;
		}
		if (str2 == null || str2.length() <= 2) {//�ж�����γ̺��Ƿ�Ϊ��
			IOHelper.outputToConsole("�������");
			return;
		} else {
			int courseNo = Integer
					.parseInt(str2.substring(1, str2.length() - 1));
			
			List<Course> courseList = ObjectAndFile.loadCourseFile();//���ؿγ����ݱ�
			for (Course c : courseList) {//�����γ����ݱ� ��λ��ָ����ŵĿγ�
				if (c.getCourseNo() == courseNo) {
					if (c.getTeacher().getTeacherNo() == this.getTeacherNo()) {//��֤�Ƿ���Ȩ�޽��гɼ�¼�����
						c.updateScore();//��֤ͨ�� ����Course�ĸ��¿γ̳ɼ�����
						return;
					} else {
						IOHelper.outputToConsole("��Ȩ�ޣ�");//��֤��ͨ�� ��ʾ��Ȩ�� �˳���ǰ����
						return;
					}
				}
			}
			IOHelper.outputToConsole("�����ڸÿγ̣�");//�������� ������ָ���γ̺ŵĿγ�
			return;

		}

	}

	public int getTeacherNo() {
		return teacherNo;
	}

	public String getPassword() {
		return password;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public void setLoginOrNot(boolean b) {
		loginOrNot = b;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Teacher o) {
		// TODO Auto-generated method stub
		if (o.getTeacherNo() > this.getTeacherNo()) {
			return -1;
		} else
			return 1;
	}

}
