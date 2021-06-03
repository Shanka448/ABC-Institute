package abcInstitute;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.Event;

import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import abcInstitue.DB;

public class AddWorkingDaysHours {

	private JFrame frmAddWorkingDays;
	private JTextField txtEid;
	private JTextField txtLname;
	
	Connection conn = null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWorkingDaysHours window = new AddWorkingDaysHours();
					window.frmAddWorkingDays.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddWorkingDaysHours() {
		initialize();
	}
	
	public void DbValTable() {
//		String data[][]=null;
//		String column[]=null;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from lecturers1");
			ResultSet rs=ps.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
//			ResultSetMetaData rsmd=rs.getMetaData();
//			int cols=rsmd.getColumnCount();
//			column=new String[cols];
//			for(int i=1;i<=cols;i++){
//				column[i-1]=rsmd.getColumnName(i);
//			}
//			
//			rs.last();
//			int rows=rs.getRow();
//			rs.beforeFirst();
//
//			data=new String[rows][cols];
//			int count=0;
//			while(rs.next()){
//				for(int i=1;i<=cols;i++){
//					data[count][i-1]=rs.getString(i);
//					
//				}
//				count++;
//			}
//			AbstractTableModel model = new DefaultTableModel(data,new String[] {
//					"ID", "Name", "NoOfWorkingDays", "MHrs", "MMin", "THrs", "Tmin", "WHrs", "WMin", "ThHrs", "ThMin", "FHrs", "FMin", "SHrs", "SMin", "SuHrs", "SuMin"
//				});
//			table = new JTable(model);
//			table.setBounds(161, 11, 1165, 372);
//			table.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddWorkingDays = new JFrame();
		frmAddWorkingDays.setResizable(false);
		frmAddWorkingDays.getContentPane().setBackground(Color.WHITE);
		frmAddWorkingDays.setForeground(Color.WHITE);
		frmAddWorkingDays.setBackground(Color.WHITE);
		frmAddWorkingDays.setTitle("Working Days and Hours");
		frmAddWorkingDays.setBounds(100, 100, 1113, 726);
		frmAddWorkingDays.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddWorkingDays.getContentPane().setLayout(null);
		
		DbValTable();
		
		JLabel lblNewLabel = new JLabel("Working Days and Hours");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(408, 13, 259, 24);
		frmAddWorkingDays.getContentPane().add(lblNewLabel);
		
		JLabel lblNoOfWorking = new JLabel("No of Working Days");
		lblNoOfWorking.setForeground(new Color(0, 0, 0));
		lblNoOfWorking.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNoOfWorking.setBounds(67, 189, 147, 16);
		frmAddWorkingDays.getContentPane().add(lblNoOfWorking);
		
		JLabel lblWorkingDays = new JLabel("Working Days");
		lblWorkingDays.setForeground(new Color(0, 0, 0));
		lblWorkingDays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWorkingDays.setBounds(460, 97, 147, 16);
		frmAddWorkingDays.getContentPane().add(lblWorkingDays);
		
		JLabel lblWorkingTimePer = new JLabel("Working Time Per Day");
		lblWorkingTimePer.setForeground(new Color(0, 0, 0));
		lblWorkingTimePer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWorkingTimePer.setBounds(756, 47, 176, 24);
		frmAddWorkingDays.getContentPane().add(lblWorkingTimePer);
		
		JSpinner mHrs = new JSpinner();
		mHrs.setEnabled(false);
		mHrs.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		mHrs.setBounds(734, 91, 65, 22);
		frmAddWorkingDays.getContentPane().add(mHrs);
		
		JSpinner spnDays = new JSpinner();
		spnDays.setModel(new SpinnerNumberModel(0, 0, 7, 1));
		spnDays.setBounds(222, 187, 209, 30);
		frmAddWorkingDays.getContentPane().add(spnDays);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setForeground(new Color(0, 0, 0));
		lblHours.setBounds(811, 94, 55, 16);
		frmAddWorkingDays.getContentPane().add(lblHours);
		
		JSpinner mMin = new JSpinner();
		mMin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			}
		});
		mMin.setEnabled(false);
		mMin.setModel(new SpinnerNumberModel(0, 0, 60, 30));
		mMin.setBounds(878, 91, 65, 22);
		frmAddWorkingDays.getContentPane().add(mMin);
		
		JSpinner tHrs = new JSpinner();
		tHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		tHrs.setEnabled(false);
		tHrs.setBounds(734, 126, 65, 22);
		frmAddWorkingDays.getContentPane().add(tHrs);
		
		JLabel label = new JLabel("Hours");
		label.setForeground(new Color(0, 0, 0));
		label.setBounds(811, 129, 55, 16);
		frmAddWorkingDays.getContentPane().add(label);
		
		JSpinner tMin = new JSpinner();
		tMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		tMin.setEnabled(false);
		tMin.setBounds(878, 126, 65, 22);
		frmAddWorkingDays.getContentPane().add(tMin);
		
		JLabel label_1 = new JLabel("Minutes");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setBounds(955, 129, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_1);
		
		JSpinner wHrs = new JSpinner();
		wHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		wHrs.setEnabled(false);
		wHrs.setBounds(734, 156, 65, 22);
		frmAddWorkingDays.getContentPane().add(wHrs);
		
		JLabel label_2 = new JLabel("Hours");
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setBounds(811, 159, 55, 16);
		frmAddWorkingDays.getContentPane().add(label_2);
		
		JSpinner wMin = new JSpinner();
		wMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		wMin.setEnabled(false);
		wMin.setBounds(878, 156, 65, 22);
		frmAddWorkingDays.getContentPane().add(wMin);
		
		JLabel label_3 = new JLabel("Minutes");
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setBounds(955, 159, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_3);
		
		JSpinner thHrs = new JSpinner();
		thHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		thHrs.setEnabled(false);
		thHrs.setBounds(734, 186, 65, 22);
		frmAddWorkingDays.getContentPane().add(thHrs);
		
		JLabel label_4 = new JLabel("Hours");
		label_4.setForeground(new Color(0, 0, 0));
		label_4.setBounds(811, 189, 55, 16);
		frmAddWorkingDays.getContentPane().add(label_4);
		
		JSpinner thMin = new JSpinner();
		thMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		thMin.setEnabled(false);
		thMin.setBounds(878, 186, 65, 22);
		frmAddWorkingDays.getContentPane().add(thMin);
		
		JLabel label_5 = new JLabel("Minutes");
		label_5.setForeground(new Color(0, 0, 0));
		label_5.setBounds(955, 189, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_5);
		
		JSpinner fHrs = new JSpinner();
		fHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		fHrs.setEnabled(false);
		fHrs.setBounds(734, 216, 65, 22);
		frmAddWorkingDays.getContentPane().add(fHrs);
		
		JLabel label_6 = new JLabel("Hours");
		label_6.setForeground(new Color(0, 0, 0));
		label_6.setBounds(811, 219, 55, 16);
		frmAddWorkingDays.getContentPane().add(label_6);
		
		JSpinner fMin = new JSpinner();
		fMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		fMin.setEnabled(false);
		fMin.setBounds(878, 216, 65, 22);
		frmAddWorkingDays.getContentPane().add(fMin);
		
		JLabel label_7 = new JLabel("Minutes");
		label_7.setForeground(new Color(0, 0, 0));
		label_7.setBounds(955, 219, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_7);
		
		JSpinner sHrs = new JSpinner();
		sHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		sHrs.setEnabled(false);
		sHrs.setBounds(734, 246, 65, 22);
		frmAddWorkingDays.getContentPane().add(sHrs);
		
		JLabel label_8 = new JLabel("Hours");
		label_8.setForeground(new Color(0, 0, 0));
		label_8.setBounds(811, 249, 55, 16);
		frmAddWorkingDays.getContentPane().add(label_8);
		
		JSpinner sMin = new JSpinner();
		sMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		sMin.setEnabled(false);
		sMin.setBounds(878, 246, 65, 22);
		frmAddWorkingDays.getContentPane().add(sMin);
		
		JLabel label_9 = new JLabel("Minutes");
		label_9.setForeground(new Color(0, 0, 0));
		label_9.setBounds(955, 249, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_9);
		
		JSpinner suHrs = new JSpinner();
		suHrs.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		suHrs.setEnabled(false);
		suHrs.setBounds(734, 276, 65, 22);
		frmAddWorkingDays.getContentPane().add(suHrs);
		
		JLabel label_10 = new JLabel("Hours");
		label_10.setForeground(new Color(0, 0, 0));
		label_10.setBounds(811, 279, 55, 16);
		frmAddWorkingDays.getContentPane().add(label_10);
		
		JSpinner suMin = new JSpinner();
		suMin.setModel(new SpinnerNumberModel(0, 0, 59, 30));
		suMin.setEnabled(false);
		suMin.setBounds(878, 276, 65, 22);
		frmAddWorkingDays.getContentPane().add(suMin);
		
		JLabel label_11 = new JLabel("Minutes");
		label_11.setForeground(new Color(0, 0, 0));
		label_11.setBounds(955, 279, 56, 16);
		frmAddWorkingDays.getContentPane().add(label_11);
		
		JLabel lblMinutes = new JLabel("Minutes");
		lblMinutes.setForeground(new Color(0, 0, 0));
		lblMinutes.setBounds(955, 94, 56, 16);
		frmAddWorkingDays.getContentPane().add(lblMinutes);
		
		JCheckBox chckbxMonday = new JCheckBox("Monday");
		chckbxMonday.setForeground(new Color(0, 0, 0));
		chckbxMonday.setBackground(Color.WHITE);
		chckbxMonday.setBounds(590, 93, 77, 25);
		frmAddWorkingDays.getContentPane().add(chckbxMonday);
		
		JCheckBox chckbxTuesday = new JCheckBox("Tuesday");
		chckbxTuesday.setForeground(new Color(0, 0, 0));
		chckbxTuesday.setBackground(Color.WHITE);
		chckbxTuesday.setBounds(590, 123, 77, 25);
		frmAddWorkingDays.getContentPane().add(chckbxTuesday);
		
		JCheckBox chckbxWednesday = new JCheckBox("Wednesday");
		chckbxWednesday.setForeground(new Color(0, 0, 0));
		chckbxWednesday.setBackground(Color.WHITE);
		chckbxWednesday.setBounds(590, 153, 100, 25);
		frmAddWorkingDays.getContentPane().add(chckbxWednesday);
		
		JCheckBox chckbxThursday = new JCheckBox("Thursday");
		chckbxThursday.setForeground(new Color(0, 0, 0));
		chckbxThursday.setBackground(Color.WHITE);
		chckbxThursday.setBounds(590, 183, 81, 25);
		frmAddWorkingDays.getContentPane().add(chckbxThursday);
		
		JCheckBox chckbxFriday = new JCheckBox("Friday");
		chckbxFriday.setForeground(new Color(0, 0, 0));
		chckbxFriday.setBackground(Color.WHITE);
		chckbxFriday.setBounds(590, 213, 81, 25);
		frmAddWorkingDays.getContentPane().add(chckbxFriday);
		
		JCheckBox chckbxSaturday = new JCheckBox("Saturday");
		chckbxSaturday.setForeground(new Color(0, 0, 0));
		chckbxSaturday.setBackground(Color.WHITE);
		chckbxSaturday.setBounds(590, 243, 81, 25);
		frmAddWorkingDays.getContentPane().add(chckbxSaturday);
		
		JCheckBox chckbxSunday = new JCheckBox("Sunday");
		chckbxSunday.setForeground(new Color(0, 0, 0));		
		chckbxSunday.setBackground(Color.WHITE);
		chckbxSunday.setBounds(590, 273, 77, 25);
		frmAddWorkingDays.getContentPane().add(chckbxSunday);
		
		JButton btnInsert = new JButton("INSERT");
		btnInsert.setEnabled(false);
		btnInsert.setBackground(Color.BLUE);
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBounds(655, 616, 97, 43);
		frmAddWorkingDays.getContentPane().add(btnInsert);
		
		JButton btnDeleteAll = new JButton("DELETE");	
		btnDeleteAll.setBackground(Color.RED);
		btnDeleteAll.setForeground(Color.WHITE);
		btnDeleteAll.setBounds(433, 616, 97, 43);
		frmAddWorkingDays.getContentPane().add(btnDeleteAll);
		
		JLabel lblNewLabel_1 = new JLabel("Lecturer Name");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(67, 131, 147, 16);
		frmAddWorkingDays.getContentPane().add(lblNewLabel_1);
		
		txtEid = new JTextField();
		txtEid.setBounds(222, 83, 209, 30);
		frmAddWorkingDays.getContentPane().add(txtEid);
		txtEid.setColumns(10);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setForeground(new Color(0, 0, 0));
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmployeeId.setBounds(67, 86, 147, 16);
		frmAddWorkingDays.getContentPane().add(lblEmployeeId);
		
		txtLname = new JTextField();
		txtLname.setBounds(222, 129, 209, 30);
		frmAddWorkingDays.getContentPane().add(txtLname);
		txtLname.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(Color.ORANGE);
		btnUpdate.setBounds(546, 616, 97, 43);
		frmAddWorkingDays.getContentPane().add(btnUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 347, 1078, 256);
		frmAddWorkingDays.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "NoOfWorkingDays", "MHrs", "MMin", "THrs", "Tmin", "WHrs", "WMin", "ThHrs", "ThMin", "FHrs", "FMin", "SHrs", "SMin", "SuHrs", "SuMin"
			}
		));		
		scrollPane.setViewportView(table);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(new Color(255, 69, 0));
		btnClear.setBounds(324, 616, 97, 43);
		frmAddWorkingDays.getContentPane().add(btnClear);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DashBoard.main(new String[]{});
				frmAddWorkingDays.dispose();
			}
		});
		btnBack.setBackground(Color.GREEN);
		btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(67, 616, 129, 43);
		frmAddWorkingDays.getContentPane().add(btnBack);
		
		//Validate only Strings can add to the text field
		txtLname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				char c = arg0.getKeyChar();
				
				if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c) || c == KeyEvent.VK_PERIOD) {
					txtLname.setEditable(true);
				}else {
					txtLname.setEditable(false);
				}
			}
		});
		
		//Check no of working days are in range
		spnDays.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int wDays = (int) spnDays.getValue();
				if(wDays < 1 || wDays > 7) {
					btnInsert.setEnabled(false);
				}
				else {
					btnInsert.setEnabled(true);
				}
			}
		});
		
		//Check Monday check box is selected or not
		chckbxMonday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JCheckBox cb = (JCheckBox) arg0.getSource();
				if(cb.isSelected()) {
					mHrs.setEnabled(true);
					mMin.setEnabled(true);
//					chkmonday = true;
				}
				else {
					mHrs.setEnabled(false);
					mMin.setEnabled(false);	
				}
			}
		});
		
		//Check Tuesday check box is selected or not
		chckbxTuesday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					tHrs.setEnabled(true);
					tMin.setEnabled(true);
				}
				else {
					tHrs.setEnabled(false);
					tMin.setEnabled(false);
				}
			}
		});
		
		//Check Wednesday check box is selected or not
		chckbxWednesday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					wHrs.setEnabled(true);
					wMin.setEnabled(true);
				}
				else {
					wHrs.setEnabled(false);
					wMin.setEnabled(false);
				}
			}
		});
		
		//Check Thursday check box is selected or not
		chckbxThursday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					thHrs.setEnabled(true);
					thMin.setEnabled(true);
				}
				else {
					thHrs.setEnabled(false);
					thMin.setEnabled(false);
				}
			}
		});
		
		//Check Friday check box is selected or not
		chckbxFriday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					fHrs.setEnabled(true);
					fMin.setEnabled(true);
				}
				else {
					fHrs.setEnabled(false);
					fMin.setEnabled(false);
				}
			}
		});
		
		//Check Saturday check box is selected or not
		chckbxSaturday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					sHrs.setEnabled(true);
					sMin.setEnabled(true);
				}
				else {
					sHrs.setEnabled(false);
					sMin.setEnabled(false);
				}
			}
		});
		
		//Check Sunday check box is selected or not
		chckbxSunday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBox cb = (JCheckBox) e.getSource();
				if(cb.isSelected()) {
					suHrs.setEnabled(true);
					suMin.setEnabled(true);
				}
				else {
					suHrs.setEnabled(false);
					suMin.setEnabled(false);
				}
			}
		});
		
		//Insert button
		btnInsert.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				if(txtEid.getText().length() == 0 || txtLname.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Employee ID cannot be empty");
					System.out.println("Employee ID cannot be empty");
				} else {
					try {
						Connection conn = DB.getConnection();
						String Insertsql = "INSERT INTO lecturers1 (ID, Name, NoWorkingDays, Mhrs, Mmin, Thrs, Tmin, Whrs, Wmin, Thhrs, Thmin, Fhrs, Fmin, Shrs, Smin, Suhrs, Sumin) "
								+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						
						PreparedStatement ps = conn.prepareStatement(Insertsql);
						ps.setString(1, txtEid.getText());
						ps.setString(2, txtLname.getText());
						ps.setInt(3, (int) spnDays.getValue());
						ps.setInt(4, (int) mHrs.getValue());
						ps.setInt(5, (int) mMin.getValue());
						ps.setInt(6, (int) tHrs.getValue());
						ps.setInt(7, (int) tMin.getValue());
						ps.setInt(8, (int) wHrs.getValue());
						ps.setInt(9, (int) wMin.getValue());
						ps.setInt(10, (int) thHrs.getValue());
						ps.setInt(11, (int) thMin.getValue());
						ps.setInt(12, (int) fHrs.getValue());
						ps.setInt(13, (int) fMin.getValue());
						ps.setInt(14, (int) sHrs.getValue());
						ps.setInt(15, (int) sMin.getValue());
						ps.setInt(16, (int) suHrs.getValue());
						ps.setInt(17, (int) suMin.getValue());

						int rows = ps.executeUpdate();
						
						if(rows>0) {
							JOptionPane.showMessageDialog(null, "Successfully Added!");
//							conn.commit();
						}
						
						DbValTable();
						
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						System.err.println("Error Occured : " + e.getMessage());
						JOptionPane.showMessageDialog(null, "ID is already exist in Database");
					}
					catch (Exception e) {
						System.err.println("Error Occured" + e.getMessage());
					}
				}
				
				
			}
		});
		
		//Get value from table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TableModel model = table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				
				txtEid.setText(model.getValueAt(selectedRowIndex, 0).toString());
				txtLname.setText(model.getValueAt(selectedRowIndex, 1).toString());
				spnDays.setValue(model.getValueAt(selectedRowIndex, 2));
				mHrs.setValue(model.getValueAt(selectedRowIndex, 3));
				mMin.setValue(model.getValueAt(selectedRowIndex, 4));
				tHrs.setValue(model.getValueAt(selectedRowIndex, 5));
				tMin.setValue(model.getValueAt(selectedRowIndex, 6));
				wHrs.setValue(model.getValueAt(selectedRowIndex, 7));
				wMin.setValue(model.getValueAt(selectedRowIndex, 8));
				thHrs.setValue(model.getValueAt(selectedRowIndex, 9));
				thMin.setValue(model.getValueAt(selectedRowIndex, 10));
				fHrs.setValue(model.getValueAt(selectedRowIndex, 11));
				fMin.setValue(model.getValueAt(selectedRowIndex, 12));
				sHrs.setValue(model.getValueAt(selectedRowIndex, 13));
				sMin.setValue(model.getValueAt(selectedRowIndex, 14));
				suHrs.setValue(model.getValueAt(selectedRowIndex, 15));
				suMin.setValue(model.getValueAt(selectedRowIndex, 16));
				
				if((int)mHrs.getValue() > 0 || (int)mMin.getValue() > 0) {
					chckbxMonday.setSelected(true);
					mHrs.setEnabled(true);
					mMin.setEnabled(true);
				}else {
					chckbxMonday.setSelected(false);
					mHrs.setEnabled(false);
					mMin.setEnabled(false);
				}
				if((int)tHrs.getValue() > 0 || (int)tMin.getValue() > 0) {
					chckbxTuesday.setSelected(true);
					tHrs.setEnabled(true);
					tMin.setEnabled(true);
				}else {
					chckbxTuesday.setSelected(false);
					tHrs.setEnabled(false);
					tMin.setEnabled(false);
				}
				if((int)wHrs.getValue() > 0 || (int)wMin.getValue() > 0) {
					chckbxWednesday.setSelected(true);
					wHrs.setEnabled(true);
					wMin.setEnabled(true);
				}else {
					chckbxWednesday.setSelected(false);
					wHrs.setEnabled(false);
					wMin.setEnabled(false);
				}
				if((int)thHrs.getValue() > 0 || (int)thMin.getValue() > 0) {
					chckbxThursday.setSelected(true);
					thHrs.setEnabled(true);
					thMin.setEnabled(true);
				}else {
					chckbxThursday.setSelected(false);
					thHrs.setEnabled(false);
					thMin.setEnabled(false);
				}
				if((int)fHrs.getValue() > 0 || (int)fMin.getValue() > 0) {
					chckbxFriday.setSelected(true);
					fHrs.setEnabled(true);
					fMin.setEnabled(true);
				}else {
					chckbxFriday.setSelected(false);
					fHrs.setEnabled(false);
					fMin.setEnabled(false);
				}
				if((int)sHrs.getValue() > 0 || (int)sMin.getValue() > 0) {
					chckbxSaturday.setSelected(true);
					sHrs.setEnabled(true);
					sMin.setEnabled(true);
				}else {
					chckbxSaturday.setSelected(false);
					sHrs.setEnabled(false);
					sMin.setEnabled(false);
				}
				if((int)suHrs.getValue() > 0 || (int)suMin.getValue() > 0) {
					chckbxSunday.setSelected(true);
					suHrs.setEnabled(true);
					suMin.setEnabled(true);
				}else {
					chckbxSunday.setSelected(false);
					suHrs.setEnabled(false);
					suMin.setEnabled(false);
				}
			}
		});
		
		//Delete button
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = DB.getConnection();
					String sql = "DELETE FROM lecturers1 WHERE ID = ?";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, txtEid.getText());
					int response = JOptionPane.showConfirmDialog(null, "Are you sure?","Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						statement.executeUpdate();
//						conn.commit();
						System.out.println("Successfully Deleted");
						
						DbValTable();
					}else {
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		//Update button
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection conn = DB.getConnection();
					String ID = txtEid.getText().toString();
					String updateSql = "UPDATE lecturers SET Name=?, NoWorkingDays=?, Mhrs=?, Mmin=?, Thrs=?, Tmin=?, Whrs=?, Wmin=?, Thhrs=?, Thmin=?, Fhrs=?, Fmin=?, Shrs=?, Smin=?, Suhrs=?, Sumin=? WHERE ID =?";
					PreparedStatement ps = conn.prepareStatement(updateSql);
					ps.setString(1, txtLname.getText().toString());
					ps.setInt(2, (int) spnDays.getValue());
					ps.setInt(3, (int) mHrs.getValue());
					ps.setInt(4, (int) mMin.getValue());
					ps.setInt(5, (int) tHrs.getValue());
					ps.setInt(6, (int) tMin.getValue());
					ps.setInt(7, (int) wHrs.getValue());
					ps.setInt(8, (int) wMin.getValue());
					ps.setInt(9, (int) thHrs.getValue());
					ps.setInt(10, (int) thMin.getValue());
					ps.setInt(11, (int) fHrs.getValue());
					ps.setInt(12, (int) fMin.getValue());
					ps.setInt(13, (int) sHrs.getValue());
					ps.setInt(14, (int) sMin.getValue());
					ps.setInt(15, (int) suHrs.getValue());
					ps.setInt(16, (int) suMin.getValue());
					ps.setString(17, txtEid.getText().toString());
					
					ps.executeUpdate();
//					conn.commit();
//					System.out.println("Successfully Updated!");
					JOptionPane.showMessageDialog(null, "Successfully Updated!");
					DbValTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		frmAddWorkingDays.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				DbValTable();
			}
		});
		
		//Clear Button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtEid.setText("");
				txtLname.setText("");
				spnDays.setValue(0);
				mHrs.setValue(0);
				mMin.setValue(0);
				tHrs.setValue(0);
				tMin.setValue(0);
				wHrs.setValue(0);
				wMin.setValue(0);
				thHrs.setValue(0);
				thMin.setValue(0);
				fHrs.setValue(0);
				fMin.setValue(0);
				sHrs.setValue(0);
				sMin.setValue(0);
				suHrs.setValue(0);
				suMin.setValue(0);
				
				chckbxMonday.setSelected(false);
				mHrs.setEnabled(false);
				mMin.setEnabled(false);
				chckbxTuesday.setSelected(false);
				tHrs.setEnabled(false);
				tMin.setEnabled(false);
				chckbxWednesday.setSelected(false);
				wHrs.setEnabled(false);
				wMin.setEnabled(false);
				chckbxThursday.setSelected(false);
				thHrs.setEnabled(false);
				thMin.setEnabled(false);
				chckbxFriday.setSelected(false);
				fHrs.setEnabled(false);
				fMin.setEnabled(false);
				chckbxSaturday.setSelected(false);
				sHrs.setEnabled(false);
				sMin.setEnabled(false);
				chckbxSunday.setSelected(false);
				suHrs.setEnabled(false);
				suMin.setEnabled(false);
			}
		});
	}
}
