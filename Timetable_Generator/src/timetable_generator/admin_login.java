package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class admin_login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldusrname;
	private JPasswordField passwordField;

	public static admin_login frame = new admin_login();
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
	/**
	 * Create the frame.
	 */
	public admin_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		
		textFieldusrname = new JTextField();
		textFieldusrname.setBounds(171, 65, 116, 22);
		contentPane.add(textFieldusrname);
		textFieldusrname.setColumns(10);
		
		JLabel lblusrname = new JLabel("Username :");
		lblusrname.setLabelFor(textFieldusrname);
		lblusrname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblusrname.setBounds(73, 71, 85, 16);
		contentPane.add(lblusrname);
		
		JLabel lblheading = new JLabel("Admin login");
		lblheading.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblheading.setBounds(169, 13, 136, 22);
		contentPane.add(lblheading);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(73, 130, 85, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		lblPassword.setLabelFor(passwordField);
		passwordField.setBounds(171, 128, 116, 22);
		contentPane.add(passwordField);
		
		JButton btnlogin = new JButton("login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from admins where a_id=? and password=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textFieldusrname.getText() );
					pst.setString(2,passwordField.getText() );
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()){
						count=count+1;
					}
					if(count==1){
						JOptionPane.showMessageDialog(null,"login successful");
						frame.dispose();
						admin_1 st1=new admin_1(textFieldusrname.getText());
						st1.setVisible(true);
					}
					else if(count>1)JOptionPane.showMessageDialog(null,"More than one account with same username and password");
					else JOptionPane.showMessageDialog(null,"incorrect username or password");
					rs.close();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnlogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnlogin.setBounds(171, 199, 97, 25);
		contentPane.add(btnlogin);
		
		JButton btnback = new JButton("Back");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				login_type lgntype=new login_type();
				lgntype.frame.setVisible(true);
			}
		});
		btnback.setBounds(12, 10, 70, 25);
		contentPane.add(btnback);
		
		conn=sqlconnection.dbconnector();
	}
}
