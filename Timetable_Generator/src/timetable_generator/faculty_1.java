package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class faculty_1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldfid;
	private JTextField textFieldfn;
	private JTable table;
	private JTable table_1;
	private JTextField textFieldsrn;
	private JTextField textFieldcid;
	private JTextField textFieldtcc;
	private JTextField textFieldtca;
	
	public static faculty_1 frame = new faculty_1();
	private String username;

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
	public void refreshtable_1(){
		try{
			String query="select roll_no,course_id,total_classes,attended_classes from student_attendance where f_id=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,username );
			ResultSet rs=pst.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}

	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public faculty_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1328, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFacultyid.setBounds(12, 13, 100, 19);
		contentPane.add(lblFacultyid);
		
		textFieldfid = new JTextField();
		textFieldfid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldfid.setBounds(103, 13, 116, 22);
		contentPane.add(textFieldfid);
		textFieldfid.setColumns(10);
		
		JLabel lblFacultyname = new JLabel("Faculty_name :");
		lblFacultyname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFacultyname.setBounds(264, 16, 123, 16);
		contentPane.add(lblFacultyname);
		
		textFieldfn = new JTextField();
		textFieldfn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldfn.setBounds(386, 13, 162, 22);
		contentPane.add(textFieldfn);
		textFieldfn.setColumns(10);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogout.setBounds(1201, 12, 97, 25);
		contentPane.add(btnLogout);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChangePassword.setBounds(1045, 12, 135, 25);
		contentPane.add(btnChangePassword);
		
		JButton btnNewButton = new JButton("See semester timetable");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(801, 12, 185, 25);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 90, 558, 189);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCoursesUndertaken = new JLabel("Present Semester undertaken courses");
		lblCoursesUndertaken.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCoursesUndertaken.setBounds(142, 58, 302, 19);
		contentPane.add(lblCoursesUndertaken);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(570, 90, 728, 417);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblStudentAttendance = new JLabel("Student attendance ");
		lblStudentAttendance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStudentAttendance.setBounds(841, 58, 155, 19);
		contentPane.add(lblStudentAttendance);
		
		JLabel lblUpdateAttendance = new JLabel("Update attendance");
		lblUpdateAttendance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUpdateAttendance.setBounds(191, 291, 155, 19);
		contentPane.add(lblUpdateAttendance);
		
		JLabel lblStudentrollno = new JLabel("Student_roll_no :");
		lblStudentrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentrollno.setBounds(12, 330, 116, 16);
		contentPane.add(lblStudentrollno);
		
		textFieldsrn = new JTextField();
		textFieldsrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsrn.setBounds(126, 328, 116, 22);
		contentPane.add(textFieldsrn);
		textFieldsrn.setColumns(10);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(296, 330, 78, 16);
		contentPane.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setBounds(386, 327, 116, 22);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		JLabel lblTotalclasses = new JLabel("Total_classes_conducted :");
		lblTotalclasses.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalclasses.setBounds(12, 377, 178, 16);
		contentPane.add(lblTotalclasses);
		
		textFieldtcc = new JTextField();
		textFieldtcc.setBounds(202, 375, 131, 22);
		contentPane.add(textFieldtcc);
		textFieldtcc.setColumns(10);
		
		JLabel lblTotalclassesattended = new JLabel("Total_classes_attended :");
		lblTotalclassesattended.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalclassesattended.setBounds(12, 429, 178, 16);
		contentPane.add(lblTotalclassesattended);
		
		textFieldtca = new JTextField();
		textFieldtca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldtca.setBounds(202, 427, 131, 22);
		contentPane.add(textFieldtca);
		textFieldtca.setColumns(10);
		
		JButton btnUpdateAttendance = new JButton("Update attendance");
		btnUpdateAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdateAttendance.setBounds(188, 482, 145, 25);
		contentPane.add(btnUpdateAttendance);
	}
	
	public faculty_1(String usrname) {
		username=usrname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1328, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFacultyid.setBounds(12, 13, 100, 19);
		contentPane.add(lblFacultyid);
		
		textFieldfid = new JTextField();
		textFieldfid.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldfid.setBounds(103, 13, 116, 22);
		contentPane.add(textFieldfid);
		textFieldfid.setColumns(10);
		
		JLabel lblFacultyname = new JLabel("Faculty_name :");
		lblFacultyname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFacultyname.setBounds(264, 16, 123, 16);
		contentPane.add(lblFacultyname);
		
		textFieldfn = new JTextField();
		textFieldfn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textFieldfn.setBounds(386, 13, 162, 22);
		contentPane.add(textFieldfn);
		textFieldfn.setColumns(10);
		
		conn=sqlconnection.dbconnector();
		try{
			String query="select * from faculty where f_id=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,username );
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				textFieldfid.setText(Integer.toString(rs.getInt("f_id")));
				textFieldfn.setText(rs.getString("f_name"));
			}
			rs.close();
			pst.close();
			//conn.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				faculty_login fclogin=new faculty_login();
				fclogin.setVisible(true);
			}
		});
		btnLogout.setBounds(1201, 12, 97, 25);
		contentPane.add(btnLogout);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				faculty_password_change fpc=new faculty_password_change(username);
				fpc.setVisible(true);
			}
		});
		btnChangePassword.setBounds(1045, 12, 135, 25);
		contentPane.add(btnChangePassword);
		
		JButton btnNewButton = new JButton("See semester timetable");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				faculty_timetable ftt=new faculty_timetable(username);
				ftt.setVisible(true);
			}
		});
		btnNewButton.setBounds(801, 12, 185, 25);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 90, 558, 189);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblCoursesUndertaken = new JLabel("Present Semester undertaken courses");
		lblCoursesUndertaken.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCoursesUndertaken.setBounds(142, 58, 302, 19);
		contentPane.add(lblCoursesUndertaken);
		
		try{
			String query="select cid,cname,hours_weekly from courses where f_id=?";
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
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(570, 90, 728, 417);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblStudentAttendance = new JLabel("Student attendance ");
		lblStudentAttendance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblStudentAttendance.setBounds(841, 58, 155, 19);
		contentPane.add(lblStudentAttendance);
		
		refreshtable_1();
		
		JLabel lblUpdateAttendance = new JLabel("Update attendance");
		lblUpdateAttendance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblUpdateAttendance.setBounds(191, 291, 155, 19);
		contentPane.add(lblUpdateAttendance);
		
		JLabel lblStudentrollno = new JLabel("Student_roll_no :");
		lblStudentrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStudentrollno.setBounds(12, 330, 116, 16);
		contentPane.add(lblStudentrollno);
		
		textFieldsrn = new JTextField();
		textFieldsrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsrn.setBounds(126, 328, 116, 22);
		contentPane.add(textFieldsrn);
		textFieldsrn.setColumns(10);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(296, 330, 78, 16);
		contentPane.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setBounds(386, 327, 116, 22);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		JLabel lblTotalclasses = new JLabel("Total_classes_conducted :");
		lblTotalclasses.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalclasses.setBounds(12, 377, 178, 16);
		contentPane.add(lblTotalclasses);
		
		textFieldtcc = new JTextField();
		textFieldtcc.setBounds(202, 375, 131, 22);
		contentPane.add(textFieldtcc);
		textFieldtcc.setColumns(10);
		
		JLabel lblTotalclassesattended = new JLabel("Total_classes_attended :");
		lblTotalclassesattended.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotalclassesattended.setBounds(12, 429, 178, 16);
		contentPane.add(lblTotalclassesattended);
		
		textFieldtca = new JTextField();
		textFieldtca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldtca.setBounds(202, 427, 131, 22);
		contentPane.add(textFieldtca);
		textFieldtca.setColumns(10);
		
		JButton btnUpdateAttendance = new JButton("Update attendance");
		btnUpdateAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFieldcid.getText().equals("")&&!textFieldtcc.getText().equals("")&&!textFieldtca.getText().equals("")&&!textFieldsrn.getText().equals("")){
					int k=0;
					try{
						String query="select cid from courses where f_id=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,username );
						ResultSet rs=pst.executeQuery();
						int course_id=Integer.parseInt(textFieldcid.getText());
						while(rs.next()){
							if(course_id==rs.getInt("cid")){
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
						try{
							String query="update attendance set total_no_classes=? ,atd=? where sroll=? and course_id=?";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.setInt(1,Integer.parseInt(textFieldtcc.getText()) );
							pst.setInt(2,Integer.parseInt(textFieldtca.getText()) );
							pst.setString(3,textFieldsrn.getText() );
							pst.setString(4,textFieldcid.getText() );
							pst.executeQuery();
							JOptionPane.showMessageDialog(null, "Attendance updated successfully if the student is registered under the given course");
							pst.close();
							//conn.close();
						}
						catch(Exception e){
							JOptionPane.showMessageDialog(null, e);
						}
					}
					else JOptionPane.showMessageDialog(null, "Course is not registered under you");
					
					refreshtable_1();
				}
				else JOptionPane.showMessageDialog(null, "None of the fiels can be empty");
			}
		});
		btnUpdateAttendance.setBounds(188, 482, 145, 25);
		contentPane.add(btnUpdateAttendance);
	}


}
