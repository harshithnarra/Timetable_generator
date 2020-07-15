package timetable_generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_faculty_timetable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private String uname;
	private String uname1;
	public static admin_faculty_timetable frame = new admin_faculty_timetable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_faculty_timetable frame = new admin_faculty_timetable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public admin_faculty_timetable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1341, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTimetable = new JLabel("Timetable");
		lblTimetable.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimetable.setBounds(609, 10, 115, 25);
		contentPane.add(lblTimetable);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setBounds(12, 13, 67, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 1299, 486);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	public admin_faculty_timetable(String username,String username1) {
		uname=username;
		uname1=username1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1341, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.frame=this;
		
		
		JLabel lblTimetable = new JLabel("Timetable");
		lblTimetable.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTimetable.setBounds(609, 10, 115, 25);
		contentPane.add(lblTimetable);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_tt1 adt1=new admin_tt1(uname);
				adt1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 67, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 1299, 486);
		contentPane.add(scrollPane);
		
		table = new JTable(new MyTableModel1(uname1));
		scrollPane.setViewportView(table);
	}

}
