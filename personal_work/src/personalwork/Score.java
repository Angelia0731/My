package personalwork;

import java.io.*;
/*
 * �����ƣ�Score
 * �����������ڴ���ѧ���ĳɼ�
 */
@SuppressWarnings("serial")
public class Score implements Serializable,Comparable<Score> {
	private Course course;
	private String score;
	
	public Score(Course c,String score){
		this.course=c;
		this.score=score;
	}
	public void setCourse(Course c){
		this.course=c;
	}
	public void setScore(String score){
		this.score=score;
	}
	public Course getCourse(){
		return course;
	}
	public String getScore(){
		return score;
	}
	@Override
	public int compareTo(Score o) {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
