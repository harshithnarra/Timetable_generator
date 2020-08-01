package timetable_generator;

import java.util.Vector;
import java.sql.*;
import java.util.*; 
import java.util.Random; 

import javax.swing.JOptionPane;

public class generate_tt_1 {

	Connection conn=null;
	public int no_courses;
	public int groups=20;
	public int no_year=4;
	public int no_branches=5;
	public int no_schools=3;
	
	public boolean all_coloured(int[] colour){
		int i;
		for(i=0;i<no_courses;i++){
			if(colour[i]==-1)return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public generate_tt_1(){
		conn=sqlconnection.dbconnector();
		
		//building nodes
		try{
			String query="select count(cid) count from courses";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				no_courses=rs.getInt("count");
			}
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		course[] cr=new course[no_courses];
		
		int i=0;
		
		try{
			String query="select * from courses order by cid";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				int[] sg=new int[groups];
				Vector<Vector<String>> list = new Vector<Vector<String>>(groups);
				//int s;
				int j=0;
				int k,l;
				for(k=1;k<=no_year;k++){
					for(l=1;l<=no_branches;l++){
						String q="select count(*) count from student_att where year=? and branch_id=? and course_id=?";
						PreparedStatement p=conn.prepareStatement(q);
						p.setInt(1,k );
						p.setInt(2,l );
						p.setInt(3,rs.getInt("cid") );
						ResultSet r=p.executeQuery();
						while(r.next()){
							//System.out.println(r.getInt("count"));
							sg[j]=r.getInt("count");
							//System.out.println("hi "+sg[j]);
						}
						r.close();
						p.close();
						
						String q1="select roll_no from student_att where year=? and branch_id=? and course_id=?";
						PreparedStatement p1=conn.prepareStatement(q1);
						p1.setInt(1,k );
						p1.setInt(2,l );
						p1.setInt(3,rs.getInt("cid") );
						ResultSet r1=p1.executeQuery();
						Vector<String> l1= new Vector<String>();
						while(r1.next()){
							l1.add(r1.getString("roll_no"));
						}
						if(l1.size()==0)l1.add("hello");
						list.add(l1);
						r1.close();
						p1.close();
						
						//System.out.println("hi1 "+sg[j]+" "+j);
						j++;
					}
				}
				/*for(s=0;s<groups;s++){
					System.out.println("hi "+sg[s]);
				}*/
				/*for(s=0;s<groups;s++){
					for(String t:list.get(s)){
						System.out.print(t+" ");
					}
					System.out.println("");
				}
				System.out.println("");*/
				//System.out.println(i);
				cr[i]=new course(rs.getInt("cid"),rs.getString("cname"),rs.getInt("f_id"),rs.getInt("hours_weekly"),rs.getInt("school_id"),rs.getInt("course_type_id"),rs.getInt("classroom_type_id"),sg,list);
				//cr[i].display();
				//System.out.println(i);
				i++;
			}
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		int[][] edges= new int[no_courses][no_courses];
		int m,n;
		for(m=0;m<no_courses;m++){
			for(n=0;n<no_courses;n++){
				edges[m][n]=0;
			}
		}
		/*for(m=0;m<no_courses;m++){
			System.out.println(cr[m].cid);
		}*/
		//building graph
		//System.out.println(cr[4].list.size()+" "+cr[5].list.size());
		for(m=0;m<no_courses;m++){
			for(n=m+1;n<no_courses;n++){
				//System.out.println("hi "+m+" "+n);
				if(cr[m].f_id==cr[n].f_id){
					edges[m][n]=1;
					edges[n][m]=1;
					continue;
				}
				//System.out.println("hi "+m+" "+n);
				for(i=0;i<groups;i++){
					//System.out.println(cr[m].sg[i]+" "+cr[n].sg[i]);
					if(cr[m].sg[i]>0 && cr[n].sg[i]>0){
						//System.out.println(m+" "+n+" "+i);
						Vector<String> l2= new Vector<String>();
						Vector<String> l3= new Vector<String>();
						l2=cr[m].list.get(i);
						l3=cr[n].list.get(i);
						l2.retainAll(l3);
						if(!l2.isEmpty()){
							edges[m][n]=1;
							edges[n][m]=1;
							break;
						}
					}
				}
			}
		}
		
		
		//printing graph
		System.out.println("adjacency matrix");
		for(m=0;m<no_courses;m++){
			for(n=0;n<no_courses;n++){
				System.out.print(edges[m][n]+"  ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		
		//welsh powell vertex colouring
		int[] degree=new int[no_courses];
		for(m=0;m<no_courses;m++){
			degree[m]=0;
		}
		int sum;
		for(m=0;m<no_courses;m++){
			sum=0;
			for(n=0;n<no_courses;n++){
				sum+=edges[m][n];
			}
			degree[m]=sum;
		}
		comparator_vertices comp=new comparator_vertices(degree);
		Integer[] vertex_order = comp.createIndexArray();
		Arrays.sort(vertex_order, comp);
		int[] colour=new int[no_courses];
		for(m=0;m<no_courses;m++){
			colour[m]=-1;
		}
		
		//printing degree
		System.out.println("Degree");
		for(m=0;m<no_courses;m++){
			System.out.print(degree[m]+"  ");
		}
		System.out.println("\n");
				
		//printing vertex_order
		System.out.println("vertex_order");
		for(m=0;m<no_courses;m++){
			System.out.print(vertex_order[m]+"  ");
		}
		System.out.println("\n");
				
				
		int present_colour=0;
		colour[vertex_order[0]]=present_colour;
		while(!all_coloured(colour)){
			int z;
			for(z=0;z<no_courses;z++){
				//System.out.println(z);
				if(colour[vertex_order[z]]==-1){
					int y;
					int x=0;
					for(y=0;y<no_courses;y++){
						if(edges[vertex_order[z]][y]==1){
							if(colour[y]==present_colour){
								x++;
								break;
							}
						}
					}
					if(x==0){
						colour[vertex_order[z]]=present_colour;
						//System.out.println(vertex_order[z]+" "+present_colour);
					}
				}
			}
			present_colour++;
		}
		int no_colours=0;
		for(m=0;m<no_courses;m++){
			if(colour[m]>no_colours)no_colours=colour[m];
		}
		no_colours++;
		
		//printing colour
		System.out.println("colours");
		for(m=0;m<no_courses;m++){
			System.out.print(colour[m]+"  ");
		}
		System.out.println("\n");
		
		//getting classroom data
		Vector<Vector<classroom>> room=new Vector<Vector<classroom>>(no_schools);
		for(i=1;i<=no_schools;i++){
			Vector<classroom> m1=new Vector<classroom>();
			try{
				String query="select * from classrooms where school_id=? order by capacity desc";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setInt(1,i );
				ResultSet rs=pst.executeQuery();
				while(rs.next()){
					classroom c=new classroom(rs.getInt("cr_id"),rs.getInt("school_id"),rs.getInt("capacity"),rs.getInt("type_id"));
					m1.add(c);
				}
				rs.close();
				pst.close();
				//conn.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
			room.add(m1);
		}
		
		//creating slots with each slot in descending order based on strength
		Vector<Vector<Integer>> slot=new Vector<Vector<Integer>>();
		for(m=0;m<no_colours;m++){
			Vector<Integer> k1=new Vector<Integer>();
			for(n=0;n<no_courses;n++){
				if(colour[n]==m){
					k1.add(n);
				}
			}
			slot.add(k1);
		}
		comparator_slots comparator = new comparator_slots(cr);
		for(m=0;m<no_colours;m++){
			Collections.sort(slot.get(m),comparator);
		}
		
		//printing slots
		System.out.println("slots");
		for(m=0;m<no_colours;m++){
			for(n=0;n<slot.get(m).size();n++){
				System.out.print(slot.get(m).get(n)+"-"+cr[slot.get(m).get(n)].course_strength+"  ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		//assigning classes to courses
		int no_slots=no_colours;
		for(m=0;m<no_slots;m++){
			for(n=0;n<no_schools;n++){
				for(classroom w:room.get(n)){
					w.occupied.add(0);
				}
			}
			for(Integer q:slot.get(m)){
				for(classroom w:room.get(cr[q].school_id-1)){
					if(cr[q].classroom_type_id==w.type_id && w.occupied.get(m)==0){
						if(cr[q].course_strength<=w.capacity ){
							w.occupied.setElementAt(1,m);
							cr[q].assigned_classroom=w;
							break;
						}
					}
				}
			}
			if(cr[slot.get(m).get(0)].assigned_classroom.capacity==0){
				JOptionPane.showMessageDialog(null, "Timetable generation failed as there are not big enough classrooms or classroom of required type not present");
				JOptionPane.showMessageDialog(null, "Course id: "+cr[slot.get(m).get(0)].cid+" name: "+cr[slot.get(m).get(0)].cname+" has strength "+cr[slot.get(m).get(0)].course_strength+" but there is no classroom with that capacity");
				System.exit(0);
			}
			Vector<Integer> k1=new Vector<Integer>();
			for(Integer q:slot.get(m)){
				if(cr[q].assigned_classroom.capacity==0){
					k1.add(q);
				}
			}
			if(k1.size()>0){
				for(Integer q:k1){
					slot.get(m).removeElement(q);
				}
				slot.add(k1);
				no_slots++;
			}
		}
		
		//printing slots after assigning classrooms
		System.out.println("slots after assigning classrooms");
		for(m=0;m<no_slots;m++){
			for(n=0;n<slot.get(m).size();n++){
				System.out.print(slot.get(m).get(n)+"  ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		//printing assigned classrooms after assigning classrooms
		System.out.println("Classrooms assigned");
		for(m=0;m<no_slots;m++){
			for(n=0;n<slot.get(m).size();n++){
				System.out.print(cr[slot.get(m).get(n)].assigned_classroom.cr_id+"-"+cr[slot.get(m).get(n)].assigned_classroom.school_id+"-"+cr[slot.get(m).get(n)].assigned_classroom.capacity+"-"+cr[slot.get(m).get(n)].assigned_classroom.type_id+"  ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		//building slot_time and slot_lab vectors
		Vector<Integer> slot_time=new Vector<Integer>();
		Vector<Integer> slot_lab=new Vector<Integer>();
		for(m=0;m<no_slots;m++){
			int f=0;
			for(Integer q:slot.get(m)){
				if(cr[q].hours_weekly>f)f=cr[q].hours_weekly;
			}
			slot_time.add(f);
			int w=0;
			for(Integer q:slot.get(m)){
				if(cr[q].course_type_id==2){
					w++;
					//break;
				}
			}
			//if(w==0)slot_lab.add(0);
			//else slot_lab.add(1);
			slot_lab.add(w);
		}
		int swlc=0;
		for(int zz=0;zz<slot_lab.size();zz++)if(slot_lab.get(zz)>0)swlc++;
		if(swlc>10)JOptionPane.showMessageDialog(null,"Not enough slots to slot all labs");
		
		//printing slot_time
		System.out.println("slot_time");
		for(m=0;m<no_slots;m++){
			System.out.print(slot_time.get(m)+"  ");
		}
		System.out.println("\n");
		
		//printing slot_lab
		System.out.println("slot_lab");
		for(m=0;m<no_slots;m++){
			System.out.print(slot_lab.get(m)+"  ");
		}
		System.out.println("\n");
		
		//assigning time slots to courses
		int no_time_slots = 0;
		try{
			String query="select count(*) count from time_slots";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				no_time_slots=rs.getInt("count");
			}
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		int days=5;
		int total_hours=0;
		for(m=0;m<no_slots;m++){
			total_hours+=slot_time.get(m);
		}
		if(total_hours>days*no_time_slots){
			JOptionPane.showMessageDialog(null, "Timetable generation failed as there are not enough time slots available for allocation to all courses");
			JOptionPane.showMessageDialog(null, "Available time slots: "+days*no_time_slots+" Minimum required time slots: "+total_hours);
			System.exit(0);
		}
		Vector<Integer> slot_time_inc=new Vector<Integer>();
		slot_time_inc=(Vector<Integer>) slot_time.clone();
		if(total_hours<days*no_time_slots){
			for(m=0;m<no_slots;m++){
				if(slot_lab.get(m)>0&& total_hours<days*no_time_slots){
					slot_time_inc.setElementAt(slot_time_inc.get(m)+1,m);
					total_hours++;
				}
			}
		}
		//printing slot_time_inc
		System.out.println("slot_time_inc");
		for(m=0;m<no_slots;m++){
			System.out.print(slot_time_inc.get(m)+"  ");
		}
		System.out.println("\n");
		int total_slots=days*no_time_slots;
		Vector<Integer> time_slots=new Vector<Integer>(total_slots);
		for(m=0;m<total_slots;m++){
			time_slots.add(m);
		}
		//consider there are 4 lab slots per day 8,9,130,230 and ignoring 330
		int total_lab_slots=20;
		Vector<Integer> lab_time_slots=new Vector<Integer>(total_lab_slots);
		for(m=0;m<total_lab_slots;m++){
			lab_time_slots.add(m);
		}
		Random rand = new Random(); 
		for(m=0;m<no_slots;m++){
			if(slot_lab.get(m)>0){
				int r=rand.nextInt(total_lab_slots);
				int s=lab_time_slots.get(r);
				lab_time_slots.remove(r);
				if(s%2==0)lab_time_slots.remove(r);
				if(s%2==1)lab_time_slots.remove(r-1);
				System.out.println("hi "+r+"-"+s);
				for(int zz=0;zz<lab_time_slots.size();zz++)System.out.print(lab_time_slots.get(zz)+" ");
				System.out.println();
				int[] z=new int[3];;
				z[0]=(s/4)*9;
				if(s%4==1)z[0]+=1;
				else if(s%4==2)z[0]+=4;
				else if(s%4==3)z[0]+=5;
				z[1]=z[0]+1;
				z[2]=z[0]+2;
				for(i=0;i<3;i++){
					time_slots.removeElement(z[i]);
				}
				total_lab_slots-=2;
				total_slots-=3;
				for(Integer q:slot.get(m)){
					if(cr[q].course_type_id==2){
						for(i=0;i<3;i++){
							cr[q].assigned_time_slots.add(z[i]);
						}
					}
					else{
						if(slot_time.get(m)==slot_time_inc.get(m)){
							if(cr[q].hours_weekly==slot_time.get(m)){
								for(i=0;i<3;i++){
									cr[q].assigned_time_slots.add(z[i]);
								}
							}
							else{
								for(i=1;i<3;i++){
									cr[q].assigned_time_slots.add(z[i]);
								}
							}
						}
						else{
							for(i=1;i<3;i++){
								cr[q].assigned_time_slots.add(z[i]);
							}
						}
					}
				}
				slot_time_inc.setElementAt(slot_time_inc.get(m)-3, m);
			}
		}
		for(m=0;m<no_slots;m++){
			for(n=0;n<slot_time_inc.get(m);n++){
				int h=0;
				for(Integer q:slot.get(m)){
					if(cr[q].hours_weekly==slot_time.get(m)){
						h=slot.get(m).indexOf(q);
						break;
					}
				}
				int r=rand.nextInt(total_slots);
				int s=time_slots.get(r);
				//better chance of making sure there are no multiple one hour periods of a course on the same day
				int j;
				for(i=0;i<10;i++){
					//System.out.println("hi "+i+" "+m+" "+n+" "+s);
					Vector<Integer> c=new Vector<Integer>();
					for(j=(s/9)*9;j<(s/9)*9+9;j++){
						c.add(j);
					}
					c.retainAll(cr[slot.get(m).get(h)].assigned_time_slots);
					if(c.isEmpty()){
						break;
					}
					r=rand.nextInt(total_slots);
					s=time_slots.get(r);
				}
				time_slots.remove(r);
				total_slots--;
				for(Integer q:slot.get(m)){
					if(cr[q].hours_weekly>cr[q].assigned_time_slots.size()){
						cr[q].assigned_time_slots.add(s);
					}
				}
			}
		}
		
		//printing assigned time slots
		System.out.println("time slots assigned");
		for(m=0;m<no_slots;m++){
			for(n=0;n<slot.get(m).size();n++){
				for(Integer q:cr[slot.get(m).get(n)].assigned_time_slots){
					System.out.print(q+"-");
				}
				System.out.println(" ");
			}
			System.out.println("");
		}
		System.out.println("");
		
		//creating timetable objects
		for(m=0;m<no_courses;m++){
			for(n=0;n<cr[m].hours_weekly;n++){
				int s=cr[m].assigned_time_slots.get(n);
				int d=(s/9)+1;
				int sl=(s%9)+1;
				timetable tt=new timetable(cr[m].cid,cr[m].assigned_classroom.cr_id,cr[m].assigned_classroom.school_id,d,sl);
				try{
					String query="insert into timetable values(?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setInt(1, tt.cid);
					pst.setInt(2, tt.cr_id);
					pst.setInt(3, tt.school_id);
					pst.setInt(4, tt.day);
					pst.setInt(5, tt.slot);
					pst.executeQuery();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Timetable generated Successfully");
	}
}
