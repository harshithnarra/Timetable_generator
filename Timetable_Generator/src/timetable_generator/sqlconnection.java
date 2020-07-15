package timetable_generator;

import java.sql.*;
import java.util.*;

import javax.swing.*;


public class sqlconnection {
	Connection conn=null;
	public static Connection dbconnector(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "sys as sysdba";            
            String password = "orcl";
			Connection conn=DriverManager.getConnection(dbUrl,userName,password);
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
