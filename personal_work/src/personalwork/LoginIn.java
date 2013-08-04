package personalwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*�����ƣ�LoginIn
 *��������LoginIn���ڸ����û��ĵ�¼����
 * 
 */

@SuppressWarnings("serial")
public class LoginIn implements Serializable {
	public String command;
	public int userType = 0;
	public String current_Manager_Name;
	public String current_Manager_Login_Code;

	public LoginIn(int userType, String command) {
		this.userType = userType;
		this.command = command;
	}
	public static SystemManager managerLoginIn(String command){
        return null;
	    
	}


	public static SystemManager managerLoginIn(String id,String password) {
		/*
		 * ��������managerLoginIn �������������ڹ���Ա�ĵ�¼����
		 */
		String filename = "managerInfoList.txt";
		ArrayList<String> managerInfo = ObjectAndFile
				.readManagerInfoFromFile(filename); // ��ȡ��ǰ�Ĺ���Ա��Ϣ
		String currentManagerName = managerInfo.get(0);
		String currentManagerCode = managerInfo.get(1);
		
		
		
		/*�ж������Ƿ�Ϸ�*/
		if (id == null || id.length() == 0) {
//			IOHelper.outputToConsole("���벻��Ϊ�գ�");
			return null;

		} else {
			
			/*�ж���Ϣ�Ƿ��Ǻ�*/
			if ((id.equals(currentManagerName) && password
					.equals(currentManagerCode))) {
//				IOHelper.outputToConsole("��¼�ɹ���");
				SystemManager s=new SystemManager(currentManagerName,currentManagerCode);
//				s.setLoginOrNot(true);
				return s;
			} else {
//				IOHelper.outputToConsole("�û����������������");
                return null;
			}
		}

	}
	
	public static Student studentLoginIn(String command){
	    return null;
	}

	public static Student studentLoginIn(String id,String password) {
		/*
		 * ��������studentLoginIn
		 * ��������������ѧ���ĵ�¼����
		 * @return Student ��valid user or null��
		 */
		if(id==null||id.length()==0){
//		    IOHelper.outputToConsole("���벻��Ϊ�գ�");
            return null;
		}
		
		else{
		    int studentNo=Integer.parseInt(id);
			List<Student> studentList = ObjectAndFile.loadStudentFile();//����ѧ�����ݱ�
			for (Student s : studentList) {//����ѧ�����ݱ� ��λָ��ѧ�ŵ�ѧ��
				if (studentNo == s.getStudentNo()//��֤�����Ƿ��Ǻ�
						&& password.equals(s.getPassword())) {
//					IOHelper.outputToConsole("��¼�ɹ���");
//					s.setLoginOrNot(true);
					return s;//����ָ������
				}
			}
//			IOHelper.outputToConsole("�����ڸ��û���");//�������� ������ָ��ѧ�ŵ�ѧ��
			return null;
		}
	}

	public static Teacher teacherLoginIn(String id,String password) {
		/*
		 * ��������teacherLoginIn
		 * ��������������teacher�ĵ�¼����
		 * @return Teacher ��valid user or null��
		 */
//		String[] split = command.split(" ");
//		String str=split[1];
//		String str1 = split[2];
//		String str2 = split[3];

//		if (!str.equals("teacher")||str1.length() <= 2 || str2.length() <= 2) {//�ж������Ƿ�Ϸ�
//			IOHelper.outputToConsole("�����������������");
//			return null;//����null
//		} else {
//			int teacherNo = Integer.parseInt(str1.substring(1,
//					str1.length() - 1));
//			String password = str2.substring(1, str2.length() - 1);
	    if(id==null||id.length()==0){
	        return null;
	    }else{
			int teacherNo=Integer.parseInt(id);
			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();
			for (Teacher t : teacherList) {//����teacher���ݱ� ��λ��ָ�����ŵ�teacher
				if (teacherNo == t.getTeacherNo()//��֤�����Ƿ��Ǻ�
						&& password.equals(t.getPassword())) {
//					IOHelper.outputToConsole("��¼�ɹ���");
//					t.setLoginOrNot(true);
					return t;//����ָ������
				}
			}
//			IOHelper.outputToConsole("�����ڸ��û���");//�������� ������ָ�����ŵ���ʦ
			return null;
		}

	}

}

