package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_student extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldrn;
	private JTextField textFieldname;
	private JTextField textFieldyear;
	private JTextField textFieldbid;
	private JTextField textFieldsid;
	

	public static admin_student frame = new admin_student();
	private String uname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_student frame = new admin_student();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	
	public void refresh_table(){
		try{
			String query="select sroll,name,year,branch_id,school_id from students";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	/**
	 * Create the frame.
	 */
	public admin_student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblStudentManager = new JLabel("Student Manager");
		lblStudentManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentManager.setBounds(412, 13, 181, 21);
		contentPane.add(lblStudentManager);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 963, 248);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(10, 9, 75, 25);
		contentPane.add(btnBack);
		
		JLabel lblRollNo = new JLabel("Roll No :");
		lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRollNo.setBounds(12, 339, 60, 16);
		contentPane.add(lblRollNo);
		
		textFieldrn = new JTextField();
		textFieldrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldrn.setBounds(70, 337, 116, 22);
		contentPane.add(textFieldrn);
		textFieldrn.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(286, 340, 56, 16);
		contentPane.add(lblName);
		
		textFieldname = new JTextField();
		textFieldname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldname.setBounds(340, 337, 116, 22);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(568, 340, 56, 16);
		contentPane.add(lblYear);
		
		textFieldyear = new JTextField();
		textFieldyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldyear.setBounds(623, 337, 116, 22);
		contentPane.add(textFieldyear);
		textFieldyear.setColumns(10);
		
		JLabel lblBranchid = new JLabel("Branch_id :");
		lblBranchid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBranchid.setBounds(10, 407, 75, 16);
		contentPane.add(lblBranchid);
		
		textFieldbid = new JTextField();
		textFieldbid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldbid.setBounds(91, 405, 116, 22);
		contentPane.add(textFieldbid);
		textFieldbid.setColumns(10);
		
		JLabel lblSchoolid = new JLabel("School_id :");
		lblSchoolid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchoolid.setBounds(301, 408, 75, 16);
		contentPane.add(lblSchoolid);
		
		textFieldsid = new JTextField();
		textFieldsid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsid.setBounds(381, 405, 116, 22);
		contentPane.add(textFieldsid);
		textFieldsid.setColumns(10);
		
		JButton btnAddStudent = new JButton("Add student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddStudent.setBounds(110, 487, 116, 25);
		contentPane.add(btnAddStudent);
		
		JButton btnUpdateStudent = new JButton("Update student");
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdateStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateStudent.setBounds(381, 487, 133, 25);
		contentPane.add(btnUpdateStudent);
		
		JButton btnDeleteStudent = new JButton("Delete student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteStudent.setBounds(623, 488, 133, 25);
		contentPane.add(btnDeleteStudent);
	}
	
	public admin_student(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblStudentManager = new JLabel("Student Manager");
		lblStudentManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentManager.setBounds(412, 13, 181, 21);
		contentPane.add(lblStudentManager);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 963, 248);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn=sqlconnection.dbconnector();
		refresh_table();
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_1 ad1=new admin_1(uname);
				ad1.setVisible(true);
			}
		});
		btnBack.setBounds(10, 9, 75, 25);
		contentPane.add(btnBack);
		
		JLabel lblRollNo = new JLabel("Roll No :");
		lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRollNo.setBounds(12, 339, 60, 16);
		contentPane.add(lblRollNo);
		
		textFieldrn = new JTextField();
		textFieldrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldrn.setBounds(70, 337, 116, 22);
		contentPane.add(textFieldrn);
		textFieldrn.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(286, 340, 56, 16);
		contentPane.add(lblName);
		
		textFieldname = new JTextField();
		textFieldname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldname.setBounds(340, 337, 116, 22);
		contentPane.add(textFieldname);
		textFieldname.setColumns(10);
		
		JLabel lblYear = new JLabel("Year :");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblYear.setBounds(568, 340, 56, 16);
		contentPane.add(lblYear);
		
		textFieldyear = new JTextField();
		textFieldyear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldyear.setBounds(623, 337, 116, 22);
		contentPane.add(textFieldyear);
		textFieldyear.setColumns(10);
		
		JLabel lblBranchid = new JLabel("Branch_id :");
		lblBranchid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBranchid.setBounds(10, 407, 75, 16);
		contentPane.add(lblBranchid);
		
		textFieldbid = new JTextField();
		textFieldbid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldbid.setBounds(91, 405, 116, 22);
		contentPane.add(textFieldbid);
		textFieldbid.setColumns(10);
		
		JLabel lblSchoolid = new JLabel("School_id :");
		lblSchoolid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchoolid.setBounds(301, 408, 75, 16);
		contentPane.add(lblSchoolid);
		
		textFieldsid = new JTextField();
		textFieldsid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsid.setBounds(381, 405, 116, 22);
		contentPane.add(textFieldsid);
		textFieldsid.setColumns(10);
		
		JButton btnAddStudent = new JButton("Add student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{//primary key compulsory, inserts even if other fields are empty as NULL 
					String query="insert into students values(?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textFieldrn.getText() );
					pst.setString(2,textFieldname.getText());
					if(!textFieldyear.getText().equals("")) pst.setInt(3,Integer.parseInt(textFieldyear.getText())); else pst.setNull(3,Types.INTEGER);
					pst.setString(4,textFieldrn.getText() );
					if(!textFieldbid.getText().equals("")) pst.setInt(5,Integer.parseInt(textFieldbid.getText()));   else pst.setNull(5,Types.INTEGER);
					if(!textFieldsid.getText().equals("")) pst.setInt(6,Integer.parseInt(textFieldsid.getText()));   else pst.setNull(6,Types.INTEGER);
					pst.executeQuery();
					JOptionPane.showMessageDialog(null, "Student added successfully");
					pst.close();
					refresh_table();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddStudent.setBounds(110, 487, 116, 25);
		contentPane.add(btnAddStudent);
		
		JButton btnUpdateStudent = new JButton("Update student");
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select sroll from students";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String rn=textFieldrn.getText();
					while(rs.next()){
						if(rn.equals(rs.getString("sroll"))){
							k=1;
							break;
						}
					}
					pst.close();
					rs.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				if(k==1){
					try{//primary key compulsory, updates NULL if the field is left empty
						String query="update students set name=? ,year=?, branch_id=?,school_id=? where sroll=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,textFieldname.getText());
						if(!textFieldyear.getText().equals("")) pst.setInt(2,Integer.parseInt(textFieldyear.getText())); else pst.setNull(2,Types.INTEGER);
						if(!textFieldbid.getText().equals("")) pst.setInt(3,Integer.parseInt(textFieldbid.getText()));   else pst.setNull(3,Types.INTEGER);
						if(!textFieldsid.getText().equals("")) pst.setInt(4,Integer.parseInt(textFieldsid.getText()));   else pst.setNull(4,Types.INTEGER);
						pst.setString(5,textFieldrn.getText() );
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Student updated successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid Roll NO");
			}
		});
		btnUpdateStudent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateStudent.setBounds(381, 487, 133, 25);
		contentPane.add(btnUpdateStudent);
		
		JButton btnDeleteStudent = new JButton("Delete student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select sroll from students";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String rn=textFieldrn.getText();
					while(rs.next()){
						if(rn.equals(rs.getString("sroll"))){
							k=1;
							break;
						}
					}
					pst.close();
					rs.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				if(k==1){
					try{//primary key compulsory and is the only required field,doesn't take other fields into consideration
						String query="delete from students where sroll=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,textFieldrn.getText() );
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Student deleted successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid Roll NO");
			}
		});
		btnDeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteStudent.setBounds(623, 488, 133, 25);
		contentPane.add(btnDeleteStudent);
	}

}
