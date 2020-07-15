package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class admin_1 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldaid;
	private JTextField textField;

	public static admin_1 frame = new admin_1();
	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_1 frame = new admin_1();
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
	public admin_1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblAdminid = new JLabel("Admin_id :");
		lblAdminid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdminid.setBounds(12, 13, 75, 16);
		contentPane.add(lblAdminid);
		
		textFieldaid = new JTextField();
		textFieldaid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldaid.setBounds(82, 11, 116, 22);
		contentPane.add(textFieldaid);
		textFieldaid.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(12, 61, 63, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(82, 59, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnLogout = new JButton("logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogout.setBounds(413, 10, 133, 25);
		contentPane.add(btnLogout);
		
		JButton btnChangePassword = new JButton("change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnChangePassword.setBounds(413, 58, 133, 25);
		contentPane.add(btnChangePassword);
		
		JButton btnAddupdatedeleteStudent = new JButton("Add/update/delete student");
		btnAddupdatedeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddupdatedeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddupdatedeleteStudent.setBounds(47, 137, 211, 25);
		contentPane.add(btnAddupdatedeleteStudent);
		
		JButton btnAddupdatedeleteFaculty = new JButton("Add/update/delete faculty");
		btnAddupdatedeleteFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddupdatedeleteFaculty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddupdatedeleteFaculty.setBounds(317, 137, 201, 25);
		contentPane.add(btnAddupdatedeleteFaculty);
		
		JButton btnAddupdatedeleteCourse = new JButton("Add/update/delete course");
		btnAddupdatedeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddupdatedeleteCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddupdatedeleteCourse.setBounds(47, 208, 211, 25);
		contentPane.add(btnAddupdatedeleteCourse);
		
		JButton btnAdddeleteCourseEnrolment = new JButton("Add/delete course enrolment");
		btnAdddeleteCourseEnrolment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdddeleteCourseEnrolment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdddeleteCourseEnrolment.setBounds(317, 209, 201, 25);
		contentPane.add(btnAdddeleteCourseEnrolment);
		
		JButton btnGenerateSemesterTimetable = new JButton("Generate Semester timetable");
		btnGenerateSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerateSemesterTimetable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateSemesterTimetable.setBounds(47, 280, 211, 25);
		contentPane.add(btnGenerateSemesterTimetable);
		
		JButton btnSeeSemesterTimetable = new JButton("See semester timetable");
		btnSeeSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSeeSemesterTimetable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeeSemesterTimetable.setBounds(317, 281, 201, 25);
		contentPane.add(btnSeeSemesterTimetable);
	}
	
	public admin_1(String usrname) {
		username=usrname;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblAdminid = new JLabel("Admin_id :");
		lblAdminid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAdminid.setBounds(12, 13, 75, 16);
		contentPane.add(lblAdminid);
		
		textFieldaid = new JTextField();
		textFieldaid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldaid.setBounds(82, 11, 116, 22);
		contentPane.add(textFieldaid);
		textFieldaid.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(12, 61, 63, 16);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(82, 59, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		conn=sqlconnection.dbconnector();
		try{
			String query="select * from admins where a_id=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,username );
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				textFieldaid.setText(Integer.toString(rs.getInt("a_id")));
				textField.setText(rs.getString("name"));
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
				admin_login adlogin=new admin_login();
				adlogin.setVisible(true);
			}
		});
		btnLogout.setBounds(413, 10, 133, 25);
		contentPane.add(btnLogout);
		
		JButton btnChangePassword = new JButton("change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_password_change apc=new admin_password_change(username);
				apc.setVisible(true);
			}
		});
		btnChangePassword.setBounds(413, 58, 133, 25);
		contentPane.add(btnChangePassword);
		
		JButton btnAddupdatedeleteStudent = new JButton("Add/update/delete student");
		btnAddupdatedeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_student as=new admin_student(username);
				as.setVisible(true);
			}
		});
		btnAddupdatedeleteStudent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddupdatedeleteStudent.setBounds(47, 137, 211, 25);
		contentPane.add(btnAddupdatedeleteStudent);
		
		JButton btnAddupdatedeleteFaculty = new JButton("Add/update/delete faculty");
		btnAddupdatedeleteFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_faculty af=new admin_faculty(username);
				af.setVisible(true);
			}
		});
		btnAddupdatedeleteFaculty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddupdatedeleteFaculty.setBounds(317, 137, 201, 25);
		contentPane.add(btnAddupdatedeleteFaculty);
		
		JButton btnAddupdatedeleteCourse = new JButton("Add/update/delete course");
		btnAddupdatedeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_course ac=new admin_course(username);
				ac.setVisible(true);
			}
		});
		btnAddupdatedeleteCourse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddupdatedeleteCourse.setBounds(47, 208, 211, 25);
		contentPane.add(btnAddupdatedeleteCourse);
		
		JButton btnAdddeleteCourseEnrolment = new JButton("Add/delete course enrolment");
		btnAdddeleteCourseEnrolment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_enroll ae=new admin_enroll(username);
				ae.setVisible(true);
			}
		});
		btnAdddeleteCourseEnrolment.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAdddeleteCourseEnrolment.setBounds(317, 209, 201, 25);
		contentPane.add(btnAdddeleteCourseEnrolment);
		
		JButton btnGenerateSemesterTimetable = new JButton("Generate Semester timetable");
		btnGenerateSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="delete from timetable";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.executeQuery();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				generate_tt_1 gt1=new generate_tt_1();
			}
		});
		btnGenerateSemesterTimetable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateSemesterTimetable.setBounds(47, 280, 211, 25);
		contentPane.add(btnGenerateSemesterTimetable);
		
		JButton btnSeeSemesterTimetable = new JButton("See semester timetable");
		btnSeeSemesterTimetable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_tt1 att=new admin_tt1(username);
				att.setVisible(true);
			}
		});
		btnSeeSemesterTimetable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSeeSemesterTimetable.setBounds(317, 281, 201, 25);
		contentPane.add(btnSeeSemesterTimetable);
	}

}
