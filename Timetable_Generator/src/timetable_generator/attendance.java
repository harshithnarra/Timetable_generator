package timetable_generator;

public class attendance {
	public String sroll;
	public Integer course_id;
	public Integer total_no_classes; 
	public Integer atd; 
	
	public attendance(String a,Integer b,Integer c,Integer d){
		this.sroll=a;
		this.course_id=b;
		this.total_no_classes=c;
		this.atd=d;
	}
}
