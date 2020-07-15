package timetable_generator;

import java.util.Vector;

public class classroom {
	
	public int cr_id;
	public int school_id;
	public int capacity;
	public int type_id;
	public Vector<Integer> occupied=new Vector<Integer>();
	
	public classroom(int a,int b,int c,int d){
		this.cr_id=a;
		this.school_id=b;
		this.capacity=c;
		this.type_id=d;
	}
}
