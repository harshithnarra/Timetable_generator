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

public class student_timetable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	private String uname;
	public static student_timetable frame = new student_timetable();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					student_timetable frame = new student_timetable();
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
	public student_timetable() {
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
	
	public student_timetable(String username) {
		uname=username;
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
				student_1 st1=new student_1(uname);
				st1.setVisible(true);
			}
		});
		btnBack.setBounds(12, 13, 67, 25);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 56, 1299, 486);
		contentPane.add(scrollPane);
		
		table = new JTable(new MyTableModel(uname));
		scrollPane.setViewportView(table);
	}
}
