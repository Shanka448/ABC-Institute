package abcInstitute;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SessionsNotAvailableTimes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable tableLec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionsNotAvailableTimes frame = new SessionsNotAvailableTimes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SessionsNotAvailableTimes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 921, 677);
		setTitle("Sessions and Not Available Times Allocation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 903, 630);
		contentPane.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Not Available Times Student", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblStudentSessionsAnd = new JLabel("Student Sessions and Not Available Times Allocations");
		lblStudentSessionsAnd.setForeground(Color.BLUE);
		lblStudentSessionsAnd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStudentSessionsAnd.setBounds(234, 13, 498, 24);
		panel_1.add(lblStudentSessionsAnd);
		
		JLabel label_1 = new JLabel("Select Group");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(84, 77, 107, 16);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Select Sub Group");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(84, 121, 117, 16);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Select Session ID");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBounds(84, 165, 117, 16);
		panel_1.add(label_3);
		
		JComboBox cmbGroup = new JComboBox();
		cmbGroup.setBounds(234, 73, 245, 31);
		panel_1.add(cmbGroup);
		
		JComboBox cmbSgroup = new JComboBox();
		cmbSgroup.setBounds(234, 117, 245, 31);
		panel_1.add(cmbSgroup);
		
		JComboBox cmbSsId = new JComboBox();
		cmbSsId.setBounds(234, 163, 245, 31);
		panel_1.add(cmbSsId);
		
		JLabel lblTo_1 = new JLabel("To");
		lblTo_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTo_1.setBounds(556, 142, 54, 16);
		panel_1.add(lblTo_1);
		
		JButton btnSsubmit = new JButton("SUBMIT");
		btnSsubmit.setForeground(Color.WHITE);
		btnSsubmit.setBackground(Color.BLUE);
		btnSsubmit.setBounds(730, 524, 147, 42);
		panel_1.add(btnSsubmit);
		
		JButton btnSclear = new JButton("CLEAR");
		btnSclear.setForeground(Color.WHITE);
		btnSclear.setBackground(new Color(255, 140, 0));
		btnSclear.setBounds(176, 524, 142, 42);
		panel_1.add(btnSclear);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrom.setBounds(556, 99, 54, 16);
		panel_1.add(lblFrom);
		
		JLabel label_5 = new JLabel("Time");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_5.setBounds(676, 63, 54, 16);
		panel_1.add(label_5);
		
		DbValTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 243, 874, 256);
		panel_1.add(scrollPane);
		
		//Std Delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table.getSelectedRow();
					String cell = table.getModel().getValueAt(i, 0).toString();
					Connection conn=DB.getConnection();
					String delete = "DELETE FROM std_not_available_times WHERE ID = " +cell;
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						PreparedStatement statement = conn.prepareStatement(delete);
						statement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted Successfully!");
						UpdateTable();
					}else {
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(360, 524, 142, 42);
		panel_1.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setBounds(540, 524, 147, 42);
		panel_1.add(btnUpdate);
		
		JSpinner spnFrom = new JSpinner();
		Date date = new Date();
		spnFrom.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnStime = new JSpinner.DateEditor(spnFrom, "HH:mm:ss");
		spnFrom.setEditor(de_spnStime);
		spnFrom.setBounds(638, 92, 218, 31);
		panel_1.add(spnFrom);
		
		JSpinner spnTo = new JSpinner();
		Date Edate = new Date();
		spnTo.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnEtime = new JSpinner.DateEditor(spnTo, "HH:mm:ss");
		spnTo.setEditor(de_spnEtime);
		spnTo.setBounds(638, 142, 218, 31);
		panel_1.add(spnTo);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeAllocation.main(new String[]{});
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setBounds(12, 524, 132, 42);
		panel_1.add(btnNewButton);
		
		
		//Student Submit
		btnSsubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date Sdate =  (Date) spnFrom.getValue();
				Time time = new Time(Sdate.getTime());
				Date Edate = (Date) spnTo.getValue();
				Time Etime = new Time(Edate.getTime());
				String group = cmbGroup.getSelectedItem().toString();
				String subGroup = cmbSgroup.getSelectedItem().toString();
				String session = cmbSsId.getSelectedItem().toString();
				
				try {
					Connection conn=DB.getConnection();
					String insert = "INSERT INTO std_not_available_times (SGroup, Sub_Group, Session_ID, FromT, ToT) " 
							+ "VALUES (?, ?, ?, ?, ?)";
					
					PreparedStatement statement = conn.prepareStatement(insert);
					statement.setString(1, group);
					statement.setString(2, subGroup);
					statement.setString(3, session);
					statement.setTime(4, time);
					statement.setTime(5, Etime);
					
					int rows = statement.executeUpdate();
					
					if(rows>0) {
						JOptionPane.showMessageDialog(null, "Successfully Added!");
						conn.commit();
					}
					DbValTableLec();
					DbValTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Not Available Times Lecturer", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblSessionsAndNot = new JLabel("Lecturer Sessions and Not Available Times Allocations");
		lblSessionsAndNot.setBounds(206, 13, 498, 24);
		panel.add(lblSessionsAndNot);
		lblSessionsAndNot.setForeground(Color.BLUE);
		lblSessionsAndNot.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSelectLecturer = new JLabel("Select Lecturer");
		lblSelectLecturer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelectLecturer.setBounds(64, 80, 107, 16);
		panel.add(lblSelectLecturer);
		
		JComboBox cmbSelectLec = new JComboBox();
		cmbSelectLec.setBounds(223, 73, 245, 31);
		panel.add(cmbSelectLec);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBounds(649, 532, 170, 42);
		panel.add(btnSubmit);
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(Color.BLUE);
		
		JLabel label = new JLabel("Select Group");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(64, 124, 107, 16);
		panel.add(label);
		
		JComboBox cmbSelectGroup = new JComboBox();
		cmbSelectGroup.setBounds(223, 117, 245, 31);
		panel.add(cmbSelectGroup);
		
		JLabel lblFrom_1 = new JLabel("From");
		lblFrom_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblFrom_1.setBounds(504, 121, 54, 16);
		panel.add(lblFrom_1);
		
		JLabel label_6 = new JLabel("Select Sub Group");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_6.setBounds(64, 168, 117, 16);
		panel.add(label_6);
		
		JComboBox cmbSubGroup = new JComboBox();
		cmbSubGroup.setBounds(223, 161, 245, 31);
		panel.add(cmbSubGroup);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTo.setBounds(504, 165, 54, 16);
		panel.add(lblTo);
		
		JComboBox cmbSession = new JComboBox();
		cmbSession.setBounds(639, 73, 218, 31);
		panel.add(cmbSession);
		
		JLabel label_8 = new JLabel("Select Session ID");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_8.setBounds(504, 80, 117, 16);
		panel.add(label_8);
		
		DbValTableLec();
		JScrollPane scrollPane_1 = new JScrollPane(tableLec);
		scrollPane_1.setBounds(12, 252, 874, 267);
		panel.add(scrollPane_1);
		
		JButton btnUpdate1 = new JButton("Update");
		btnUpdate1.setForeground(Color.WHITE);
		btnUpdate1.setBackground(Color.ORANGE);
		btnUpdate1.setBounds(451, 532, 170, 42);
		panel.add(btnUpdate1);
		
		//Lec Delete
		JButton btnDel = new JButton("Delete");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = tableLec.getSelectedRow();
					String cell = tableLec.getModel().getValueAt(i, 0).toString();
					Connection conn=DB.getConnection();
					String delete = "DELETE FROM lec_not_avilable_times WHERE ID = " +cell;
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						PreparedStatement statement = conn.prepareStatement(delete);
						statement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted Successfully!");
						conn.commit();
					}
					
					UpdateTableLec();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDel.setForeground(Color.WHITE);
		btnDel.setBackground(Color.RED);
		btnDel.setBounds(249, 532, 170, 42);
		panel.add(btnDel);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(new Color(255, 140, 0));
		btnClear.setBounds(46, 532, 170, 42);
		panel.add(btnClear);
		
		JSpinner spnLfrom = new JSpinner();
		Date Sdate = new Date();
		spnLfrom.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spntimeFrom = new JSpinner.DateEditor(spnLfrom, "HH:mm:ss");
		spnLfrom.setEditor(de_spntimeFrom);
		spnLfrom.setBounds(639, 117, 218, 31);
		panel.add(spnLfrom);
		
		JSpinner spnLto = new JSpinner();
		Date Tdate = new Date();
		spnLto.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spntimeTo = new JSpinner.DateEditor(spnLto, "HH:mm:ss");
		spnLto.setEditor(de_spntimeTo);
		spnLto.setBounds(639, 158, 218, 31);
		panel.add(spnLto);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Connection conn=DB.getConnection();
					String selectLec = "SELECT lecturerName FROM lecturers";
					String selectStd = "SELECT grpId, subGrpId FROM students";
					String selectSsid = "SELECT ssid FROM sessions";
					PreparedStatement ps = conn.prepareStatement(selectLec);
					PreparedStatement statement = conn.prepareStatement(selectStd);
					PreparedStatement ps2 = conn.prepareStatement(selectSsid);
					ResultSet rs = ps.executeQuery();
					ResultSet rs2 = ps2.executeQuery();
					ResultSet result = statement.executeQuery();
					
					while(rs.next()) {
						String name = rs.getString("lecturerName");
						cmbSelectLec.addItem(name);
					}
					while(result.next()) {
						String GroupID = result.getString("grpId");
						String SubGroupID = result.getString("subGrpId");
						cmbGroup.addItem(GroupID);
						cmbSelectGroup.addItem(GroupID);
						cmbSgroup.addItem(SubGroupID);
						cmbSubGroup.addItem(SubGroupID);
					}
					while(rs2.next()) {
						String ssId = rs2.getString("ssid");
						cmbSsId.addItem(ssId);
						cmbSession.addItem(ssId);
					}
					UpdateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Student Update Button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnFrom.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnTo.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn=DB.getConnection();
					int i = table.getSelectedRow();
					String ID = table.getModel().getValueAt(i, 0).toString();
					String update = "UPDATE std_not_available_times SET SGroup=?, Sub_Group=?, Session_ID=?, FromT=?, ToT=? WHERE ID=" +ID;
					PreparedStatement statement = conn.prepareStatement(update);
					statement.setString(1, (String)cmbGroup.getSelectedItem());
					statement.setString(2, (String)cmbSgroup.getSelectedItem());
					statement.setString(3, (String)cmbSsId.getSelectedItem());
					statement.setTime(4, time);
					statement.setTime(5, Etime);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					UpdateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Lecture Update
		btnUpdate1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnLfrom.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnLto.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn=DB.getConnection();
					int i = tableLec.getSelectedRow();
					String ID = tableLec.getModel().getValueAt(i, 0).toString();
					String update = "UPDATE lec_not_avilable_times SET Lecturer_Name=?, Main_Group=?, Sub_Group=?, Session_ID=?, Time_From=?, Time_To=? WHERE ID=" +ID;
					PreparedStatement statement = conn.prepareStatement(update);
					statement.setString(1, cmbSelectLec.getSelectedItem().toString());
					statement.setString(2, cmbSelectGroup.getSelectedItem().toString());
					statement.setString(3, cmbSubGroup.getSelectedItem().toString());
					statement.setString(4, cmbSession.getSelectedItem().toString());
					statement.setTime(5, time);
					statement.setTime(6, Etime);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Successfully Updated!");

					UpdateTableLec();
					UpdateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Std Table
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				TableModel model = table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				try {
					Date date1 = formatter.parse(model.getValueAt(selectedRowIndex, 4).toString());
					Date dateto = formatter.parse(model.getValueAt(selectedRowIndex, 5).toString());
					spnFrom.setValue(date1);
					spnTo.setValue(dateto);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cmbGroup.setSelectedItem((Object)model.getValueAt(table.getSelectedRow(), 1).toString());
				cmbSgroup.setSelectedItem((Object)model.getValueAt(table.getSelectedRow(), 2).toString());
				cmbSsId.setSelectedItem((Object)model.getValueAt(table.getSelectedRow(), 3).toString());
//				spnFrom.setValue(date1);
//				spnTo.setValue(model.getValueAt(selectedRowIndex, 4));
			}
		});
		
		//Lec Table
		tableLec.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				TableModel model = tableLec.getModel();
				int selectedRowIndex = tableLec.getSelectedRow();
				cmbSelectLec.setSelectedItem((Object)model.getValueAt(selectedRowIndex, 1).toString());
				cmbSelectGroup.setSelectedItem((Object)model.getValueAt(selectedRowIndex, 2).toString());
				cmbSubGroup.setSelectedItem((Object)model.getValueAt(selectedRowIndex, 3).toString());
				cmbSession.setSelectedItem((Object)model.getValueAt(selectedRowIndex, 4).toString());
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				try {
					Date date1 = formatter.parse(model.getValueAt(selectedRowIndex, 5).toString());
					Date dateto = formatter.parse(model.getValueAt(selectedRowIndex, 6).toString());
					spnLfrom.setValue(date1);
					spnLto.setValue(dateto);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Clear Button
		btnSclear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbGroup.setSelectedItem(null);
				cmbSgroup.setSelectedItem(null);
				cmbSsId.setSelectedItem(null);
			}
		});
		
		//Lec Clear Button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbSelectLec.setSelectedItem(null);
				cmbSelectGroup.setSelectedItem(null);
				cmbSubGroup.setSelectedItem(null);
				cmbSession.setSelectedItem(null);
			}
		});
		
		//Lecturer Submit
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date Sdate =  (Date) spnLfrom.getValue();
				Time time = new Time(Sdate.getTime());
				Date Edate = (Date) spnLto.getValue();
				Time Etime = new Time(Edate.getTime());
				
				String Lecturer = cmbSelectLec.getSelectedItem().toString(); 
				String group = cmbSelectGroup.getSelectedItem().toString();
				String subGroup = cmbSubGroup.getSelectedItem().toString();
				String session = cmbSession.getSelectedItem().toString();
				
				try {
					Connection conn=DB.getConnection();
					String insertLec = "INSERT INTO lec_not_avilable_times (Lecturer_Name, Main_Group, Sub_Group, Session_ID, Time_From, Time_To) " 
							+ "VALUES (?, ?, ?, ?, ?, ?)";
					
					PreparedStatement statementLec = conn.prepareStatement(insertLec);
					statementLec.setString(1, Lecturer);
					statementLec.setString(2, group);
					statementLec.setString(3, subGroup);
					statementLec.setString(4, session);
					statementLec.setTime(5, time);
					statementLec.setTime(6, Etime);
					
					int rows = statementLec.executeUpdate();
					
					if(rows>0) {
						JOptionPane.showMessageDialog(null, "Successfully Added!");
					}
					UpdateTableLec();
					UpdateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	public void DbValTable() {
		String data[][]=null;
		String column[]=null;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from std_not_available_times",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			table = new JTable(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Group No", "Sub Group No", "Session ID", "From", "To"
				}
			));
			table.setBackground(new java.awt.Color(255, 255, 255));
			table.setBounds(161, 11, 1165, 372);
			table.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from std_not_available_times",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		table.setModel(model);
		table.repaint();
	}
	public void DbValTableLec() {
		String data[][]=null;
		String column[]=null;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from lec_not_avilable_times",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			tableLec = new JTable(model);
			tableLec.setBackground(new java.awt.Color(255, 255, 255));
			tableLec.setBounds(161, 11, 1165, 372);
			tableLec.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTableLec() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from lec_not_avilable_times",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		tableLec.setModel(model);
		tableLec.repaint();
	}

}
