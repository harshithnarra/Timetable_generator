package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class admin_tt1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	private String uname;
	public static admin_tt1 frame = new admin_tt1();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_tt1 frame = new admin_tt1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public admin_tt1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(12, 13, 68, 25);
		contentPane.add(btnBack);
		
		JLabel lblEnterDataTo = new JLabel("Enter data to view timetable");
		lblEnterDataTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterDataTo.setBounds(131, 15, 231, 16);
		contentPane.add(lblEnterDataTo);
		
		JLabel lblStudentRollNofaculty = new JLabel("Student roll no/Faculty id :");
		lblStudentRollNofaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentRollNofaculty.setBounds(26, 127, 177, 16);
		contentPane.add(lblStudentRollNofaculty);
		
		textField = new JTextField();
		textField.setBounds(203, 125, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnViewStudentTimetable = new JButton("View student timetable");
		btnViewStudentTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnViewStudentTimetable.setBounds(26, 207, 177, 25);
		contentPane.add(btnViewStudentTimetable);
		
		JButton btnViewFacultyTimetable = new JButton("View faculty timetable");
		btnViewFacultyTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnViewFacultyTimetable.setBounds(255, 207, 170, 25);
		contentPane.add(btnViewFacultyTimetable);
	}
	
	public admin_tt1(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_1 ad1=new admin_1(uname);
				ad1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 68, 25);
		contentPane.add(btnBack);
		
		JLabel lblEnterDataTo = new JLabel("Enter data to view timetable");
		lblEnterDataTo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterDataTo.setBounds(131, 15, 231, 16);
		contentPane.add(lblEnterDataTo);
		
		JLabel lblStudentRollNofaculty = new JLabel("Student roll no/Faculty id :");
		lblStudentRollNofaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentRollNofaculty.setBounds(26, 127, 177, 16);
		contentPane.add(lblStudentRollNofaculty);
		
		textField = new JTextField();
		textField.setBounds(203, 125, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		conn=sqlconnection.dbconnector();
		
		JButton btnViewStudentTimetable = new JButton("View student timetable");
		btnViewStudentTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count=0;
				try{
					String query="select * from students where sroll=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textField.getText() );
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						count=count+1;
					}
					rs.close();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				if(count==1){
					frame.dispose();
					admin_student_timetable ast=new admin_student_timetable(uname,textField.getText());
					ast.setVisible(true);
				}
				else if(count>1)JOptionPane.showMessageDialog(null,"More than one student with same roll no");
				else JOptionPane.showMessageDialog(null,"invalid student roll no");
			}
		});
		btnViewStudentTimetable.setBounds(26, 207, 177, 25);
		contentPane.add(btnViewStudentTimetable);
		
		JButton btnViewFacultyTimetable = new JButton("View faculty timetable");
		btnViewFacultyTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count=0;
				try{
					String query="select * from faculty where f_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textField.getText() );
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						count=count+1;
					}
					rs.close();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				if(count==1){
					frame.dispose();
					admin_faculty_timetable aft=new admin_faculty_timetable(uname,textField.getText());
					aft.setVisible(true);
				}
				else if(count>1)JOptionPane.showMessageDialog(null,"More than one faculty with same id");
				else JOptionPane.showMessageDialog(null,"invalid faculty id");
			}
		});
		btnViewFacultyTimetable.setBounds(255, 207, 170, 25);
		contentPane.add(btnViewFacultyTimetable);
	}
}
