package timetable_generator;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.sql.*;
import java.util.*;

public class MyTableModel extends AbstractTableModel {

	String[] columnNames = {"","08:00-09:00","09:00-10:00","10:00-11:00","11:00-12:00","12:00-01:30","01:30-02:30","02:30-03:30","03:30-04:30","04:30-05:30","05:30-06:30"};
	
	Connection conn=null;
	

	Object[][] data = {{"Monday Course","","","","","Lunch","","","","",""},
			{"classroom",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)," ",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)},
			{"school","","","","","","","","","",""},
			{"Tuesday Course","","","","","Lunch","","","","",""},
			{"classroom",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)," ",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)},
			{"school","","","","","","","","","",""},
			{"Wednesday Course","","","","","Lunch","","","","",""},
			{"classroom",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)," ",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)},
			{"school","","","","","","","","","",""},
			{"Thursday Course","","","","","Lunch","","","","",""},
			{"classroom",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)," ",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)},
			{"school","","","","","","","","","",""},
			{"Friday Course","","","","","Lunch","","","","",""},
			{"classroom",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)," ",new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1),new Integer(-1)},
			{"school","","","","","","","","","",""}};

	private String uname;

	public MyTableModel(String username){
		uname=username;
		conn=sqlconnection.dbconnector();
		try{
			String query="select * from timetable_course_school where cid in(select course_id from attendance where sroll=?) order by day,slot";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, uname);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				if(rs.getInt("slot")<=4){
					data[(rs.getInt("day")-1)*3][rs.getInt("slot")]=rs.getString("cname");
					data[(rs.getInt("day")-1)*3+1][rs.getInt("slot")]=rs.getInt("cr_id");
					data[(rs.getInt("day")-1)*3+2][rs.getInt("slot")]=rs.getString("sname");
				}
				else{
					data[(rs.getInt("day")-1)*3][rs.getInt("slot")+1]=rs.getString("cname");
					data[(rs.getInt("day")-1)*3+1][rs.getInt("slot")+1]=rs.getInt("cr_id");
					data[(rs.getInt("day")-1)*3+2][rs.getInt("slot")+1]=rs.getString("sname");
				}
				
			}
			rs.close();
			pst.close();
			//conn.close();
			int i,j;
			for(i=0;i<15;i++){
				for(j=0;j<11;j++){
					if(data[i][j].getClass() == Integer.class && ((Integer) data[i][j]).intValue()==-1){
						data[i][j]="";
					}
				}
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}

public int getColumnCount() {
return columnNames.length;
}

public int getRowCount() {
return data.length;
}

public String getColumnName(int col) {
return columnNames[col];
}


public Object getValueAt(int row, int col) {
return data[row][col];
}

public Class getColumnClass(int c) {
return getValueAt(0, c).getClass();
}



public boolean isCellEditable(int row, int col) {
return false;
}

public void setValueAt(Object value, int row, int col) {

data[row][col] = value;
fireTableCellUpdated(row, col);
}
}
