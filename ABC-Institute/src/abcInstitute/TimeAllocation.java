package abcInstitute;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TimeAllocation {

	private JFrame frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeAllocation window = new TimeAllocation();
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
	public TimeAllocation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0,659, 457);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new java.awt.Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Not Available Locations");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotAvailableLocations sessions = new NotAvailableLocations();
				sessions.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(57, 124, 225, 78);
		contentPane.add(btnNewButton);
		
		JButton btnNotAvailableSessions = new JButton("Not Available Sessions");
		btnNotAvailableSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionsNotAvailableTimes sessions = new SessionsNotAvailableTimes();
				sessions.setVisible(true);
				frame.dispose();
			}
		});
		btnNotAvailableSessions.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNotAvailableSessions.setBounds(349, 124, 225, 78);
		contentPane.add(btnNotAvailableSessions);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(32, 178, 170));
		btnNewButton_1.setBounds(433, 294, 158, 57);
		contentPane.add(btnNewButton_1);
	}

}
