package timetable_generator;

import java.util.Comparator;

public class comparator_slots implements Comparator<Integer> {
	
	private course[] cr;
	
	public comparator_slots(course[] cr){
		this.cr=cr;
	}

	public int compare(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return cr[b].course_strength-cr[a].course_strength;
	}

}
