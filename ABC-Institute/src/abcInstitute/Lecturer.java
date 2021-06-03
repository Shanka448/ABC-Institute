package abcInstitute;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Lecturer {

	private JFrame frame;
	private static final long serialVersionUID = 1181619521987085822L;
	private JPanel contentPane;
	private String emplolyeeID = "";
	private String lecturerLevel = "";
	private String lecturerRank = "";
	private String lecturerFaculty = "";
	private String lecturerDepartment = "";
	private String lecturerCenter = "";
	private String lecturerBuilduing = "";
	private String lecturerName = "";
	private int crud;
	private JTable tabelMngLecturer;
	String[] levelStrings = { "1","2","3","4","5","6"};
	
	String[] facultyStrings = { "Computing", "Engineering", "Business", "Humanities & Sciences"};
	
	String[] departmentStrings = { "IT", "Examination", "Service"};
	
	String[] builduingStrings = { "New Builduing","A-Block","B-Block","c-Block","D-Block"};
	
	String[] centerStrings = { "Malabe","Metro","Kandy","Matara","Kurunegala","Jaffna"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lecturer window = new Lecturer();
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
	public Lecturer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new java.awt.Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
DbValTable();
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
		tabbedPane.setBounds(10, 11, 1330, 707);
		contentPane.add(tabbedPane);
		
		JPanel tabAddLecturers = new JPanel();
		tabAddLecturers.setBackground(new java.awt.Color(255, 255, 255));
		tabbedPane.addTab("Add Lecturers", null, tabAddLecturers, null);
		tabAddLecturers.setLayout(null);
		
		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setBounds(471, 311, 56, 19);
		lblBuilding.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblBuilding);
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(471, 366, 37, 19);
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblLevel);
		
		JLabel lblCenter = new JLabel("Center");
		lblCenter.setBounds(471, 263, 47, 19);
		lblCenter.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblCenter);
		
		JLabel lblEmpolyeeId = new JLabel("Empolyee ID");
		lblEmpolyeeId.setBounds(471, 116, 89, 19);
		lblEmpolyeeId.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblEmpolyeeId);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(471, 213, 82, 19);
		lblDepartment.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblDepartment);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setBounds(471, 166, 49, 19);
		lblFaculty.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblFaculty);
		
		JLabel lblLecturerName = new JLabel("Lecturer Name");
		lblLecturerName.setBounds(471, 71, 103, 19);
		lblLecturerName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblLecturerName);
		JComboBox comboBoxLecturersLevel = new JComboBox(levelStrings);
		comboBoxLecturersLevel.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxLecturersLevel.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxLecturersLevel.setSelectedIndex(0);
		comboBoxLecturersLevel.setBounds(709, 363, 174, 28);
		tabAddLecturers.add(comboBoxLecturersLevel);
		
		JLabel lblLecturersRank = new JLabel("0000001");
		lblLecturersRank.setFont(new Font("Arial", Font.BOLD, 13));
		lblLecturersRank.setBounds(701, 425, 76, 28);
		tabAddLecturers.add(lblLecturersRank);
		
		JTextField textFieldLecturersId = new JTextField(null);
		textFieldLecturersId.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldLecturersId.setBounds(707, 114, 174, 25);
		tabAddLecturers.add(textFieldLecturersId);
		textFieldLecturersId.setColumns(10);
		
		JButton btnLecturersGenerateRank = new JButton("Generate");
		btnLecturersGenerateRank.setBackground(new java.awt.Color(70, 130, 180));
		btnLecturersGenerateRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				emplolyeeID = textFieldLecturersId.getText();
				lecturerLevel = (String)comboBoxLecturersLevel.getSelectedItem();
				GenerateRank(emplolyeeID,lecturerLevel);
			}
			public void GenerateRank(String emplolyeeID,String lecturerLevel) {
				lecturerRank = lecturerLevel + "." + emplolyeeID;
				lblLecturersRank.setText(lecturerRank);
				
			}
		});
		btnLecturersGenerateRank.setFont(new Font("Arial", Font.BOLD, 13));
		btnLecturersGenerateRank.setBounds(787, 424, 96, 31);
		tabAddLecturers.add(btnLecturersGenerateRank);
		
		
		
		JTextField textFieldLecturersName = new JTextField(null);
		textFieldLecturersName.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldLecturersName.setBounds(707, 70, 174, 25);
		tabAddLecturers.add(textFieldLecturersName);
		textFieldLecturersName.setColumns(10);
		
		JComboBox comboBoxLecturersFaculty = new JComboBox(facultyStrings);
		comboBoxLecturersFaculty.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxLecturersFaculty.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxLecturersFaculty.setSelectedIndex(0);
		comboBoxLecturersFaculty.setBounds(707, 164, 174, 27);
		tabAddLecturers.add(comboBoxLecturersFaculty);
		
		
		
		JLabel lblRank = new JLabel("Rank");
		lblRank.setBounds(471, 428, 36, 19);
		lblRank.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLecturers.add(lblRank);
		
		JComboBox comboBoxLecturersDepartment = new JComboBox(departmentStrings);
		comboBoxLecturersDepartment.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxLecturersFaculty.setSelectedIndex(0);
		comboBoxLecturersDepartment.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxLecturersDepartment.setBounds(707, 211, 174, 27);
		tabAddLecturers.add(comboBoxLecturersDepartment);
		
		JComboBox comboBoxLecturersBuilduing = new JComboBox(builduingStrings);
		comboBoxLecturersBuilduing.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxLecturersBuilduing.setSelectedIndex(0);
		comboBoxLecturersBuilduing.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxLecturersBuilduing.setBounds(707, 308, 174, 28);
		tabAddLecturers.add(comboBoxLecturersBuilduing);
		
		JComboBox comboBoxLecturersCenter = new JComboBox(centerStrings);
		comboBoxLecturersCenter.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxLecturersCenter.setSelectedIndex(0);
		comboBoxLecturersCenter.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxLecturersCenter.setBounds(707, 262, 174, 28);
		tabAddLecturers.add(comboBoxLecturersCenter);
		
		JButton btnLecturersClear = new JButton("Clear");
		btnLecturersClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldLecturersId.setText(null);
				comboBoxLecturersLevel.setSelectedIndex(0);
				lblLecturersRank.setText(null);
				comboBoxLecturersFaculty.setSelectedIndex(0);
				comboBoxLecturersDepartment.setSelectedIndex(0);
				comboBoxLecturersCenter.setSelectedIndex(0);
				comboBoxLecturersBuilduing.setSelectedIndex(0);
				textFieldLecturersName.setText(null);
			}
		});
		btnLecturersClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLecturersClear.setBackground(new java.awt.Color(255, 99, 71));
		btnLecturersClear.setBounds(471, 577, 158, 47);
		tabAddLecturers.add(btnLecturersClear);
		
		JButton btnLecturersSave = new JButton("Save");
		btnLecturersSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emplolyeeID = textFieldLecturersId.getText();
				lecturerName = textFieldLecturersName.getText();
				lecturerFaculty = (String)comboBoxLecturersFaculty.getSelectedItem();
				lecturerDepartment = (String)comboBoxLecturersDepartment.getSelectedItem();
				lecturerCenter = (String)comboBoxLecturersCenter.getSelectedItem();
				lecturerBuilduing = (String)comboBoxLecturersBuilduing.getSelectedItem();
				lecturerLevel = (String)comboBoxLecturersLevel.getSelectedItem();
				lecturerRank = lblLecturersRank.getText();
				crud =1;
				ValidateInputs(emplolyeeID,lecturerName,lecturerFaculty,lecturerDepartment,lecturerCenter,lecturerBuilduing,lecturerLevel,lecturerRank,crud);
			}

		});
		btnLecturersSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnLecturersSave.setBackground(new java.awt.Color(0, 191, 255));
		btnLecturersSave.setBounds(725, 577, 158, 47);
		tabAddLecturers.add(btnLecturersSave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new java.awt.Color(32, 178, 170));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton.setBounds(1086, 567, 158, 57);
		tabAddLecturers.add(btnNewButton);
		
		JPanel tabManageLecturers = new JPanel();
		tabManageLecturers.setBackground(new java.awt.Color(255, 255, 255));
		tabbedPane.addTab("Manage Lecturers", null, tabManageLecturers, null);
		tabManageLecturers.setLayout(null);
		
		JLabel lblBuilding_1 = new JLabel("Building");
		lblBuilding_1.setBounds(738, 485, 56, 19);
		lblBuilding_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblBuilding_1);
		
		JLabel lblLevel_1 = new JLabel("Level");
		lblLevel_1.setBounds(1030, 420, 37, 19);
		lblLevel_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblLevel_1);
		
		JLabel lblCenter_1 = new JLabel("Center");
		lblCenter_1.setBounds(738, 420, 47, 19);
		lblCenter_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblCenter_1);
		
		JLabel lblEmpolyeeId_1 = new JLabel("Empolyee ID");
		lblEmpolyeeId_1.setBounds(73, 485, 89, 19);
		lblEmpolyeeId_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblEmpolyeeId_1);
		
		JLabel lblDepartment_1 = new JLabel("Department");
		lblDepartment_1.setBounds(414, 485, 82, 19);
		lblDepartment_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblDepartment_1);
		
		JLabel lblFaculty_1 = new JLabel("Faculty");
		lblFaculty_1.setBounds(414, 420, 49, 19);
		lblFaculty_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblFaculty_1);
		
		JLabel lblLecturerName_1 = new JLabel("Lecturer Name");
		lblLecturerName_1.setBounds(73, 420, 103, 19);
		lblLecturerName_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblLecturerName_1);
		
		JLabel lblRank_1 = new JLabel("Rank");
		lblRank_1.setBounds(1030, 485, 36, 19);
		lblRank_1.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLecturers.add(lblRank_1);
		
		JTextField textFieldMngLecturerName = new JTextField();
		textFieldMngLecturerName.setBounds(203, 419, 139, 24);
		textFieldMngLecturerName.setFont(new Font("Arial", Font.BOLD, 14));
		tabManageLecturers.add(textFieldMngLecturerName);
		textFieldMngLecturerName.setColumns(10);
		
		JComboBox comboBoxMngLecturersLevel = new JComboBox(levelStrings);
		comboBoxMngLecturersLevel.setBounds(1102, 417, 160, 28);
		comboBoxMngLecturersLevel.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxMngLecturersLevel.setSelectedIndex(0);
		comboBoxMngLecturersLevel.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(comboBoxMngLecturersLevel);
		
		JComboBox comboBoxMngLecturersFaculty = new JComboBox(facultyStrings);
		comboBoxMngLecturersFaculty.setBounds(530, 418, 149, 27);
		comboBoxMngLecturersFaculty.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxMngLecturersFaculty.setSelectedIndex(0);
		comboBoxMngLecturersFaculty.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(comboBoxMngLecturersFaculty);
		
		JComboBox comboBoxMngLecturersDepartment = new JComboBox(departmentStrings);
		comboBoxMngLecturersDepartment.setBounds(530, 483, 149, 27);
		comboBoxMngLecturersDepartment.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxMngLecturersDepartment.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(comboBoxMngLecturersDepartment);
		
		JComboBox comboBoxMngLecturersBuilduing = new JComboBox(builduingStrings);
		comboBoxMngLecturersBuilduing.setBounds(828, 482, 149, 28);
		comboBoxMngLecturersBuilduing.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxMngLecturersBuilduing.setSelectedIndex(0);
		comboBoxMngLecturersBuilduing.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(comboBoxMngLecturersBuilduing);
		
		JComboBox comboBoxMngLecturersCenter = new JComboBox(centerStrings);
		comboBoxMngLecturersCenter.setBounds(828, 419, 149, 25);
		comboBoxMngLecturersCenter.setBackground(new java.awt.Color(255, 255, 255));
		comboBoxMngLecturersCenter.setSelectedIndex(0);
		comboBoxMngLecturersCenter.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(comboBoxMngLecturersCenter);
		
		JLabel lblMngLecturersRank = new JLabel("");
		lblMngLecturersRank.setBounds(1102, 476, 160, 28);
		lblMngLecturersRank.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(lblMngLecturersRank);
		
		JTextField textFieldMngLecturerId = new JTextField();
		textFieldMngLecturerId.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldMngLecturerId.setBounds(203, 484, 139, 24);
		tabManageLecturers.add(textFieldMngLecturerId);
		textFieldMngLecturerId.setColumns(10);
		
		JButton btnMngLecturersClear = new JButton("Clear");
		btnMngLecturersClear.setBounds(86, 607, 158, 47);
		btnMngLecturersClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMngLecturerId.setText(null);
				comboBoxMngLecturersLevel.setSelectedIndex(0);
				lblMngLecturersRank.setText(null);
				comboBoxMngLecturersFaculty.setSelectedIndex(0);
				comboBoxMngLecturersDepartment.setSelectedIndex(0);
				comboBoxMngLecturersCenter.setSelectedIndex(0);
				comboBoxMngLecturersBuilduing.setSelectedIndex(0);
				textFieldMngLecturerName.setText(null);
			}
		});
		
		JPanel panelMngLecturer = new JPanel();
		panelMngLecturer.setBounds(73, 11, 1189, 372);
		panelMngLecturer.setBackground(new java.awt.Color(255, 255, 255));
		tabManageLecturers.add(panelMngLecturer);
		panelMngLecturer.setLayout(new BorderLayout(0, 0));
		tabelMngLecturer.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	lecturerName = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 0).toString();
	        	emplolyeeID = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 1).toString();
				lecturerFaculty = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 2).toString();;
				lecturerDepartment = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 3).toString();
				lecturerCenter = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 4).toString();
				lecturerBuilduing = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 5).toString();
				lecturerLevel = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 6).toString();
				lecturerRank = tabelMngLecturer.getModel().getValueAt(tabelMngLecturer.getSelectedRow(), 7).toString();				
	        	textFieldMngLecturerName.setText(lecturerName);
	        	textFieldMngLecturerId.setText(emplolyeeID);
	        	comboBoxMngLecturersFaculty.setSelectedItem((Object)lecturerFaculty);
	        	comboBoxMngLecturersDepartment.setSelectedItem((Object)lecturerDepartment);
	        	comboBoxMngLecturersCenter.setSelectedItem((Object)lecturerCenter);
	        	comboBoxMngLecturersBuilduing.setSelectedItem((Object)lecturerBuilduing);
	        	comboBoxMngLecturersLevel.setSelectedItem((Object)lecturerLevel);
	        	lblMngLecturersRank.setText(lecturerRank);
	        	
	        }
	    });
		
		JScrollPane paneScrlMngLecturer = new JScrollPane(tabelMngLecturer);
		panelMngLecturer.add(paneScrlMngLecturer);
		
		btnMngLecturersClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLecturersClear.setBackground(new java.awt.Color(255, 99, 71));
		tabManageLecturers.add(btnMngLecturersClear);
		
		JButton btnMngLecturerBack = new JButton("Back");
		btnMngLecturerBack.setBounds(1104, 597, 158, 57);
		btnMngLecturerBack.setBackground(new java.awt.Color(32, 178, 170));
		btnMngLecturerBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLecturerBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		tabManageLecturers.add(btnMngLecturerBack);
		
		JButton btnMngLecturersDelete = new JButton("Delete");
		btnMngLecturersDelete.setBounds(803, 607, 158, 47);
		btnMngLecturersDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emplolyeeID = textFieldMngLecturerId.getText();
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM lecturers WHERE lecturerId = ?");
					ps.setString(1,emplolyeeID);
					ps.execute();

					showMessageDialog(null, "Item deleted !");
					UpdateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMngLecturersDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLecturersDelete.setBackground(java.awt.Color.RED);
		tabManageLecturers.add(btnMngLecturersDelete);
		
		JButton btnMngLecturersGenerateRank = new JButton("Generate");
		btnMngLecturersGenerateRank.setBackground(new Color(70, 130, 180));
		btnMngLecturersGenerateRank.setBounds(1159, 510, 103, 32);
		btnMngLecturersGenerateRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateRank1();
			}
			public void GenerateRank1() {
				emplolyeeID = textFieldMngLecturerId.getText();
				lecturerLevel = (String)comboBoxMngLecturersLevel.getSelectedItem();
				lecturerRank = lecturerLevel + "." + emplolyeeID;
				lblMngLecturersRank.setText(lecturerRank);
				
			}
		});
		btnMngLecturersGenerateRank.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageLecturers.add(btnMngLecturersGenerateRank);
		
		JButton btnMngLecturersUpdate = new JButton("Update");
		btnMngLecturersUpdate.setBounds(474, 607, 158, 47);
		btnMngLecturersUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emplolyeeID = textFieldMngLecturerId.getText();
				lecturerLevel = (String)comboBoxMngLecturersLevel.getSelectedItem();
				lecturerRank = lblMngLecturersRank.getText().toString();
				lecturerFaculty = (String)comboBoxMngLecturersFaculty.getSelectedItem();
				lecturerDepartment = (String)comboBoxMngLecturersDepartment.getSelectedItem();
				lecturerCenter = (String)comboBoxMngLecturersCenter.getSelectedItem();
				lecturerBuilduing = (String)comboBoxMngLecturersBuilduing.getSelectedItem();
				lecturerName = textFieldMngLecturerName.getText();
				crud = 0;
				ValidateInputs(emplolyeeID,lecturerName,lecturerFaculty,lecturerDepartment,lecturerCenter,lecturerBuilduing,lecturerLevel,lecturerRank,crud);
				
			}
		});
		btnMngLecturersUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLecturersUpdate.setBackground(new java.awt.Color(50, 205, 50));
		tabManageLecturers.add(btnMngLecturersUpdate);
		

		
	}

	public void ValidateInputs(String lId, String lName , String lFaculty, String lDepartment , String lCenter, String lBuilding, String lLevel ,String lRank , int crud) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			emplolyeeID = lId;
			lecturerLevel = lLevel;
			lecturerRank = lecturerLevel + "." + emplolyeeID;
			lecturerFaculty = lFaculty;
			lecturerDepartment = lDepartment;
			lecturerCenter = lCenter;
			lecturerBuilduing = lBuilding;
			lecturerName =lName;
			
			
			if(emplolyeeID.isEmpty() || lecturerLevel.isEmpty() || lecturerRank.isEmpty() || lecturerFaculty.isEmpty() || lecturerDepartment.isEmpty() || lecturerCenter.isEmpty() || lecturerBuilduing.isEmpty() || lecturerName.isEmpty()) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else if(emplolyeeID.length() == 6) {
				if(crud == 0) {
					PreparedStatement  ps = con.prepareStatement("UPDATE lecturers SET lecturerName = ?, faculty = ?, department = ? , center = ? , builduing = ? , level = ? , ranks = ? WHERE lecturerId = ?");
					ps.setString(1,lecturerName);
					ps.setString(2,lecturerFaculty);
					ps.setString(3,lecturerDepartment);
					ps.setString(4,lecturerCenter);
					ps.setString(5,lecturerBuilduing);
					ps.setString(6, lecturerLevel);
					ps.setString(7,lecturerRank);
					ps.setString(8, emplolyeeID);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
				}else if(crud == 1){
					query.execute("insert into lecturers values('"+lecturerName+"','"+emplolyeeID+"','"+lecturerFaculty+"','"+lecturerDepartment+"','"+lecturerCenter+"','"+lecturerBuilduing+"','"+lecturerLevel+"','"+lecturerRank+"')");
					showMessageDialog(null, "Successfully added!");
					UpdateTable();
					
					
				}
			}else {
				showMessageDialog(null, "Empolyee ID length should be 6 Digit only", "Error", ERROR_MESSAGE);
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void DbValTable() {
		String data[][]=null;
		String column[]=null;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=ps.executeQuery();
			
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount();
			column=new String[cols];
			for(int i=1;i<=cols;i++){
				column[i-1]=rsmd.getColumnName(i);
			}
			
			rs.last();
			int rows=rs.getRow();
			rs.beforeFirst();

			data=new String[rows][cols];
			int count=0;
			while(rs.next()){
				for(int i=1;i<=cols;i++){
					data[count][i-1]=rs.getString(i);
					
				}
				count++;
			}
			AbstractTableModel model = new DefaultTableModel(data,column);
			tabelMngLecturer = new JTable(model);
			tabelMngLecturer.setBackground(new java.awt.Color(255, 255, 255));
			tabelMngLecturer.setBounds(161, 11, 1165, 372);
			tabelMngLecturer.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=ps.executeQuery();
		String data[][]=null;
		String column[]=null;
		ResultSetMetaData rsmd=rs.getMetaData();
		int cols=rsmd.getColumnCount();
		column=new String[cols];
		for(int i=1;i<=cols;i++){
			column[i-1]=rsmd.getColumnName(i);
		}
		
		rs.last();
		int rows=rs.getRow();
		rs.beforeFirst();

		data=new String[rows][cols];
		int count=0;
		while(rs.next()){
			for(int i=1;i<=cols;i++){
				data[count][i-1]=rs.getString(i);
				
			}
			count++;
		}
		AbstractTableModel model = new DefaultTableModel(data,column);
		tabelMngLecturer.setModel(model);
		tabelMngLecturer.repaint();
	}

}
