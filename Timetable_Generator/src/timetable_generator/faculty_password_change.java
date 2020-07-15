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
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class faculty_password_change extends JFrame {

	private JPanel contentPane;

	public static faculty_password_change frame = new faculty_password_change();
	private String uname;
	private String password;
	private JPasswordField passwordFieldpp;
	private JPasswordField passwordFieldnp;
	private JPasswordField passwordFieldrnp;
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
	public faculty_password_change() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChangePassword.setBounds(138, 13, 140, 26);
		contentPane.add(lblChangePassword);
		
		JLabel lblPresentPassword = new JLabel("Present Password :");
		lblPresentPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPresentPassword.setBounds(12, 66, 133, 16);
		contentPane.add(lblPresentPassword);
		
		passwordFieldpp = new JPasswordField();
		passwordFieldpp.setBounds(188, 64, 130, 22);
		contentPane.add(passwordFieldpp);
		
		JLabel lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewPassword.setBounds(12, 120, 114, 16);
		contentPane.add(lblNewPassword);
		
		passwordFieldnp = new JPasswordField();
		passwordFieldnp.setBounds(188, 118, 130, 22);
		contentPane.add(passwordFieldnp);
		
		JLabel lblRetypeNewPassword = new JLabel("Retype New Password:");
		lblRetypeNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRetypeNewPassword.setBounds(12, 175, 156, 16);
		contentPane.add(lblRetypeNewPassword);
		
		passwordFieldrnp = new JPasswordField();
		passwordFieldrnp.setBounds(188, 173, 130, 22);
		contentPane.add(passwordFieldrnp);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnDone.setBounds(150, 226, 97, 25);
		contentPane.add(btnDone);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(12, 15, 69, 25);
		contentPane.add(btnBack);
	}

	public faculty_password_change(String username) {
		uname=username;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChangePassword.setBounds(138, 13, 140, 26);
		contentPane.add(lblChangePassword);
		
		JLabel lblPresentPassword = new JLabel("Present Password :");
		lblPresentPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPresentPassword.setBounds(12, 66, 133, 16);
		contentPane.add(lblPresentPassword);
		
		passwordFieldpp = new JPasswordField();
		passwordFieldpp.setBounds(188, 64, 130, 22);
		contentPane.add(passwordFieldpp);
		
		JLabel lblNewPassword = new JLabel("New Password :");
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewPassword.setBounds(12, 120, 114, 16);
		contentPane.add(lblNewPassword);
		
		passwordFieldnp = new JPasswordField();
		passwordFieldnp.setBounds(188, 118, 130, 22);
		contentPane.add(passwordFieldnp);
		
		JLabel lblRetypeNewPassword = new JLabel("Retype New Password:");
		lblRetypeNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRetypeNewPassword.setBounds(12, 175, 156, 16);
		contentPane.add(lblRetypeNewPassword);
		
		passwordFieldrnp = new JPasswordField();
		passwordFieldrnp.setBounds(188, 173, 130, 22);
		contentPane.add(passwordFieldrnp);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from faculty where f_id=?";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,uname );
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
						password=rs.getString("password");
					}
					rs.close();
					pst.close();
					//conn.close();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, e);
				}
				if(password.equals(passwordFieldpp.getText())){
					if(passwordFieldnp.getText().equals(passwordFieldrnp.getText())){
						try{
							String query="update faculty set password=? where f_id=?";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.setString(1,passwordFieldnp.getText() );
							pst.setString(2,uname );
							pst.executeQuery();
							JOptionPane.showMessageDialog(null, "Password changed successfully");
							pst.close();
							//conn.close();
						}
						catch(Exception e){
							JOptionPane.showMessageDialog(null, e);
						}
					}
					else JOptionPane.showMessageDialog(null, "Two new passwords do not match");
				}
				else JOptionPane.showMessageDialog(null, "Present Password incorrect");
			}
		});
		btnDone.setBounds(150, 226, 97, 25);
		contentPane.add(btnDone);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				faculty_1 fc1=new faculty_1(uname);
				fc1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 15, 69, 25);
		contentPane.add(btnBack);
		
		conn=sqlconnection.dbconnector();
	}

}
