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
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Subjects {

	private JFrame frame;
	private static final long serialVersionUID = -9159108511851479260L;
	private JPanel contentPane;
	String[] YearStrings = { "1st Year", "2nd Year", "3rd Year", "4th Year"};
	String[] columns = {"ID","Subject Name","Subject Code","Offerd Year","Offerd Semester","Lecture Hours","Tute Hours","Lab HOurs","Evaluation Hours"};
	private JTextField textFieldSubjectName;
	private JTextField textFieldSubjectCode;
	private JTextField textFieldMngSubjectName;
	private JTextField textFieldMngSubjectCode;
	private String subjectName = "";
	private String subjectCode= "";
	private String subjectYear = "";
	private String subjectSem = "";
	private String subjectLec =  "";
	private String subjectTut =  "";
	private String subjectLab =  "";
	private String subjectEva =  "";
	private int crud;
	private int sid;
	private JTable tabelMngSubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subjects window = new Subjects();
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
	public Subjects() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0,1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 11, 1303, 707);
		contentPane.add(tabbedPane);
		
		JPanel tabAddSubject = new JPanel();
		tabAddSubject.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Add Subject", null, tabAddSubject, null);
		tabAddSubject.setLayout(null);
		
		JLabel lblOfferdYear = new JLabel("Offerd Year");
		lblOfferdYear.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOfferdYear.setBounds(312, 47, 103, 19);
		tabAddSubject.add(lblOfferdYear);
		
		JLabel lblOfferdSemester = new JLabel("Offerd Semester");
		lblOfferdSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		lblOfferdSemester.setBounds(312, 106, 135, 21);
		tabAddSubject.add(lblOfferdSemester);
		
		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSubjectName.setBounds(312, 178, 114, 21);
		tabAddSubject.add(lblSubjectName);
		
		JLabel lblSubjectCode = new JLabel("Subject Code");
		lblSubjectCode.setFont(new Font("Arial", Font.PLAIN, 16));
		lblSubjectCode.setBounds(312, 246, 103, 21);
		tabAddSubject.add(lblSubjectCode);
		
		JLabel lbNumberofLectureHours = new JLabel("Number of Lecture Hours");
		lbNumberofLectureHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNumberofLectureHours.setBounds(312, 310, 179, 21);
		tabAddSubject.add(lbNumberofLectureHours);
		
		JLabel lblNumberOfTutorialHours = new JLabel("Number of Tutorial Hours");
		lblNumberOfTutorialHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNumberOfTutorialHours.setBounds(312, 378, 179, 21);
		tabAddSubject.add(lblNumberOfTutorialHours);
		
		JLabel lblNumberOfLabHours = new JLabel("Number of Lab Hours");
		lblNumberOfLabHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNumberOfLabHours.setBounds(312, 457, 158, 21);
		tabAddSubject.add(lblNumberOfLabHours);
		
		JLabel lblNumberOfEvaluationHours = new JLabel("Number of Evaluation Hours");
		lblNumberOfEvaluationHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNumberOfEvaluationHours.setBounds(312, 516, 201, 28);
		tabAddSubject.add(lblNumberOfEvaluationHours);
		
		JComboBox comboBoxOfferdYear = new JComboBox(YearStrings);
		comboBoxOfferdYear.setBackground(new Color(255, 255, 255));
		comboBoxOfferdYear.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxOfferdYear.setBounds(587, 46, 174, 25);
		tabAddSubject.add(comboBoxOfferdYear);
		
		JRadioButton rdbtnSem1 = new JRadioButton("   1st  Semester");
		rdbtnSem1.setBackground(new Color(255, 255, 255));
		rdbtnSem1.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnSem1.setBounds(587, 97, 174, 23);
		tabAddSubject.add(rdbtnSem1);
		
		JRadioButton rdbtnSem2 = new JRadioButton("   2nd Semester");
		rdbtnSem2.setBackground(new Color(255, 255, 255));
		rdbtnSem2.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnSem2.setBounds(586, 123, 199, 23);
		tabAddSubject.add(rdbtnSem2);
		
		ButtonGroup rdgrpSems =new ButtonGroup();    
		rdgrpSems.add(rdbtnSem1);
		rdgrpSems.add(rdbtnSem2); 
		
		textFieldSubjectName = new JTextField();
		textFieldSubjectName.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldSubjectName.setColumns(10);
		textFieldSubjectName.setBounds(587, 178, 174, 25);
		tabAddSubject.add(textFieldSubjectName);
		
		textFieldSubjectCode = new JTextField();
		textFieldSubjectCode.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldSubjectCode.setColumns(10);
		textFieldSubjectCode.setBounds(587, 246, 174, 25);
		tabAddSubject.add(textFieldSubjectCode);
		
		JSpinner spinnerNoOfLecHrs = new JSpinner();
		spinnerNoOfLecHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerNoOfLecHrs.setBounds(587, 310, 174, 25);
		tabAddSubject.add(spinnerNoOfLecHrs);
		
		JSpinner spinnerNoOfTutHrs = new JSpinner();
		spinnerNoOfTutHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerNoOfTutHrs.setBounds(587, 377, 174, 26);
		tabAddSubject.add(spinnerNoOfTutHrs);
		
		JSpinner spinnerNoOfLabHrs = new JSpinner();
		spinnerNoOfLabHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerNoOfLabHrs.setBounds(587, 450, 174, 28);
		tabAddSubject.add(spinnerNoOfLabHrs);
		
		JSpinner spinnerNoOfEvaHrs = new JSpinner();
		spinnerNoOfEvaHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerNoOfEvaHrs.setBounds(587, 516, 174, 28);
		tabAddSubject.add(spinnerNoOfEvaHrs);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(32, 178, 170));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnBack.setBounds(1006, 584, 167, 77);
		tabAddSubject.add(btnBack);
		
		JButton btnSubjectClear = new JButton("Clear");
		btnSubjectClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldSubjectName.setText(null);
				textFieldSubjectCode.setText(null);
				comboBoxOfferdYear.setSelectedIndex(0);
				spinnerNoOfLecHrs.setValue(0);
				spinnerNoOfTutHrs.setValue(0);
				spinnerNoOfLabHrs.setValue(0);
				spinnerNoOfEvaHrs.setValue(0);
				rdgrpSems.clearSelection();
			}
		});
		btnSubjectClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSubjectClear.setBackground(new Color(255, 99, 71));
		btnSubjectClear.setBounds(312, 614, 158, 47);
		tabAddSubject.add(btnSubjectClear);
		
		JButton btnSubjectSave = new JButton("Save");
		btnSubjectSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subjectName = textFieldSubjectName.getText();
				subjectCode= textFieldSubjectCode.getText();
				subjectYear = (String) comboBoxOfferdYear.getSelectedItem();
					try {
						spinnerNoOfLecHrs.commitEdit();
						spinnerNoOfTutHrs.commitEdit();
						spinnerNoOfLabHrs.commitEdit();
						spinnerNoOfEvaHrs.commitEdit();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(rdbtnSem1.isSelected()) {
					subjectSem = "1st Semester";
				}else if(rdbtnSem2.isSelected()) {
					subjectSem = "2nd Semester";
				}
				subjectLec =  spinnerNoOfLecHrs.getValue().toString() ;
				subjectTut =  spinnerNoOfTutHrs.getValue().toString();
				subjectLab =  spinnerNoOfLabHrs.getValue().toString();
				subjectEva =  spinnerNoOfEvaHrs.getValue().toString();
				crud = 1;
				sid = 0;
				
				ValidateInputs(subjectYear,subjectSem,subjectName,subjectCode,subjectLec,subjectTut,subjectLab,subjectEva,crud,sid);
			}
		});
		btnSubjectSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSubjectSave.setBackground(new Color(0, 191, 255));
		btnSubjectSave.setBounds(603, 614, 158, 47);
		tabAddSubject.add(btnSubjectSave);
		
		DbValTable();
		
		JPanel tabManageSubject = new JPanel();
		tabManageSubject.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Manage Subject", null, tabManageSubject, null);
		tabManageSubject.setLayout(null);
		
		JLabel lblMngOfferdYear = new JLabel("Offerd Year");
		lblMngOfferdYear.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngOfferdYear.setBounds(43, 466, 103, 19);
		tabManageSubject.add(lblMngOfferdYear);
		
		JLabel lblMngOfferdSemester = new JLabel("Offerd Semester");
		lblMngOfferdSemester.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngOfferdSemester.setBounds(43, 534, 135, 21);
		tabManageSubject.add(lblMngOfferdSemester);
		
		JLabel lblMngSubjectName = new JLabel("Subject Name");
		lblMngSubjectName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngSubjectName.setBounds(354, 465, 114, 21);
		tabManageSubject.add(lblMngSubjectName);
		
		JLabel lblMngSubjectCode = new JLabel("Subject Code");
		lblMngSubjectCode.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngSubjectCode.setBounds(354, 534, 103, 21);
		tabManageSubject.add(lblMngSubjectCode);
		
		JLabel lblMngNumberofLectureHours = new JLabel("Number of Lecture Hours");
		lblMngNumberofLectureHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngNumberofLectureHours.setBounds(619, 465, 179, 21);
		tabManageSubject.add(lblMngNumberofLectureHours);
		
		JLabel lblMngNumberOfTutorialHours = new JLabel("Number of Tutorial Hours");
		lblMngNumberOfTutorialHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngNumberOfTutorialHours.setBounds(619, 534, 179, 21);
		tabManageSubject.add(lblMngNumberOfTutorialHours);
		
		JLabel lblMngNumberOfLabHours = new JLabel("Number of Lab Hours");
		lblMngNumberOfLabHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngNumberOfLabHours.setBounds(969, 465, 158, 21);
		tabManageSubject.add(lblMngNumberOfLabHours);
		
		JLabel lblMngNumberOfEvaluationHours = new JLabel("Number of Evaluation Hours");
		lblMngNumberOfEvaluationHours.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngNumberOfEvaluationHours.setBounds(969, 530, 201, 28);
		tabManageSubject.add(lblMngNumberOfEvaluationHours);
		
		JComboBox comboMngBoxOfferdYear = new JComboBox(YearStrings);
		comboMngBoxOfferdYear.setBackground(new Color(255, 255, 255));
		comboMngBoxOfferdYear.setFont(new Font("Arial", Font.BOLD, 13));
		comboMngBoxOfferdYear.setBounds(173, 465, 143, 25);
		tabManageSubject.add(comboMngBoxOfferdYear);
		
		JRadioButton rdbtnMngSem1 = new JRadioButton("   1st  Semester");
		rdbtnMngSem1.setBackground(new Color(255, 255, 255));
		rdbtnMngSem1.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnMngSem1.setBounds(174, 523, 174, 23);
		tabManageSubject.add(rdbtnMngSem1);
		
		JRadioButton rdbtnMngSem2 = new JRadioButton("   2nd Semester");
		rdbtnMngSem2.setBackground(new Color(255, 255, 255));
		rdbtnMngSem2.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnMngSem2.setBounds(173, 549, 199, 23);
		tabManageSubject.add(rdbtnMngSem2);
		
		ButtonGroup rdgrpMngSems =new ButtonGroup();    
		rdgrpMngSems.add(rdbtnMngSem1);
		rdgrpMngSems.add(rdbtnMngSem2); 
		
		textFieldMngSubjectName = new JTextField();
		textFieldMngSubjectName.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldMngSubjectName.setColumns(10);
		textFieldMngSubjectName.setBounds(478, 465, 103, 25);
		tabManageSubject.add(textFieldMngSubjectName);
		
		textFieldMngSubjectCode = new JTextField();
		textFieldMngSubjectCode.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldMngSubjectCode.setColumns(10);
		textFieldMngSubjectCode.setBounds(467, 534, 103, 25);
		tabManageSubject.add(textFieldMngSubjectCode);
		
		JSpinner spinnerMngNoOfLecHrs = new JSpinner();
		spinnerMngNoOfLecHrs.setBackground(new Color(255, 255, 255));
		spinnerMngNoOfLecHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerMngNoOfLecHrs.setBounds(824, 465, 103, 25);
		tabManageSubject.add(spinnerMngNoOfLecHrs);
		
		JSpinner spinnerMngNoOfTutHrs = new JSpinner();
		spinnerMngNoOfTutHrs.setBackground(new Color(255, 255, 255));
		spinnerMngNoOfTutHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerMngNoOfTutHrs.setBounds(824, 533, 103, 26);
		tabManageSubject.add(spinnerMngNoOfTutHrs);
		
		JSpinner spinnerMngNoOfLabHrs = new JSpinner();
		spinnerMngNoOfLabHrs.setBackground(new Color(255, 255, 255));
		spinnerMngNoOfLabHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerMngNoOfLabHrs.setBounds(1137, 463, 103, 28);
		tabManageSubject.add(spinnerMngNoOfLabHrs);
		
		JSpinner spinnerMngNoOfEvaHrs = new JSpinner();
		spinnerMngNoOfEvaHrs.setBackground(new Color(255, 255, 255));
		spinnerMngNoOfEvaHrs.setFont(new Font("Arial", Font.BOLD, 13));
		spinnerMngNoOfEvaHrs.setBounds(1180, 532, 60, 28);
		tabManageSubject.add(spinnerMngNoOfEvaHrs);
		
		JButton btnMngBack = new JButton("Back");
		btnMngBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngBack.setBackground(new Color(32, 178, 170));
		btnMngBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnMngBack.setBounds(1073, 603, 167, 62);
		tabManageSubject.add(btnMngBack);
		
		tabelMngSubject.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
							
	        	textFieldMngSubjectName.setText(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 1).toString());
	        	textFieldMngSubjectCode.setText(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 2).toString());
	        	comboMngBoxOfferdYear.setSelectedItem((Object)tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 3).toString());
	        	subjectSem = tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 4).toString();
	        	if(subjectSem .equals("1st Semester")) {
	        		rdbtnMngSem1.setSelected(true);
	        	}else if(subjectSem.equals( "2nd Semester")) {
	        		rdbtnMngSem2.setSelected(true);
	        	}
	        	spinnerMngNoOfLecHrs.setValue(Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 5).toString()));
	        	spinnerMngNoOfTutHrs.setValue(Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 6).toString()));
	        	spinnerMngNoOfLabHrs.setValue(Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 7).toString()));
	        	spinnerMngNoOfEvaHrs.setValue(Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 8).toString()));
	        	
	        }
	    });
		
		JButton btnMngSubjectClear = new JButton("Clear");
		btnMngSubjectClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldMngSubjectName.setText(null);
				textFieldMngSubjectCode.setText(null);
				comboMngBoxOfferdYear.setSelectedIndex(0);
				spinnerMngNoOfLecHrs.setValue(0);
				spinnerMngNoOfTutHrs.setValue(0);
				spinnerMngNoOfLabHrs.setValue(0);
				spinnerMngNoOfEvaHrs.setValue(0);
				rdgrpMngSems.clearSelection();
				
			}
		});
		btnMngSubjectClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngSubjectClear.setBackground(new Color(255, 140, 0));
		btnMngSubjectClear.setBounds(173, 618, 158, 47);
		tabManageSubject.add(btnMngSubjectClear);
		
		JButton btnMngSubjectUpdate = new JButton("Update");
		btnMngSubjectUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subjectName = textFieldMngSubjectName.getText();
				subjectCode= textFieldMngSubjectCode.getText();
				subjectYear = (String) comboMngBoxOfferdYear.getSelectedItem();
					try {
						spinnerMngNoOfLecHrs.commitEdit();
						spinnerMngNoOfTutHrs.commitEdit();
						spinnerMngNoOfLabHrs.commitEdit();
						spinnerMngNoOfEvaHrs.commitEdit();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(rdbtnMngSem1.isSelected()) {
					subjectSem = "1st Semester";
				}else if(rdbtnMngSem2.isSelected()) {
					subjectSem = "2nd Semester";
				}
				subjectLec =  spinnerMngNoOfLecHrs.getValue().toString() ;
				subjectTut =  spinnerMngNoOfTutHrs.getValue().toString();
				subjectLab =  spinnerMngNoOfLabHrs.getValue().toString();
				subjectEva =  spinnerMngNoOfEvaHrs.getValue().toString();
				crud = 0;
				sid = Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 0).toString());
				System.out.println(sid);
				ValidateInputs(subjectYear,subjectSem,subjectName,subjectCode,subjectLec,subjectTut,subjectLab,subjectEva,crud,sid);
			}
		});
		btnMngSubjectUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngSubjectUpdate.setBackground(new Color(50, 205, 50));
		btnMngSubjectUpdate.setBounds(451, 618, 158, 47);
		tabManageSubject.add(btnMngSubjectUpdate);
		
		JPanel panelMngSubject = new JPanel();
		panelMngSubject.setBounds(67, 11, 1165, 372);
		tabManageSubject.add(panelMngSubject);
		panelMngSubject.setLayout(new BorderLayout(0, 0));
		
		JScrollPane paneScrlMngSubject = new JScrollPane(tabelMngSubject);
		panelMngSubject.add(paneScrlMngSubject, BorderLayout.NORTH);
		
		JButton btnMngLecturersDelete = new JButton("Delete");
		btnMngLecturersDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sid = Integer.parseInt(tabelMngSubject.getModel().getValueAt(tabelMngSubject.getSelectedRow(), 0).toString());
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM subjects WHERE sid = ?");
					ps.setInt(1,sid);
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
		btnMngLecturersDelete.setBackground(new Color(255, 69, 0));
		btnMngLecturersDelete.setBounds(736, 618, 158, 47);
		tabManageSubject.add(btnMngLecturersDelete);
		
		
	}
	public void ValidateInputs(String subjectYear,String subjectSem,String subjectName,String subjectCode,String subjectLec,String subjectTut,String subjectLab,String subjectEva,int crud,int sid) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.sid = sid;
			this.subjectName = subjectName;
			this.subjectCode= subjectCode;
			this.subjectYear = subjectYear;
			this.subjectSem = subjectSem;
			this.subjectLec = subjectLec ;
			this.subjectTut = subjectTut;
			this.subjectLab = subjectLab;
			this.subjectEva = subjectEva;
			
			
			if(subjectName.isEmpty() || subjectCode.isEmpty() || subjectYear.isEmpty() || subjectSem.isEmpty() || subjectTut.isEmpty()|| subjectLec.isEmpty() || subjectTut.isEmpty() || subjectEva.isEmpty()) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else {
				if(crud == 0) {
					PreparedStatement  ps = con.prepareStatement("UPDATE subjects SET subjectName = ?, subjectCode = ?, offerdYear = ? , offerdSem = ? , noOfLecHrs = ? , noOfTutHrs = ? , noOfLabHrs = ? , noOfEvaHrs = ? WHERE sid = ?");
					ps.setString(1,subjectName);
					ps.setString(2,subjectCode);
					ps.setString(3,subjectYear);
					ps.setString(4,subjectSem);
					ps.setString(5,subjectLec);
					ps.setString(6, subjectTut);
					ps.setString(7,subjectLab);
					ps.setString(8, subjectEva);
					ps.setInt(9, sid);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
			}else if(crud == 1){
					query.execute("insert into subjects (subjectName, subjectCode, offerdYear, offerdSem, noOfLecHrs, noOfTutHrs, noOfLabHrs, noOfEvaHrs) values('"+subjectName+"','"+subjectCode+"','"+subjectYear+"','"+subjectSem+"','"+subjectLec+"','"+subjectTut+"','"+subjectLab+"','"+subjectEva+"')");
					showMessageDialog(null, "Successfully added!");
					UpdateTable();
					
					
				}
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
			PreparedStatement ps=con.prepareStatement("select * from subjects",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			AbstractTableModel model = new DefaultTableModel(data,columns);
			tabelMngSubject = new JTable(model);
			tabelMngSubject.setBounds(161, 11, 1165, 372);
			tabelMngSubject.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from Subjects",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		AbstractTableModel model = new DefaultTableModel(data,columns);
		tabelMngSubject.setModel(model);
		tabelMngSubject.repaint();
	}

}
