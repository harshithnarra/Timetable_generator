package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;


public class admin_course extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldcid;
	private JTextField textField_cn;
	private JTextField textField_fid;
	private JTextField textField_hw;
	private JTextField textField_sid;
	private JTextField textFieldct;
	private JTable table;

	private String uname;
	public static admin_course frame = new admin_course();
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
	private JTextField textFieldcti;
	
	public void refresh_table(){
		try{
			String query="select cid,cname,f_id,hours_weekly,school_id,course_type_id,classroom_type_id from courses";
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
	public admin_course() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 987, 525);
		contentPane.add(panel);
		
		JLabel lblCourseManager = new JLabel("Course Manager");
		lblCourseManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCourseManager.setBounds(412, 13, 181, 21);
		panel.add(lblCourseManager);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 963, 248);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(10, 9, 75, 25);
		panel.add(button);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(12, 339, 73, 16);
		panel.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setColumns(10);
		textFieldcid.setBounds(91, 336, 116, 22);
		panel.add(textFieldcid);
		
		JLabel lblCoursename = new JLabel("Course_name :");
		lblCoursename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCoursename.setBounds(286, 340, 108, 16);
		panel.add(lblCoursename);
		
		textField_cn = new JTextField();
		textField_cn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_cn.setColumns(10);
		textField_cn.setBounds(398, 336, 116, 22);
		panel.add(textField_cn);
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyid.setBounds(568, 340, 83, 16);
		panel.add(lblFacultyid);
		
		textField_fid = new JTextField();
		textField_fid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_fid.setColumns(10);
		textField_fid.setBounds(650, 336, 116, 22);
		panel.add(textField_fid);
		
		JLabel lblHoursweek = new JLabel("Hours/week :");
		lblHoursweek.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoursweek.setBounds(10, 407, 92, 16);
		panel.add(lblHoursweek);
		
		textField_hw = new JTextField();
		textField_hw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_hw.setColumns(10);
		textField_hw.setBounds(114, 404, 116, 22);
		panel.add(textField_hw);
		
		JLabel label_sid = new JLabel("School_id :");
		label_sid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_sid.setBounds(301, 408, 75, 16);
		panel.add(label_sid);
		
		textField_sid = new JTextField();
		textField_sid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_sid.setColumns(10);
		textField_sid.setBounds(381, 405, 116, 22);
		panel.add(textField_sid);
		
		JButton btnAddCourse = new JButton("Add course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddCourse.setBounds(110, 487, 116, 25);
		panel.add(btnAddCourse);
		
		JButton btnUpdateCourse = new JButton("Update course");
		btnUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdateCourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateCourse.setBounds(381, 487, 133, 25);
		panel.add(btnUpdateCourse);
		
		JButton btnDeleteCourse = new JButton("Delete course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteCourse.setBounds(623, 488, 133, 25);
		panel.add(btnDeleteCourse);
		
		JLabel lblCousetype = new JLabel("Couse_type :");
		lblCousetype.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCousetype.setBounds(568, 408, 92, 16);
		panel.add(lblCousetype);
		
		textFieldct = new JTextField();
		textFieldct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldct.setBounds(664, 405, 116, 22);
		panel.add(textFieldct);
		textFieldct.setColumns(10);
		
		JLabel lblenterFor = new JLabel("(Enter 1 for theory course and 2 for lab course)");
		lblenterFor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblenterFor.setBounds(553, 437, 263, 16);
		panel.add(lblenterFor);
		
		JLabel lblClassroomtypeid = new JLabel("Classroom_type_id :");
		lblClassroomtypeid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClassroomtypeid.setBounds(824, 376, 133, 16);
		panel.add(lblClassroomtypeid);
		
		textFieldcti = new JTextField();
		textFieldcti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcti.setBounds(841, 404, 116, 22);
		panel.add(textFieldcti);
		textFieldcti.setColumns(10);
	}
	
	public admin_course(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1009, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(0, 0, 987, 525);
		contentPane.add(panel);
		
		JLabel lblCourseManager = new JLabel("Course Manager");
		lblCourseManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCourseManager.setBounds(412, 13, 181, 21);
		panel.add(lblCourseManager);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 54, 963, 248);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn=sqlconnection.dbconnector();
		refresh_table();
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_1 ad1=new admin_1(uname);
				ad1.setVisible(true);
			}
		});
		button.setBounds(10, 9, 75, 25);
		panel.add(button);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(12, 339, 73, 16);
		panel.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setColumns(10);
		textFieldcid.setBounds(91, 336, 116, 22);
		panel.add(textFieldcid);
		
		JLabel lblCoursename = new JLabel("Course_name :");
		lblCoursename.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCoursename.setBounds(286, 340, 108, 16);
		panel.add(lblCoursename);
		
		textField_cn = new JTextField();
		textField_cn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_cn.setColumns(10);
		textField_cn.setBounds(398, 336, 116, 22);
		panel.add(textField_cn);
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyid.setBounds(568, 340, 83, 16);
		panel.add(lblFacultyid);
		
		textField_fid = new JTextField();
		textField_fid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_fid.setColumns(10);
		textField_fid.setBounds(650, 336, 116, 22);
		panel.add(textField_fid);
		
		JLabel lblHoursweek = new JLabel("Hours/week :");
		lblHoursweek.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoursweek.setBounds(10, 407, 92, 16);
		panel.add(lblHoursweek);
		
		textField_hw = new JTextField();
		textField_hw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_hw.setColumns(10);
		textField_hw.setBounds(114, 404, 116, 22);
		panel.add(textField_hw);
		
		JLabel label_sid = new JLabel("School_id :");
		label_sid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_sid.setBounds(301, 408, 75, 16);
		panel.add(label_sid);
		
		textField_sid = new JTextField();
		textField_sid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_sid.setColumns(10);
		textField_sid.setBounds(381, 405, 116, 22);
		panel.add(textField_sid);
		
		JButton btnAddCourse = new JButton("Add course");
		btnAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{//primary key compulsory, inserts even if other fields are empty as NULL 
					String query="insert into courses values(?,?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					if(!textFieldcid.getText().equals("")) pst.setInt(1,Integer.parseInt(textFieldcid.getText())); else pst.setNull(1,Types.INTEGER);
					pst.setString(2,textField_cn.getText() );
					if(!textField_fid.getText().equals("")) pst.setInt(3,Integer.parseInt(textField_fid.getText())); else pst.setNull(3,Types.INTEGER);
					if(!textField_hw.getText().equals("")) pst.setInt(4,Integer.parseInt(textField_hw.getText())); else pst.setNull(4,Types.INTEGER);
					if(!textField_sid.getText().equals("")) pst.setInt(5,Integer.parseInt(textField_sid.getText()));   else pst.setNull(5,Types.INTEGER);
					if(!textFieldct.getText().equals("")) pst.setInt(6,Integer.parseInt(textFieldct.getText()));   else pst.setNull(6,Types.INTEGER);
					if(!textFieldcti.getText().equals("")) pst.setInt(7,Integer.parseInt(textFieldcti.getText()));   else pst.setNull(7,Types.INTEGER);
					pst.executeQuery();
					JOptionPane.showMessageDialog(null, "Course added successfully");
					pst.close();
					refresh_table();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddCourse.setBounds(110, 487, 116, 25);
		panel.add(btnAddCourse);
		
		JButton btnUpdateCourse = new JButton("Update course");
		btnUpdateCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select cid from courses";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String cid=textFieldcid.getText();
					while(rs.next()){
						if(cid.equals(rs.getString("cid"))){
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
						String query="update courses set cname=? ,f_id=?,hours_weekly=?,school_id=?,course_type_id=?,classroom_type_id=? where cid=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,textField_cn.getText() );
						if(!textField_fid.getText().equals("")) pst.setInt(2,Integer.parseInt(textField_fid.getText())); else pst.setNull(2,Types.INTEGER);
						if(!textField_hw.getText().equals("")) pst.setInt(3,Integer.parseInt(textField_hw.getText())); else pst.setNull(3,Types.INTEGER);
						if(!textField_sid.getText().equals("")) pst.setInt(4,Integer.parseInt(textField_sid.getText()));   else pst.setNull(4,Types.INTEGER);
						if(!textFieldct.getText().equals("")) pst.setInt(5,Integer.parseInt(textFieldct.getText()));   else pst.setNull(5,Types.INTEGER);
						if(!textFieldcti.getText().equals("")) pst.setInt(6,Integer.parseInt(textFieldcti.getText()));   else pst.setNull(6,Types.INTEGER);
						if(!textFieldcid.getText().equals("")) pst.setInt(7,Integer.parseInt(textFieldcid.getText())); else pst.setNull(7,Types.INTEGER);
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Course updated successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid Course_id");
			}
		});
		btnUpdateCourse.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateCourse.setBounds(381, 487, 133, 25);
		panel.add(btnUpdateCourse);
		
		JButton btnDeleteCourse = new JButton("Delete course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select cid from courses";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String cid=textFieldcid.getText();
					while(rs.next()){
						if(cid.equals(rs.getString("cid"))){
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
						String query="delete from courses where cid=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setInt(1,Integer.parseInt(textFieldcid.getText()));
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Course deleted successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid Course_id");
			}
		});
		btnDeleteCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteCourse.setBounds(623, 488, 133, 25);
		panel.add(btnDeleteCourse);
		
		JLabel lblCousetype = new JLabel("Couse_type :");
		lblCousetype.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCousetype.setBounds(568, 408, 92, 16);
		panel.add(lblCousetype);
		
		textFieldct = new JTextField();
		textFieldct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldct.setBounds(664, 405, 116, 22);
		panel.add(textFieldct);
		textFieldct.setColumns(10);
		
		JLabel lblenterFor = new JLabel("(Enter 1 for theory course and 2 for lab course)");
		lblenterFor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblenterFor.setBounds(553, 437, 263, 16);
		panel.add(lblenterFor);
		
		JLabel lblClassroomtypeid = new JLabel("Classroom_type_id :");
		lblClassroomtypeid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClassroomtypeid.setBounds(824, 376, 133, 16);
		panel.add(lblClassroomtypeid);
		
		textFieldcti = new JTextField();
		textFieldcti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcti.setBounds(841, 404, 116, 22);
		panel.add(textFieldcti);
		textFieldcti.setColumns(10);
	}
}
