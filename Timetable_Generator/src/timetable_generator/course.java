package timetable_generator;

import java.util.Vector;
import java.sql.*;

public class course {
	public Integer cid;
	public String cname;
	public Integer f_id;
	public Integer hours_weekly;
	public Integer school_id;
	public Integer course_type_id;
	public Integer classroom_type_id;
	public int groups=20;
	public int[] sg=new int[groups];
	public Vector<Vector<String>> list = new Vector<Vector<String>>(groups);
	public classroom assigned_classroom=new classroom(0,0,0,0);
	public int course_strength=0;
	public Vector<Integer> assigned_time_slots=new Vector<Integer>();
	
	public course(Integer a,String b,Integer c,Integer d,Integer e,Integer f,Integer g,int[] h,Vector<Vector<String>> z){
		this.cid=a;
		this.cname=b;
		this.f_id=c;
		this.hours_weekly=d;
		this.school_id=e;
		this.course_type_id=f;
		this.classroom_type_id=g;
		
		int i;
		for(i=0;i<groups;i++){
			sg[i]=h[i];
		}
		//sg=h;
		for(i=0;i<groups;i++){
			course_strength+=sg[i];
		}
		list=z;
	}
	
	public void display(){
		int i;
		for(i=0;i<groups;i++){
			System.out.println(sg[i]+" ");
		}
		System.out.println("");
		
		int s;
		for(s=0;s<groups;s++){
			for(String t:list.get(s)){
				System.out.print(t+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
