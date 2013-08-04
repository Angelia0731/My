package personalwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*类名称：LoginIn
 *类描述：LoginIn用于各个用户的登录操作
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
		 * 方法名：managerLoginIn 方法描述：用于管理员的登录操作
		 */
		String filename = "managerInfoList.txt";
		ArrayList<String> managerInfo = ObjectAndFile
				.readManagerInfoFromFile(filename); // 读取当前的管理员信息
		String currentManagerName = managerInfo.get(0);
		String currentManagerCode = managerInfo.get(1);
		
		
		
		/*判断输入是否合法*/
		if (id == null || id.length() == 0) {
//			IOHelper.outputToConsole("输入不能为空！");
			return null;

		} else {
			
			/*判断信息是否吻合*/
			if ((id.equals(currentManagerName) && password
					.equals(currentManagerCode))) {
//				IOHelper.outputToConsole("登录成功！");
				SystemManager s=new SystemManager(currentManagerName,currentManagerCode);
//				s.setLoginOrNot(true);
				return s;
			} else {
//				IOHelper.outputToConsole("用户名或密码输入错误！");
                return null;
			}
		}

	}
	
	public static Student studentLoginIn(String command){
	    return null;
	}

	public static Student studentLoginIn(String id,String password) {
		/*
		 * 方法名：studentLoginIn
		 * 方法描述：用于学生的登录操作
		 * @return Student （valid user or null）
		 */
		if(id==null||id.length()==0){
//		    IOHelper.outputToConsole("输入不能为空！");
            return null;
		}
		
		else{
		    int studentNo=Integer.parseInt(id);
			List<Student> studentList = ObjectAndFile.loadStudentFile();//加载学生数据表
			for (Student s : studentList) {//遍历学生数据表 定位指定学号的学生
				if (studentNo == s.getStudentNo()//验证密码是否吻合
						&& password.equals(s.getPassword())) {
//					IOHelper.outputToConsole("登录成功！");
//					s.setLoginOrNot(true);
					return s;//返回指定对象
				}
			}
//			IOHelper.outputToConsole("不存在该用户！");//遍历结束 不存在指定学号的学生
			return null;
		}
	}

	public static Teacher teacherLoginIn(String id,String password) {
		/*
		 * 方法名：teacherLoginIn
		 * 方法描述：用于teacher的登录操作
		 * @return Teacher （valid user or null）
		 */
//		String[] split = command.split(" ");
//		String str=split[1];
//		String str1 = split[2];
//		String str2 = split[3];

//		if (!str.equals("teacher")||str1.length() <= 2 || str2.length() <= 2) {//判断输入是否合法
//			IOHelper.outputToConsole("输入错误！请重新输入");
//			return null;//返回null
//		} else {
//			int teacherNo = Integer.parseInt(str1.substring(1,
//					str1.length() - 1));
//			String password = str2.substring(1, str2.length() - 1);
	    if(id==null||id.length()==0){
	        return null;
	    }else{
			int teacherNo=Integer.parseInt(id);
			List<Teacher> teacherList = ObjectAndFile.loadTeacherFile();
			for (Teacher t : teacherList) {//遍历teacher数据表 定位到指定工号的teacher
				if (teacherNo == t.getTeacherNo()//验证密码是否吻合
						&& password.equals(t.getPassword())) {
//					IOHelper.outputToConsole("登录成功！");
//					t.setLoginOrNot(true);
					return t;//返回指定对象
				}
			}
//			IOHelper.outputToConsole("不存在该用户！");//遍历结束 不存在指定工号的老师
			return null;
		}

	}

}

