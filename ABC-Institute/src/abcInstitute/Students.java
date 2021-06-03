package abcInstitute;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Students {

	private JFrame frame;
	private JPanel contentPane;
	private JTable tabelMngStudents;
	private String studentYearSem = "";
	private String studentProgramme = "";
	private String studentGroupeNumber =  "";
	private String studentSubGroupeNumber =  "";
	private String studentGroupeId =  "";
	private String studentSubGroupeId =  "";
	private int crud,stdid;
	String[] academicYearSemStrings = { "Y1.S1","Y1.S2","Y2.S1","Y2.S2","Y3.S1","Y3.S2","Y4.S1","Y4.S2"};
	String[] programmeStrings = {"IT","CSSE","CSE","IM"};
	String[] columns = {"ID","Year & Semester","Programme","Group","Sub Group","Group ID","Sub Group ID"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Students window = new Students();
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
	public Students() {
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
		
		JPanel tabAddStudents = new JPanel();
		tabAddStudents.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Add Students", null, tabAddStudents, null);
		tabAddStudents.setLayout(null);
		
		JLabel lblacademicYearSem = new JLabel("Academic Year & Semester");
		lblacademicYearSem.setBounds(457, 71, 208, 19);
		lblacademicYearSem.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblacademicYearSem);
		
		JLabel lblProgramme = new JLabel("Programme");
		lblProgramme.setBounds(457, 126, 174, 19);
		lblProgramme.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblProgramme);
		
		JLabel lblGroupNumber = new JLabel("Group Number");
		lblGroupNumber.setBounds(457, 199, 134, 19);
		lblGroupNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblGroupNumber);
		
		JLabel lblSubGroupNumber = new JLabel("Sub Group Number");
		lblSubGroupNumber.setBounds(457, 259, 151, 24);
		lblSubGroupNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblSubGroupNumber);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setBounds(457, 328, 134, 19);
		lblGroupId.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblGroupId);
		
		JLabel lblSubGroupId = new JLabel("Sub Group ID");
		lblSubGroupId.setBounds(457, 395, 134, 19);
		lblSubGroupId.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblSubGroupId);
		
		
		JComboBox comboBoxStudentsacademicYearSem = new JComboBox(academicYearSemStrings);
		comboBoxStudentsacademicYearSem.setBackground(new Color(255, 255, 255));
		comboBoxStudentsacademicYearSem.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxStudentsacademicYearSem.setSelectedIndex(0);
		comboBoxStudentsacademicYearSem.setBounds(709, 70, 174, 25);
		tabAddStudents.add(comboBoxStudentsacademicYearSem);
		
		JComboBox comboBoxStudentsProgramme = new JComboBox(programmeStrings);
		comboBoxStudentsProgramme.setBackground(new Color(255, 255, 255));
		comboBoxStudentsProgramme.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxStudentsProgramme.setSelectedIndex(0);
		comboBoxStudentsProgramme.setBounds(709, 124, 174, 25);
		tabAddStudents.add(comboBoxStudentsProgramme);
		
		JSpinner spinnerStudentGrpNo = new JSpinner();
		spinnerStudentGrpNo.setBackground(new Color(255, 255, 255));
		spinnerStudentGrpNo.setBounds(709, 198, 174, 25);
		tabAddStudents.add(spinnerStudentGrpNo);
		
		JSpinner spinnerStudentSubGrpNo = new JSpinner();
		spinnerStudentSubGrpNo.setBackground(new Color(255, 255, 255));
		spinnerStudentSubGrpNo.setBounds(709, 261, 174, 25);
		tabAddStudents.add(spinnerStudentSubGrpNo);
		
		JLabel lblStudentGrpIdVal = new JLabel("");
		lblStudentGrpIdVal.setBackground(new Color(255, 255, 255));
		lblStudentGrpIdVal.setBounds(709, 322, 174, 25);
		lblStudentGrpIdVal.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblStudentGrpIdVal);
		
		JLabel lblStudentSubGrpIdVal = new JLabel("");
		lblStudentSubGrpIdVal.setBackground(new Color(255, 255, 255));
		lblStudentSubGrpIdVal.setBounds(709, 389, 174, 25);
		lblStudentSubGrpIdVal.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddStudents.add(lblStudentSubGrpIdVal);
		
		
		
		JButton btnStudentsGenerateIds = new JButton("Generate");
		btnStudentsGenerateIds.setBackground(new Color(70, 130, 180));
		btnStudentsGenerateIds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					spinnerStudentGrpNo.commitEdit();
					spinnerStudentSubGrpNo.commitEdit();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				studentYearSem = (String) comboBoxStudentsacademicYearSem.getSelectedItem();
				studentProgramme =  (String) comboBoxStudentsProgramme.getSelectedItem();
				studentGroupeNumber =  "0" + spinnerStudentGrpNo.getValue().toString();
				studentSubGroupeNumber =  spinnerStudentSubGrpNo.getValue().toString();
				studentGroupeId =  studentYearSem+"."+studentProgramme+"."+studentGroupeNumber;
				studentSubGroupeId =  studentGroupeId+"."+studentSubGroupeNumber;
				GenerateId(studentGroupeId,studentSubGroupeId);
			}
			public void GenerateId(String studentGroupeId,String studentSubGroupeId) {
				lblStudentGrpIdVal.setText(studentGroupeId);
				lblStudentSubGrpIdVal.setText(studentSubGroupeId);
				
			}
		});
		btnStudentsGenerateIds.setFont(new Font("Arial", Font.BOLD, 13));
		btnStudentsGenerateIds.setBounds(787, 477, 96, 31);
		tabAddStudents.add(btnStudentsGenerateIds);
		
		JButton btnStudentsClear = new JButton("Clear");
		btnStudentsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxStudentsacademicYearSem.setSelectedIndex(0);
				comboBoxStudentsProgramme.setSelectedIndex(0);
				spinnerStudentGrpNo.setValue(0);
				spinnerStudentSubGrpNo.setValue(0);
				lblStudentGrpIdVal.setText(null);
				lblStudentSubGrpIdVal.setText(null);
			}
		});
		btnStudentsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStudentsClear.setBackground(new Color(255, 140, 0));
		btnStudentsClear.setBounds(457, 603, 158, 47);
		tabAddStudents.add(btnStudentsClear);
		
		JButton btnStudentsSave = new JButton("Save");
		btnStudentsSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentYearSem = (String) comboBoxStudentsacademicYearSem.getSelectedItem();
				studentProgramme =  (String) comboBoxStudentsProgramme.getSelectedItem();
				studentGroupeNumber =  "0" + spinnerStudentGrpNo.getValue().toString();
				studentSubGroupeNumber =  spinnerStudentSubGrpNo.getValue().toString();
				studentGroupeId =  lblStudentGrpIdVal.getText();
				studentSubGroupeId =  lblStudentSubGrpIdVal.getText();
				crud = 1;
				stdid = 0;
				ValidateInputs(studentYearSem,studentProgramme,studentGroupeNumber,studentSubGroupeNumber,studentGroupeId,studentSubGroupeId,crud,stdid);
			}

		});
		btnStudentsSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnStudentsSave.setBackground(new Color(0, 191, 255));
		btnStudentsSave.setBounds(722, 603, 158, 47);
		tabAddStudents.add(btnStudentsSave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton.setBounds(1065, 573, 167, 77);
		tabAddStudents.add(btnNewButton);
		
		
		
		JPanel tabManageStudents = new JPanel();
		tabManageStudents.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Manage Students", null, tabManageStudents, null);
		tabManageStudents.setLayout(null);
		
		
		
		JLabel lblMngacademicYearSem = new JLabel("Academic Year & Semester");
		lblMngacademicYearSem.setBounds(109, 420, 204, 19);
		lblMngacademicYearSem.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngacademicYearSem.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngacademicYearSem);
		
		JLabel lblMngProgramme = new JLabel("Programme");
		lblMngProgramme.setBounds(110, 490, 89, 19);
		lblMngProgramme.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngProgramme);
		
		JLabel lblMngGroupNumber = new JLabel("Group Number");
		lblMngGroupNumber.setBounds(565, 419, 134, 19);
		lblMngGroupNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngGroupNumber);
		
		JLabel lblMngSubGroupNumber = new JLabel("Sub Group Number");
		lblMngSubGroupNumber.setBounds(568, 487, 151, 24);
		lblMngSubGroupNumber.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngSubGroupNumber);
		
		JLabel lblMngGroupId = new JLabel("Group ID");
		lblMngGroupId.setBounds(1002, 420, 134, 19);
		lblMngGroupId.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngGroupId);
		
		JLabel lblMngSubGroupId = new JLabel("Sub Group ID");
		lblMngSubGroupId.setBounds(1002, 490, 134, 19);
		lblMngSubGroupId.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngSubGroupId);
		
		JComboBox comboBoxMngStudentsacademicYearSem = new JComboBox(academicYearSemStrings);
		comboBoxMngStudentsacademicYearSem.setBounds(323, 419, 174, 25);
		comboBoxMngStudentsacademicYearSem.setBackground(new Color(255, 255, 255));
		comboBoxMngStudentsacademicYearSem.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxMngStudentsacademicYearSem.setSelectedIndex(0);
		tabManageStudents.add(comboBoxMngStudentsacademicYearSem);
		
		JComboBox comboBoxMngStudentsProgramme = new JComboBox(programmeStrings);
		comboBoxMngStudentsProgramme.setBounds(323, 489, 174, 25);
		comboBoxMngStudentsProgramme.setBackground(new Color(255, 255, 255));
		comboBoxMngStudentsProgramme.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxMngStudentsProgramme.setSelectedIndex(0);
		tabManageStudents.add(comboBoxMngStudentsProgramme);
		
		JSpinner spinnerMngStudentGrpNo = new JSpinner();
		spinnerMngStudentGrpNo.setBounds(729, 409, 174, 25);
		spinnerMngStudentGrpNo.setBackground(new Color(255, 255, 255));
		spinnerMngStudentGrpNo.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageStudents.add(spinnerMngStudentGrpNo);
		
		JSpinner spinnerMngStudentSubGrpNo = new JSpinner();
		spinnerMngStudentSubGrpNo.setBounds(729, 484, 174, 25);
		spinnerMngStudentSubGrpNo.setBackground(new Color(255, 255, 255));
		spinnerMngStudentSubGrpNo.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageStudents.add(spinnerMngStudentSubGrpNo);
		
		JLabel lblMngStudentGrpIdVal = new JLabel("");
		lblMngStudentGrpIdVal.setBounds(1123, 414, 151, 25);
		lblMngStudentGrpIdVal.setBackground(new Color(255, 255, 255));
		lblMngStudentGrpIdVal.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngStudentGrpIdVal);
		
		JLabel lblMngStudentSubGrpIdVal = new JLabel("");
		lblMngStudentSubGrpIdVal.setBounds(1123, 490, 151, 25);
		lblMngStudentSubGrpIdVal.setBackground(new Color(255, 255, 255));
		lblMngStudentSubGrpIdVal.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageStudents.add(lblMngStudentSubGrpIdVal);
		
		
		JPanel panelMngStudents = new JPanel();
		panelMngStudents.setBounds(62, 11, 1165, 372);
		panelMngStudents.setBackground(new Color(255, 255, 255));
		tabManageStudents.add(panelMngStudents);
		panelMngStudents.setLayout(new BorderLayout(0, 0));
		
		DbValTable();
		tabelMngStudents.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
	        	comboBoxMngStudentsacademicYearSem.setSelectedItem((Object)tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 1).toString());
	        	comboBoxMngStudentsProgramme.setSelectedItem((Object)tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 2).toString());
	        	spinnerMngStudentGrpNo.setValue(Integer.parseInt(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 3).toString()));
	        	spinnerMngStudentSubGrpNo.setValue(Integer.parseInt(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 4).toString()));
	        	lblMngStudentGrpIdVal.setText(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 5).toString());
				lblMngStudentSubGrpIdVal.setText(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 6).toString());
	        	
	        }
	    });
		
		JScrollPane paneScrlMngStudents = new JScrollPane(tabelMngStudents);
		panelMngStudents.add(paneScrlMngStudents);
		
		JButton btnMngStudentsClear = new JButton("Clear");
		btnMngStudentsClear.setBounds(128, 613, 158, 47);
		btnMngStudentsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxMngStudentsacademicYearSem.setSelectedIndex(0);
				comboBoxMngStudentsProgramme.setSelectedIndex(0);
				spinnerMngStudentGrpNo.setValue(0);
				spinnerMngStudentSubGrpNo.setValue(0);
				lblMngStudentGrpIdVal.setText(null);
				lblMngStudentSubGrpIdVal.setText(null);
			}
		});
		btnMngStudentsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngStudentsClear.setBackground(new Color(255, 140, 0));
		tabManageStudents.add(btnMngStudentsClear);
		
		JButton btnMngStudentsBack = new JButton("Back");
		btnMngStudentsBack.setBounds(1071, 603, 167, 57);
		btnMngStudentsBack.setBackground(new Color(32, 178, 170));
		btnMngStudentsBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMngStudentsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		tabManageStudents.add(btnMngStudentsBack);
		
		JButton btnMngStudentsDelete = new JButton("Delete");
		btnMngStudentsDelete.setBounds(729, 613, 158, 47);
		btnMngStudentsDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stdid = Integer.parseInt(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 0).toString());
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM students WHERE stdid = ?");
					ps.setInt(1,stdid);
					ps.execute();

					showMessageDialog(null, "Item deleted !");
					UpdateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMngStudentsDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngStudentsDelete.setBackground(new Color(255, 69, 0));
		tabManageStudents.add(btnMngStudentsDelete);
		
		JButton btnMngStudentsGenerateIds = new JButton("Generate");
		btnMngStudentsGenerateIds.setBounds(1133, 520, 105, 32);
		btnMngStudentsGenerateIds.setBackground(new Color(70, 130, 180));
		btnMngStudentsGenerateIds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					spinnerStudentGrpNo.commitEdit();
					spinnerStudentSubGrpNo.commitEdit();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				studentYearSem = (String) comboBoxMngStudentsacademicYearSem.getSelectedItem();
				studentProgramme =  (String) comboBoxMngStudentsProgramme.getSelectedItem();
				studentGroupeNumber =  "0" + spinnerMngStudentGrpNo.getValue().toString();
				studentSubGroupeNumber =  spinnerMngStudentSubGrpNo.getValue().toString();
				studentGroupeId =  studentYearSem+"."+studentProgramme+"."+studentGroupeNumber;
				studentSubGroupeId =  studentGroupeId+"."+studentSubGroupeNumber;
				GenerateId(studentGroupeId,studentSubGroupeId);
			}
			public void GenerateId(String studentGroupeId,String studentSubGroupeId) {
				lblMngStudentGrpIdVal.setText(studentGroupeId);
				lblMngStudentSubGrpIdVal.setText(studentSubGroupeId);
				
			}
		});
		btnMngStudentsGenerateIds.setFont(new Font("Arial", Font.BOLD, 13));
		tabManageStudents.add(btnMngStudentsGenerateIds);
		
		JButton btnMngStudentsUpdate = new JButton("Update");
		btnMngStudentsUpdate.setBounds(437, 613, 158, 47);
		btnMngStudentsUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studentYearSem = (String) comboBoxMngStudentsacademicYearSem.getSelectedItem();
				studentProgramme =  (String) comboBoxMngStudentsProgramme.getSelectedItem();
				studentGroupeNumber =  "0" + spinnerMngStudentGrpNo.getValue().toString();
				studentSubGroupeNumber =  spinnerMngStudentSubGrpNo.getValue().toString();
				studentGroupeId =  lblMngStudentGrpIdVal.getText();
				studentSubGroupeId =  lblMngStudentSubGrpIdVal.getText();
				crud = 0;
				stdid = Integer.parseInt(tabelMngStudents.getModel().getValueAt(tabelMngStudents.getSelectedRow(), 0).toString());
				ValidateInputs(studentYearSem,studentProgramme,studentGroupeNumber,studentSubGroupeNumber,studentGroupeId,studentSubGroupeId,crud,stdid);
				
			}
		});
		btnMngStudentsUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngStudentsUpdate.setBackground(SystemColor.textHighlight);
		tabManageStudents.add(btnMngStudentsUpdate);
		

		
	}
	public void ValidateInputs(String studentYearSem,String studentProgramme,String studentGroupeNumber,String studentSubGroupeNumber,String studentGroupeId,String studentSubGroupeId, int crud, int stdid) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.studentYearSem = studentYearSem;
			this.studentProgramme =  studentProgramme;
			this.studentGroupeNumber =  studentGroupeNumber;
			this.studentSubGroupeNumber =  studentSubGroupeNumber;
			this.studentGroupeId =  studentGroupeId;
			this.studentSubGroupeId = studentSubGroupeId;
			this.stdid = stdid;
			
			if(studentYearSem.isEmpty() || studentProgramme.isEmpty() || studentGroupeNumber.isEmpty() || studentSubGroupeNumber.isEmpty() || studentGroupeId.isEmpty() || studentSubGroupeId.isEmpty() ) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else if(crud == 0) {
					PreparedStatement  ps = con.prepareStatement("UPDATE students SET academicYearSem = ?, programme = ?, grpNo = ? , subGrpNo = ? , grpId = ? , subGrpId = ?  WHERE stdid = ?");
					ps.setString(1,studentYearSem);
					ps.setString(2,studentProgramme);
					ps.setString(3,studentGroupeNumber);
					ps.setString(4,studentSubGroupeNumber);
					ps.setString(5,studentGroupeId);
					ps.setString(6, studentSubGroupeId);
					ps.setInt(7,stdid);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
				}else if(crud == 1){
					query.execute("insert into students (academicYearSem, programme, grpNo, subGrpNo, grpId, subGrpId) values('"+studentYearSem+"','"+studentProgramme+"','"+studentGroupeNumber+"','"+studentSubGroupeNumber+"','"+studentGroupeId+"','"+studentSubGroupeId+"')");
					showMessageDialog(null, "Successfully added!");
					UpdateTable();
					
					
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
			PreparedStatement ps=con.prepareStatement("select * from students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			tabelMngStudents = new JTable(model);
			tabelMngStudents.setBounds(161, 11, 1165, 372);
			tabelMngStudents.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		tabelMngStudents.setModel(model);
		tabelMngStudents.repaint();
	}

}
