package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;


public class student_1 extends JFrame {

	private JPanel contentPane;

	public static student_1 frame = new student_1();
	private String username;
	private JTextField textFieldrollno;
	private JTextField textFieldname;
	private JTextField textFieldyear;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public student_1(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1353, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		this.frame=this;
		
		JLabel lblRollNo = new JLabel("ROLL NO :");
		lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRollNo.setBounds(12, 17, 77, 16);
		getContentPane().add(lblRollNo);
		
		textFieldrollno = new JTextField();
		textFieldrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldrollno.setBounds(89, 14, 116, 22);
		getContentPane().add(textFieldrollno);
		textFieldrollno.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(253, 17, 56, 16);
		getContentPane().add(lblName);
		
		textFieldname = new JTextField();
		textFieldname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldname.setBounds(306, 14, 170, 22);
		getContentPane().add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblyear = new JLabel("Year :");
		lblyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblyear.setBounds(536, 17, 56, 16);
		getContentPane().add(lblyear);
		
		textFieldyear = new JTextField();
		textFieldyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldyear.setBounds(586, 15, 116, 22);
		getContentPane().add(textFieldyear);
		textFieldyear.setColumns(10);
		
		
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				student_login stlogin=new student_login();
				stlogin.setVisible(true);
			}
		});
		btnLogout.setBounds(1210, 14, 97, 25);
		getContentPane().add(btnLogout);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChangePassword.setBounds(1031, 14, 149, 25);
		getContentPane().add(btnChangePassword);
		
		JLabel lblPresentSemesterCouses = new JLabel("Present Semester Couses and Attendance");
		lblPresentSemesterCouses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPresentSemesterCouses.setBounds(434, 54, 403, 22);
		contentPane.add(lblPresentSemesterCouses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 89, 1288, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		JButton btnSeeSemesterTimetable = new JButton("See semester timetable");
		btnSeeSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSeeSemesterTimetable.setBounds(793, 14, 170, 25);
		contentPane.add(btnSeeSemesterTimetable);
		
		
	}
	
	public student_1(String usrname) {
		username=usrname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1353, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		this.frame=this;
		
		JLabel lblRollNo = new JLabel("ROLL NO :");
		lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRollNo.setBounds(12, 17, 77, 16);
		getContentPane().add(lblRollNo);
		
		textFieldrollno = new JTextField();
		textFieldrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldrollno.setBounds(89, 14, 116, 22);
		getContentPane().add(textFieldrollno);
		textFieldrollno.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(253, 17, 56, 16);
		getContentPane().add(lblName);
		
		textFieldname = new JTextField();
		textFieldname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldname.setBounds(306, 14, 170, 22);
		getContentPane().add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblyear = new JLabel("Year :");
		lblyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblyear.setBounds(536, 17, 56, 16);
		getContentPane().add(lblyear);
		
		textFieldyear = new JTextField();
		textFieldyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldyear.setBounds(586, 15, 116, 22);
		getContentPane().add(textFieldyear);
		textFieldyear.setColumns(10);
		
		
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				student_login stlogin=new student_login();
				stlogin.setVisible(true);
			}
		});
		btnLogout.setBounds(1210, 14, 97, 25);
		getContentPane().add(btnLogout);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				student_password_change spc=new student_password_change(username);
				spc.setVisible(true);
			}
		});
		btnChangePassword.setBounds(1031, 14, 149, 25);
		getContentPane().add(btnChangePassword);
		
		conn=sqlconnection.dbconnector();
		try{
			String query="select * from students where sroll=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,username );
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				textFieldrollno.setText(rs.getString("sroll"));
				textFieldname.setText(rs.getString("name"));
				textFieldyear.setText(Integer.toString(rs.getInt("year")));
			}
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		JLabel lblPresentSemesterCouses = new JLabel("Present Semester Couses and Attendance");
		lblPresentSemesterCouses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPresentSemesterCouses.setBounds(434, 54, 403, 22);
		contentPane.add(lblPresentSemesterCouses);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 89, 1288, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		try{
			String query="select * from course_attendance where roll_no=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,username );
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		JButton btnSeeSemesterTimetable = new JButton("See semester timetable");
		btnSeeSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				student_timetable stt=new student_timetable(username);
				stt.setVisible(true);
			}
		});
		btnSeeSemesterTimetable.setBounds(793, 14, 170, 25);
		contentPane.add(btnSeeSemesterTimetable);
	}
}
