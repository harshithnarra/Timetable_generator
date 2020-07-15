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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class admin_enroll extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldsrn;
	private JTextField textFieldcid;
	
	private String uname;
	public static admin_enroll frame = new admin_enroll();

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
	
	public void refresh_table(){
		try{
			String query="select sroll,course_id,total_no_classes,atd attended_classes from attendance";
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
	public admin_enroll() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblEnrollmentManager = new JLabel("Enrollment Manager");
		lblEnrollmentManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnrollmentManager.setBounds(309, 10, 218, 25);
		contentPane.add(lblEnrollmentManager);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(12, 13, 66, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 51, 842, 323);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Student_Roll_No :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 427, 119, 16);
		contentPane.add(lblNewLabel);
		
		textFieldsrn = new JTextField();
		textFieldsrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsrn.setBounds(134, 425, 116, 22);
		contentPane.add(textFieldsrn);
		textFieldsrn.setColumns(10);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(353, 428, 73, 16);
		contentPane.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setBounds(434, 425, 116, 22);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnroll.setBounds(208, 488, 97, 25);
		contentPane.add(btnEnroll);
		
		JButton btnDisenroll = new JButton("Disenroll");
		btnDisenroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDisenroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisenroll.setBounds(434, 488, 97, 25);
		contentPane.add(btnDisenroll);
	}

	public admin_enroll(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblEnrollmentManager = new JLabel("Enrollment Manager");
		lblEnrollmentManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEnrollmentManager.setBounds(309, 10, 218, 25);
		contentPane.add(lblEnrollmentManager);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_1 ad1=new admin_1(uname);
				ad1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 66, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 51, 842, 323);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn=sqlconnection.dbconnector();
		refresh_table();
		
		JLabel lblNewLabel = new JLabel("Student_Roll_No :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 427, 119, 16);
		contentPane.add(lblNewLabel);
		
		textFieldsrn = new JTextField();
		textFieldsrn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsrn.setBounds(134, 425, 116, 22);
		contentPane.add(textFieldsrn);
		textFieldsrn.setColumns(10);
		
		JLabel lblCourseid = new JLabel("Course_id :");
		lblCourseid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCourseid.setBounds(353, 428, 73, 16);
		contentPane.add(lblCourseid);
		
		textFieldcid = new JTextField();
		textFieldcid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldcid.setBounds(434, 425, 116, 22);
		contentPane.add(textFieldcid);
		textFieldcid.setColumns(10);
		
		JButton btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{//primary key(both fields) compulsory
					String query="insert into attendance values(?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textFieldsrn.getText() );
					if(!textFieldcid.getText().equals("")) pst.setInt(2,Integer.parseInt(textFieldcid.getText())); else pst.setNull(2,Types.INTEGER);
					pst.setInt(3,0);
					pst.setInt(4,0);
					pst.executeQuery();
					JOptionPane.showMessageDialog(null, "Student enrolled to course successfully");
					pst.close();
					refresh_table();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnroll.setBounds(208, 488, 97, 25);
		contentPane.add(btnEnroll);
		
		JButton btnDisenroll = new JButton("Disenroll");
		btnDisenroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select sroll,course_id from attendance";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String sroll=textFieldsrn.getText();
					String cid=textFieldcid.getText();
					while(rs.next()){
						if(sroll.equals(rs.getString("sroll")) && cid.equals(rs.getString("course_id"))){
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
					try{//both fields required for deletion
						String query="delete from attendance where sroll=? and course_id=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,textFieldsrn.getText() );
						pst.setInt(2,Integer.parseInt(textFieldcid.getText()));
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Student disenrolled from course successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid student_roll_no or course_id or student is not enrolled in the course");
			}
		});
		btnDisenroll.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDisenroll.setBounds(434, 488, 97, 25);
		contentPane.add(btnDisenroll);
	}

}
