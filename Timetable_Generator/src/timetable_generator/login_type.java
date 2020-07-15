package timetable_generator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login_type {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_type window = new login_type();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login_type() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnadmin = new JButton("Admin");
		btnadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				admin_login admlogin=new admin_login();
				admlogin.setVisible(true);
			}
		});
		btnadmin.setBounds(159, 49, 97, 25);
		frame.getContentPane().add(btnadmin);
		
		JButton btnfaculty = new JButton("Faculty");
		btnfaculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				faculty_login faclogin=new faculty_login();
				faclogin.setVisible(true);
			}
		});
		btnfaculty.setBounds(159, 113, 97, 25);
		frame.getContentPane().add(btnfaculty);
		
		JButton btnstudent = new JButton("Student");
		btnstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				student_login stdlogin=new student_login();
				stdlogin.setVisible(true);
			}
		});
		btnstudent.setBounds(159, 177, 97, 25);
		frame.getContentPane().add(btnstudent);
	}
}
