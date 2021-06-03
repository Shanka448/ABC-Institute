package abcInstitute;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

public class NotAvailableLocations extends JFrame{

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotAvailableLocations frame = new NotAvailableLocations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void updateTable() {
		try {
			Connection conn = DB.getConnection();
			String selectSession = "SELECT * FROM preffered_sessions";
			String selectLocation = "SELECT * FROM not_available_locations ";
			PreparedStatement statement = conn.prepareStatement(selectSession);
			PreparedStatement statement1 = conn.prepareStatement(selectLocation);
			ResultSet resultSession = statement.executeQuery();
			ResultSet resultLocation = statement1.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(resultSession));
			table_1.setModel(DbUtils.resultSetToTableModel(resultLocation));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public NotAvailableLocations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 610, 632);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 603, 597);
		contentPane.add(tabbedPane);
		setResizable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Sessions", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblPrefferedSessions = new JLabel("Preffered Sessions");
		lblPrefferedSessions.setForeground(Color.BLUE);
		lblPrefferedSessions.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrefferedSessions.setBounds(202, 25, 181, 24);
		panel_1.add(lblPrefferedSessions);
		
		JLabel lblSessionId = new JLabel("Select Lecturer");
		lblSessionId.setBounds(33, 93, 95, 16);
		panel_1.add(lblSessionId);
		
		JComboBox cmbLec = new JComboBox();
		cmbLec.setBounds(140, 90, 143, 22);
		panel_1.add(cmbLec);
		
		JLabel label_2 = new JLabel("Start Time");
		label_2.setBounds(315, 135, 68, 16);
		panel_1.add(label_2);
		
		JLabel lblSubGroup = new JLabel("Select Group");
		lblSubGroup.setBounds(33, 135, 80, 16);
		panel_1.add(lblSubGroup);
		
		JComboBox cmbGroup = new JComboBox();
		cmbGroup.setBounds(140, 138, 143, 22);
		panel_1.add(cmbGroup);
		
		JLabel label_4 = new JLabel("End Time");
		label_4.setBounds(315, 182, 68, 16);
		panel_1.add(label_4);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimeAllocation.main(new String[]{});
				dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.GREEN);
		btnBack.setBounds(33, 516, 110, 38);
		panel_1.add(btnBack);
		
		JButton btnAddSessions = new JButton("Add Session");
		btnAddSessions.setForeground(Color.WHITE);
		btnAddSessions.setBackground(Color.BLUE);
		btnAddSessions.setBounds(470, 516, 116, 38);
		panel_1.add(btnAddSessions);
		
		JSpinner spnStime = new JSpinner();
		Date date = new Date();
		spnStime.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnStime = new JSpinner.DateEditor(spnStime, "HH:mm:ss");
		spnStime.setEditor(de_spnStime);
		spnStime.setBounds(405, 132, 143, 22);
		panel_1.add(spnStime);
		
		JComboBox cmbSub = new JComboBox();
		cmbSub.setBounds(140, 179, 143, 22);
		panel_1.add(cmbSub);
		
		JLabel lblDate = new JLabel("Select Subject");
		lblDate.setBounds(33, 182, 95, 16);
		panel_1.add(lblDate);
		
		JSpinner spnEtime = new JSpinner();
		Date dateS = new Date();
		spnEtime.setModel(new SpinnerDateModel(dateS, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnEtime = new JSpinner.DateEditor(spnEtime, "HH:mm:ss");
		spnEtime.setEditor(de_spnEtime);
		spnEtime.setBounds(405, 179, 143, 22);
		panel_1.add(spnEtime);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setBounds(315, 93, 56, 16);
		panel_1.add(lblDate_1);
		
		JComboBox cmbSDate = new JComboBox();
		cmbSDate.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
		cmbSDate.setBounds(405, 90, 143, 22);
		panel_1.add(cmbSDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 262, 574, 226);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				
				cmbLec.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
				cmbGroup.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
				cmbSub.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
				cmbSDate.setSelectedItem(model.getValueAt(selectedRowIndex, 4).toString());
				SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				try {
					Date date1 = formatter.parse(model.getValueAt(selectedRowIndex, 6).toString());
					Date dateto = formatter.parse(model.getValueAt(selectedRowIndex, 7).toString());
					spnStime.setValue(date1);
					spnEtime.setValue(dateto);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(model.getValueAt(selectedRowIndex, 1).toString());
				System.out.println(model.getValueAt(selectedRowIndex, 2).toString());
				System.out.println(model.getValueAt(selectedRowIndex, 3).toString());
				System.out.println(model.getValueAt(selectedRowIndex, 4).toString());
				System.out.println(model.getValueAt(selectedRowIndex, 5).toString());
				System.out.println(model.getValueAt(selectedRowIndex, 6).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.setForeground(Color.WHITE);
		btnupdate.setBackground(Color.ORANGE);
		btnupdate.setBounds(312, 514, 116, 42);
		panel_1.add(btnupdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(167, 516, 116, 38);
		panel_1.add(btnDelete);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setBounds(33, 217, 56, 16);
		panel_1.add(lblRoom);
		
		JComboBox cmbRoomS = new JComboBox();
		cmbRoomS.setBounds(140, 214, 143, 22);
		panel_1.add(cmbRoomS);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Not Available Locations", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblAddNotAvailable = new JLabel("Add Not Available Locations");
		lblAddNotAvailable.setBounds(194, 23, 258, 24);
		panel.add(lblAddNotAvailable);
		lblAddNotAvailable.setForeground(Color.BLUE);
		lblAddNotAvailable.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblSelectRoom = new JLabel("Select Room");
		lblSelectRoom.setBounds(25, 86, 80, 16);
		panel.add(lblSelectRoom);
		
		JComboBox cmbRoom = new JComboBox();
		cmbRoom.setBounds(117, 86, 143, 22);
		panel.add(cmbRoom);
		
		JLabel lblStartTime = new JLabel("Start Time");
		lblStartTime.setBounds(303, 86, 68, 16);
		panel.add(lblStartTime);
		
		JLabel lblSelectDay = new JLabel("Select Day");
		lblSelectDay.setBounds(25, 158, 80, 16);
		panel.add(lblSelectDay);
		
		JComboBox cmbDate = new JComboBox();
		cmbDate.setBounds(117, 158, 143, 22);
		panel.add(cmbDate);
		cmbDate.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
		
		JLabel lblEndTime = new JLabel("End Time");
		lblEndTime.setBounds(303, 158, 68, 16);
		panel.add(lblEndTime);
		
		JButton btnBak = new JButton("Back");
		btnBak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimeAllocation.main(new String[]{});
				dispose();
			}
		});
		btnBak.setBounds(59, 516, 110, 38);
		panel.add(btnBak);
		btnBak.setBackground(Color.GREEN);
		btnBak.setForeground(Color.WHITE);
		
		JButton btnAddLocations = new JButton("Add Location");
		btnAddLocations.setBounds(454, 516, 116, 38);
		panel.add(btnAddLocations);
		btnAddLocations.setBackground(Color.BLUE);
		btnAddLocations.setForeground(Color.WHITE);
		
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spnStartT = new JSpinner(sm);
		JSpinner.DateEditor time = new JSpinner.DateEditor(spnStartT, "HH:mm:ss");
		spnStartT.setEditor(time);
		spnStartT.setBounds(395, 83, 154, 22);
		panel.add(spnStartT);
		
		JSpinner spnEndT = new JSpinner();
		spnEndT.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor Endtime = new JSpinner.DateEditor(spnEndT, "HH:mm:ss");
		spnEndT.setEditor(Endtime);
		spnEndT.setBounds(395, 155, 154, 22);
		panel.add(spnEndT);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 212, 574, 266);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = table_1.getModel();
				int selectedRowIndex = table_1.getSelectedRow();
				
				cmbRoom.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
				cmbDate.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
				spnStartT.setValue(model.getValueAt(selectedRowIndex, 3));
				spnEndT.setValue(model.getValueAt(selectedRowIndex, 4));
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JButton btnUpdateLoc = new JButton("Update");
		btnUpdateLoc.setForeground(Color.WHITE);
		btnUpdateLoc.setBackground(Color.ORANGE);
		btnUpdateLoc.setBounds(311, 514, 116, 42);
		panel.add(btnUpdateLoc);
		
		JButton btnDeleteLoc = new JButton("Delete");
		btnDeleteLoc.setBackground(Color.RED);
		btnDeleteLoc.setForeground(Color.WHITE);
		btnDeleteLoc.setBounds(183, 516, 116, 38);
		panel.add(btnDeleteLoc);
		
		//startup loading
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					Connection conn = DB.getConnection();
					String selectRoom = "SELECT roomName FROM locations";
					String selectLec = "SELECT Name FROM lecturers1";
					String selectGroup = "SELECT grpId FROM students";
					String selectSubject = "SELECT subjectName FROM subjects";
					PreparedStatement statement = conn.prepareStatement(selectRoom);
					PreparedStatement statementLec = conn.prepareStatement(selectLec);
					PreparedStatement statementGroup = conn.prepareStatement(selectGroup);
					PreparedStatement statementSub = conn.prepareStatement(selectSubject);
					ResultSet rs = statement.executeQuery();
					ResultSet rsLec = statementLec.executeQuery();
					ResultSet rsGroup = statementGroup.executeQuery();
					ResultSet rsSub = statementSub.executeQuery();
					
					while(rs.next()) {
						String room = rs.getString("roomName");
						cmbRoom.addItem(room);
						cmbRoomS.addItem(room);
					}
					while(rsLec.next()) {
						String Lec = rsLec.getString("Name");
						cmbLec.addItem(Lec);
					}
					while(rsGroup.next()) {
						String group = rsGroup.getString("grpId");
						cmbGroup.addItem(group);
					}
					while(rsSub.next()) {
						String sub = rsSub.getString("subjectName");
						cmbSub.addItem(sub);
					}
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		
		//Add Locations
		btnAddLocations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnStartT.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnEndT.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn = DB.getConnection();
					String add = "INSERT INTO not_available_locations (Room, Day, Start_Time, End_Time)" 
							+ "VALUES (?, ?, ?, ?)";
					PreparedStatement statement = conn.prepareStatement(add);
					statement.setString(1, cmbRoom.getSelectedItem().toString());
					statement.setString(2, cmbDate.getSelectedItem().toString());
					statement.setTime(3, time);
					statement.setTime(4, Etime);
					
					int rows = statement.executeUpdate();
					if(rows>0) {
						JOptionPane.showMessageDialog(null, "Successfully Added!");
//						conn.commit();
					}
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnAddSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnStime.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnEtime.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn = DB.getConnection();
					String addSessions = "INSERT INTO preffered_sessions (Lecturer, Main_Group, subject, Date, Room, Start_Time, End_Time)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = conn.prepareStatement(addSessions);
					statement.setString(1, cmbLec.getSelectedItem().toString());
					statement.setString(2, cmbGroup.getSelectedItem().toString());
					statement.setString(3, cmbSub.getSelectedItem().toString());
					statement.setString(4, cmbSDate.getSelectedItem().toString());
					statement.setString(5, cmbRoomS.getSelectedItem().toString());
					statement.setTime(6, time);
					statement.setTime(7, Etime);
					
					int rows = statement.executeUpdate();
					if(rows>0) {
						JOptionPane.showMessageDialog(null, "Successfully Added!");
//						conn.commit();
					}
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Sessions Update
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnStime.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnEtime.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn = DB.getConnection();
					int i = table.getSelectedRow();
					String ID = table.getModel().getValueAt(i, 0).toString();
					String update = "UPDATE preffered_sessions SET Lecturer=?, Main_Group=?, subject=?, Date=?, Room=?, Start_Time=?, End_Time=? WHERE ID=" +ID;
					PreparedStatement statement = conn.prepareStatement(update);
					statement.setString(1, cmbLec.getSelectedItem().toString());
					statement.setString(2, cmbGroup.getSelectedItem().toString());
					statement.setString(3, cmbSub.getSelectedItem().toString());
					statement.setString(4, cmbSDate.getSelectedItem().toString());
					statement.setString(5, cmbRoomS.getSelectedItem().toString());
					statement.setTime(6, time);
					statement.setTime(7, Etime);
					statement.executeUpdate();
//					conn.commit();
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		
		//Delete Sessions
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table.getSelectedRow();
					String cell = table.getModel().getValueAt(i, 0).toString();
					Connection conn = DB.getConnection();
					String delete = "DELETE FROM preffered_sessions WHERE ID = " +cell;
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						PreparedStatement statement = conn.prepareStatement(delete);
						statement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted Successfully!");
//						conn.commit();
					}else {
						
					}
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Not Available Locations Update
		btnUpdateLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date Sdate =  (Date) spnStime.getValue();
					Time time = new Time(Sdate.getTime());
					Date Edate = (Date) spnEtime.getValue();
					Time Etime = new Time(Edate.getTime());
					
					Connection conn = DB.getConnection();
					int i = table_1.getSelectedRow();
					String ID = table_1.getModel().getValueAt(i, 0).toString();
					String update = "UPDATE not_available_locations SET Room=?, Day=?, Start_Time=?, End_Time=? WHERE ID=" +ID; 
					PreparedStatement statement = conn.prepareStatement(update);
					statement.setString(1, cmbRoom.getSelectedItem().toString());
					statement.setString(2, cmbDate.getSelectedItem().toString());
					statement.setTime(3, time);
					statement.setTime(4, Etime);
					statement.executeUpdate();
//					conn.commit();
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnDeleteLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table_1.getSelectedRow();
					String cell = table_1.getModel().getValueAt(i, 0).toString();
					Connection conn = DB.getConnection();
					String delete = "DELETE FROM not_available_locations WHERE ID = " +cell;
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						PreparedStatement statement = conn.prepareStatement(delete);
						statement.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted Successfully!");
//						conn.commit();
					}else {
						
					}
					updateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
}
