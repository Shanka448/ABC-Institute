package abcInstitute;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.Color;

public class DashBoard {

	private JFrame frame;
	private String regLec;
	private String regStd,regSbj,regRoom;
	private String latLec = "";
	private String latGrp = "";
	private String latSbj = "";
	private JLabel lblLecVal;
	private JLabel lblGrpVal;
	private JLabel lblSbjVal;
	private JLabel lblRegLecVal;
	private JLabel lblRegStdVal;
	private JLabel lblRegSbjVal;
	private JLabel lblRegRoomVal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashBoard window = new DashBoard();
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
	public DashBoard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("TextField.light"));
		frame.setResizable(true);
		frame.setBackground(UIManager.getColor("info"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0,0,1366, 768);
		frame.setResizable(false);
		
		JPanel leftPane = new JPanel();
		leftPane.setBackground(UIManager.getColor("window"));
		leftPane.setBounds(10, 11, 474, 717);
		frame.getContentPane().add(leftPane);
		leftPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ABC INSTITUE");
		lblNewLabel.setBounds(83, 11, 311, 58);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD, 50));
		leftPane.add(lblNewLabel);
		
		JButton btnWorkingHours = new JButton("Working Hours");
		btnWorkingHours.setBackground(new Color(46, 139, 87));
		btnWorkingHours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddWorkingDaysHours.main(new String[]{});
				frame.dispose();
			}
		});
		btnWorkingHours.setBounds(31, 291, 194, 46);
		leftPane.add(btnWorkingHours);
		btnWorkingHours.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnStudentGroups = new JButton("Student Groups");
		btnStudentGroups.setBackground(new Color(46, 139, 87));
		btnStudentGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Students.main(new String[]{});
				frame.dispose();
			}
		});
		btnStudentGroups.setBounds(253, 291, 194, 46);
		leftPane.add(btnStudentGroups);
		btnStudentGroups.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnLecturers = new JButton("Lecturers");
		btnLecturers.setBackground(new Color(46, 139, 87));
		btnLecturers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Lecturer.main(new String[]{});
				frame.dispose();
			}
		});
		btnLecturers.setBounds(31, 405, 194, 46);
		leftPane.add(btnLecturers);
		btnLecturers.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnTags = new JButton("Tags");
		btnTags.setBackground(new Color(46, 139, 87));
		btnTags.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tags.main(new String[]{});
				frame.dispose();
			}
		});
		btnTags.setBounds(253, 405, 194, 46);
		leftPane.add(btnTags);
		btnTags.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnSubjects = new JButton("Subjects");
		btnSubjects.setBackground(new Color(46, 139, 87));
		btnSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subjects.main(new String[]{});
				frame.dispose();
			}
		});
		btnSubjects.setBounds(31, 348, 194, 46);
		leftPane.add(btnSubjects);
		btnSubjects.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton btnLocation = new JButton("Location");
		btnLocation.setBackground(new Color(46, 139, 87));
		btnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locations.main(new String[]{});
				frame.dispose();
			}
		});
		btnLocation.setBounds(253, 348, 194, 46);
		leftPane.add(btnLocation);
		btnLocation.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("Time Table Mangment System");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 31));
		lblNewLabel_2.setBounds(27, 80, 437, 88);
		leftPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("2021");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(179, 163, 98, 46);
		leftPane.add(lblNewLabel_3);
		
		JButton btnAddSessions = new JButton("Add Sessions");
		btnAddSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Session.main(new String[]{});
				frame.dispose();
			}
		});
		btnAddSessions.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAddSessions.setBackground(new Color(46, 139, 87));
		btnAddSessions.setBounds(31, 479, 194, 46);
		leftPane.add(btnAddSessions);
		
		JButton btnActivities = new JButton("Activities");
		btnActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageActivities.main(new String[]{});
				frame.dispose();
			}
		});
		btnActivities.setFont(new Font("Arial", Font.PLAIN, 20));
		btnActivities.setBackground(new Color(46, 139, 87));
		btnActivities.setBounds(253, 479, 194, 46);
		leftPane.add(btnActivities);
		
		JButton btnSessionRooms = new JButton("Session Rooms");
		btnSessionRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageSessionRooms.main(new String[]{});
				frame.dispose();
			}
		});
		btnSessionRooms.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSessionRooms.setBackground(new Color(46, 139, 87));
		btnSessionRooms.setBounds(31, 536, 194, 46);
		leftPane.add(btnSessionRooms);
		
		JButton btnTimeAllocation = new JButton("Time Allocations");
		btnTimeAllocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeAllocation.main(new String[]{});
				frame.dispose();
			}
		});
		btnTimeAllocation.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTimeAllocation.setBackground(new Color(46, 139, 87));
		btnTimeAllocation.setBounds(253, 536, 194, 46);
		leftPane.add(btnTimeAllocation);
		
		JButton btnTimeTables = new JButton("Time Tables");
		btnTimeTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeTables.main(new String[]{});
				frame.dispose();
			}
		});
		btnTimeTables.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTimeTables.setBackground(new Color(46, 139, 87));
		btnTimeTables.setBounds(124, 616, 239, 64);
		leftPane.add(btnTimeTables);
		
		JPanel rightPane = new JPanel();
		rightPane.setBackground(UIManager.getColor("window"));
		rightPane.setBounds(504, 11, 931, 748);
		frame.getContentPane().add(rightPane);
		rightPane.setLayout(null);
		
		JPanel panelRegLec = new JPanel();
		panelRegLec.setBackground(new Color(0, 128, 128));
		panelRegLec.setBounds(24, 96, 144, 99);
		rightPane.add(panelRegLec);
		panelRegLec.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblRegLecVal = new JLabel("");
		lblRegLecVal.setFont(new Font("Arial", Font.BOLD, 80));
		panelRegLec.add(lblRegLecVal);
		
		JLabel lblRegLec = new JLabel("Registered Lecturers");
		lblRegLec.setBackground(new Color(0, 0, 128));
		lblRegLec.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegLec.setBounds(24, 233, 155, 17);
		rightPane.add(lblRegLec);
		
		JPanel panelRegStd = new JPanel();
		panelRegStd.setBackground(new Color(0, 128, 128));
		panelRegStd.setBounds(232, 96, 144, 99);
		rightPane.add(panelRegStd);
		
		lblRegStdVal = new JLabel("");
		lblRegStdVal.setFont(new Font("Arial", Font.BOLD, 80));
		panelRegStd.add(lblRegStdVal);
		
		JPanel panelRegSbj = new JPanel();
		panelRegSbj.setBackground(new Color(0, 128, 128));
		panelRegSbj.setBounds(443, 96, 144, 99);
		rightPane.add(panelRegSbj);
		
		lblRegSbjVal = new JLabel("");
		panelRegSbj.add(lblRegSbjVal);
		lblRegSbjVal.setFont(new Font("Arial", Font.BOLD, 80));
		
		JPanel panelRegRooms = new JPanel();
		panelRegRooms.setBackground(new Color(0, 128, 128));
		panelRegRooms.setBounds(663, 96, 144, 99);
		rightPane.add(panelRegRooms);
		
		lblRegRoomVal = new JLabel("");
		lblRegRoomVal.setFont(new Font("Arial", Font.BOLD, 80));
		panelRegRooms.add(lblRegRoomVal);
		
		JLabel lblRegStd = new JLabel("Registered Students");
		lblRegStd.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegStd.setBounds(232, 233, 155, 17);
		rightPane.add(lblRegStd);
		
		JLabel lblRegSbj = new JLabel("Registered Subjects");
		lblRegSbj.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegSbj.setBounds(443, 233, 158, 17);
		rightPane.add(lblRegSbj);
		
		JLabel lblRegRooms = new JLabel("Registered Rooms");
		lblRegRooms.setFont(new Font("Arial", Font.BOLD, 15));
		lblRegRooms.setBounds(663, 233, 144, 17);
		rightPane.add(lblRegRooms);
		
		JPanel panelLocationView = new JPanel();
		panelLocationView.setBackground(new Color(255, 255, 255));
		panelLocationView.setBounds(24, 341, 363, 331);
		rightPane.add(panelLocationView);
		
		JPanel panelLatestView = new JPanel();
		panelLatestView.setBackground(new Color(245, 245, 220));
		panelLatestView.setBounds(429, 341, 378, 331);
		rightPane.add(panelLatestView);
		panelLatestView.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Whats's New?");
		lblNewLabel_1.setBounds(10, 11, 104, 14);
		panelLatestView.add(lblNewLabel_1);
		
		JLabel lbllLec = new JLabel("Latest Lecturer ");
		lbllLec.setFont(new Font("Arial", Font.PLAIN, 17));
		lbllLec.setBounds(10, 100, 123, 20);
		panelLatestView.add(lbllLec);
		
		JLabel lbllGroup = new JLabel("Latest Group ");
		lbllGroup.setFont(new Font("Arial", Font.PLAIN, 17));
		lbllGroup.setBounds(10, 169, 123, 20);
		panelLatestView.add(lbllGroup);
		
		JLabel lbllSbj = new JLabel("Latest Subject");
		lbllSbj.setFont(new Font("Arial", Font.PLAIN, 17));
		lbllSbj.setBounds(10, 223, 123, 20);
		panelLatestView.add(lbllSbj);
		
		lblLecVal = new JLabel("");
		lblLecVal.setFont(new Font("Arial", Font.BOLD, 17));
		lblLecVal.setBounds(143, 90, 222, 30);
		panelLatestView.add(lblLecVal);
		
		lblGrpVal = new JLabel("");
		lblGrpVal.setFont(new Font("Arial", Font.BOLD, 17));
		lblGrpVal.setBounds(143, 159, 222, 30);
		panelLatestView.add(lblGrpVal);
		
		lblSbjVal = new JLabel("");
		lblSbjVal.setFont(new Font("Arial", Font.BOLD, 17));
		lblSbjVal.setBounds(143, 223, 222, 30);
		panelLatestView.add(lblSbjVal);
		
		DbData();
		BufferedImage wPic;
		try {
			wPic = ImageIO.read(new File("images/gg1.png"));
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(wPic).getImage().getScaledInstance(341, 363, Image.SCALE_SMOOTH));
			JLabel wIcon = new JLabel();
			wIcon.setIcon(imageIcon);
			panelLocationView.add(wIcon);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("error");
		}
	}
	public void DbData() {
		try{
			Connection con=DB.getConnection();				
			
			PreparedStatement ps1 =con.prepareStatement("SELECT COUNT(lecturerId)  FROM lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs1 =ps1.executeQuery();
			if(rs1.next()) {
				regLec = rs1.getString(1);
			}
			PreparedStatement ps2 =con.prepareStatement("SELECT COUNT(stdid)  FROM students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 =ps2.executeQuery();
			if(rs2.next()) {
				regStd = rs2.getString(1);
			}
			PreparedStatement ps3 =con.prepareStatement("SELECT COUNT(sid)  FROM subjects",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs3 =ps3.executeQuery();
			if(rs3.next()) {
				regSbj = rs3.getString(1);
			}
			PreparedStatement ps4 =con.prepareStatement("SELECT COUNT(lid)  FROM locations",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs4 =ps4.executeQuery();
			if(rs4.next()) {
				regRoom = rs4.getString(1);
			}
			PreparedStatement ps5 =con.prepareStatement("SELECT lecturerName FROM lecturers ORDER BY lecturerId DESC LIMIT 1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs5 =ps5.executeQuery();
			if(rs5.next()) {
				latLec = rs5.getString(1);
			}
			PreparedStatement ps6 =con.prepareStatement("SELECT grpId FROM students ORDER BY stdid DESC LIMIT 1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs6 =ps6.executeQuery();
			if(rs6.next()) {
				latGrp = rs6.getString(1);
			}
			PreparedStatement ps7 =con.prepareStatement("SELECT subjectName FROM subjects ORDER BY sid DESC LIMIT 1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs7 =ps7.executeQuery();
			if(rs7.next()) {
				latSbj = rs7.getString(1);
			}
			lblLecVal.setText(latLec);
			lblGrpVal.setText(latGrp);
			lblSbjVal.setText(latSbj);
			lblRegLecVal.setText (regLec);
			lblRegSbjVal.setText(regSbj);
			lblRegRoomVal.setText(regRoom);
			lblRegStdVal.setText(regStd);
		}catch(Exception e){System.out.println(e);}
	}

}
