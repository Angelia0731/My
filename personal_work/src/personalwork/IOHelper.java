package personalwork;

import java.io.*;
/*�����ƣ�IOhelper
 *�����������ڴӿ���̨��ȡ����
 */
public class IOHelper {

	public static  String inputFromConsole(){
		String input = null;
		BufferedReader br1;
		
		
		try {
			br1 = new BufferedReader(new InputStreamReader(System.in));
			input=br1.readLine();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return input;
	}
	public static void outputToConsole(String s){
		System.out.println(s);
	}

}

