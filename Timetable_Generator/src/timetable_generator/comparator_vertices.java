package timetable_generator;

import java.util.Comparator;

public class comparator_vertices implements Comparator<Integer> {

	private int[] degree;
	
	public comparator_vertices(int[] degree){
		this.degree=degree;
	}
	
	public Integer[] createIndexArray()
    {
        Integer[] indexes = new Integer[degree.length];
        for (int i = 0; i < degree.length; i++)
        {
            indexes[i] = i; 
        }
        return indexes;
    }
	
	public int compare(Integer a, Integer b) {
		// TODO Auto-generated method stub
		return degree[b]-degree[a];
	}

}
