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

public class admin_faculty extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldfid;
	private JTextField textFieldfn;
	private JTextField textFieldsid;

	public static admin_faculty frame = new admin_faculty();
	private String uname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_faculty frame = new admin_faculty();
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
			String query="select f_id,f_name,school_id from faculty";
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
	public admin_faculty() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblFacultyManager = new JLabel("Faculty Manager");
		lblFacultyManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFacultyManager.setBounds(335, 13, 178, 25);
		contentPane.add(lblFacultyManager);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(12, 13, 75, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 829, 301);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyid.setBounds(12, 415, 81, 16);
		contentPane.add(lblFacultyid);
		
		textFieldfid = new JTextField();
		textFieldfid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldfid.setBounds(94, 413, 116, 22);
		contentPane.add(textFieldfid);
		textFieldfid.setColumns(10);
		
		JLabel lblFacultyname = new JLabel("Faculty_name :");
		lblFacultyname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyname.setBounds(293, 416, 108, 16);
		contentPane.add(lblFacultyname);
		
		textFieldfn = new JTextField();
		textFieldfn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldfn.setBounds(397, 413, 116, 22);
		contentPane.add(textFieldfn);
		textFieldfn.setColumns(10);
		
		JLabel lblSchoolid = new JLabel("School_id :");
		lblSchoolid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchoolid.setBounds(596, 416, 75, 16);
		contentPane.add(lblSchoolid);
		
		textFieldsid = new JTextField();
		textFieldsid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsid.setBounds(670, 413, 116, 22);
		contentPane.add(textFieldsid);
		textFieldsid.setColumns(10);
		
		JButton btnAddFaculty = new JButton("Add faculty");
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddFaculty.setBounds(84, 491, 126, 25);
		contentPane.add(btnAddFaculty);
		
		JButton btnUpdateFaculty = new JButton("Update faculty");
		btnUpdateFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdateFaculty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateFaculty.setBounds(347, 492, 127, 25);
		contentPane.add(btnUpdateFaculty);
		
		JButton btnDeleteFaculty = new JButton("Delete faculty");
		btnDeleteFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDeleteFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteFaculty.setBounds(596, 492, 126, 25);
		contentPane.add(btnDeleteFaculty);
	}

	public admin_faculty(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblFacultyManager = new JLabel("Faculty Manager");
		lblFacultyManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFacultyManager.setBounds(335, 13, 178, 25);
		contentPane.add(lblFacultyManager);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_1 ad1=new admin_1(uname);
				ad1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 75, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 829, 301);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		conn=sqlconnection.dbconnector();
		refresh_table();
		
		JLabel lblFacultyid = new JLabel("Faculty_id :");
		lblFacultyid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyid.setBounds(12, 415, 81, 16);
		contentPane.add(lblFacultyid);
		
		textFieldfid = new JTextField();
		textFieldfid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldfid.setBounds(94, 413, 116, 22);
		contentPane.add(textFieldfid);
		textFieldfid.setColumns(10);
		
		JLabel lblFacultyname = new JLabel("Faculty_name :");
		lblFacultyname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFacultyname.setBounds(293, 416, 108, 16);
		contentPane.add(lblFacultyname);
		
		textFieldfn = new JTextField();
		textFieldfn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldfn.setBounds(397, 413, 116, 22);
		contentPane.add(textFieldfn);
		textFieldfn.setColumns(10);
		
		JLabel lblSchoolid = new JLabel("School_id :");
		lblSchoolid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSchoolid.setBounds(596, 416, 75, 16);
		contentPane.add(lblSchoolid);
		
		textFieldsid = new JTextField();
		textFieldsid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldsid.setBounds(670, 413, 116, 22);
		contentPane.add(textFieldsid);
		textFieldsid.setColumns(10);
		
		JButton btnAddFaculty = new JButton("Add faculty");
		btnAddFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{//primary key compulsory, inserts even if other fields are empty as NULL 
					String query="insert into faculty values(?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					if(!textFieldfid.getText().equals("")) pst.setInt(1,Integer.parseInt(textFieldfid.getText())); else pst.setNull(1,Types.INTEGER);
					pst.setString(2,textFieldfn.getText() );
					pst.setString(3,textFieldfid.getText() );
					if(!textFieldsid.getText().equals("")) pst.setInt(4,Integer.parseInt(textFieldsid.getText()));   else pst.setNull(4,Types.INTEGER);
					pst.executeQuery();
					JOptionPane.showMessageDialog(null, "Faculty added successfully");
					pst.close();
					refresh_table();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnAddFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddFaculty.setBounds(84, 491, 126, 25);
		contentPane.add(btnAddFaculty);
		
		JButton btnUpdateFaculty = new JButton("Update faculty");
		btnUpdateFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select f_id from faculty";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String fid=textFieldfid.getText();
					while(rs.next()){
						if(fid.equals(rs.getString("f_id"))){
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
						String query="update faculty set f_name=? ,school_id=? where f_id=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,textFieldfn.getText());
						if(!textFieldsid.getText().equals("")) pst.setInt(2,Integer.parseInt(textFieldsid.getText()));   else pst.setNull(2,Types.INTEGER);
						pst.setInt(3,Integer.parseInt(textFieldfid.getText()) );
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Faculty updated successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid faculty_id");
			}
		});
		btnUpdateFaculty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdateFaculty.setBounds(347, 492, 127, 25);
		contentPane.add(btnUpdateFaculty);
		
		JButton btnDeleteFaculty = new JButton("Delete faculty");
		btnDeleteFaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int k=0;
				try{
					String query="select f_id from faculty";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					String fid=textFieldfid.getText();
					while(rs.next()){
						if(fid.equals(rs.getString("f_id"))){
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
						String query="delete from faculty where f_id=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setInt(1,Integer.parseInt(textFieldfid.getText()) );
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Faculty deleted successfully");
						pst.close();
						refresh_table();
						//conn.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(null, e);
					}
				}
				else JOptionPane.showMessageDialog(null, "Invalid faculty_id");
			}
		});
		btnDeleteFaculty.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDeleteFaculty.setBounds(596, 492, 126, 25);
		contentPane.add(btnDeleteFaculty);
	}

}
