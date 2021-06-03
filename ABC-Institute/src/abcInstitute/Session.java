package abcInstitute;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Session {

	private JFrame frame;
	private JPanel contentPane;
	private JTable tabelMngSessions;
	private JTextField textFieldNoOfStd;
	private JComboBox comboBoxSlctLec;
	private JComboBox comboBoxSlctTag;
	private JComboBox comboBoxSlctGrp;
	private JComboBox comboBoxSlctSubGrp;
	private JComboBox comboBoxSlctSbj;
	private JComboBox comboBoxSlctLec2;
	private JComboBox comboBoxSearch;
	String[] columns = {"ID","Lecturer","Lecturer","Subject Name","Subject Code","Group ID","Tag","No Of Students","Duration"};
	String[] searchStrings = {"Lecturer","Year","Subject Code"};
	
	private String slctTag;
	private String sessionName;
	private String slctSbj;
	private String slctSbjCode;
	private String slctGrp;
	private String slctLec;
	private String slctLec2;
	private String slctDur;
	private String slctNoStd;
	private String searchVal;
	private String searchBox;
	private int crud,ssid;
	private JTextField textFieldSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session window = new Session();
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
	public Session() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0,0,1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1330, 707);
		tabbedPane.setBackground(new Color(255, 255, 255));
		contentPane.add(tabbedPane);
		
		JPanel tabAddSessions = new JPanel();
		tabAddSessions.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Add Sessions", null, tabAddSessions, null);
		tabAddSessions.setLayout(null);
		
		JLabel lblSlctLecurer= new JLabel("Select Lecturer");
		lblSlctLecurer.setBounds(457, 71, 208, 19);
		lblSlctLecurer.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblSlctLecurer);
		
		JLabel lblSlctTag = new JLabel("Select Tag");
		lblSlctTag.setBounds(457, 189, 174, 19);
		lblSlctTag.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblSlctTag);
		
		JLabel lblSlctGroup = new JLabel("Select Group");
		lblSlctGroup.setBounds(457, 262, 134, 19);
		lblSlctGroup.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblSlctGroup);		
		
		JLabel lblSlctSbj = new JLabel("Select Subject");
		lblSlctSbj.setBounds(457, 342, 151, 24);
		lblSlctSbj.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblSlctSbj);
		
		JLabel lblNoOfStd = new JLabel("No Of Students");
		lblNoOfStd.setBounds(457, 435, 134, 19);
		lblNoOfStd.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblNoOfStd);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(457, 516, 134, 19);
		lblDuration.setBackground(new Color(255, 255, 255));
		lblDuration.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblDuration);
		
		JSpinner spinnerDuration = new JSpinner();
		spinnerDuration.setBounds(740, 508, 202, 29);
		spinnerDuration.setFont(new Font("Arial", Font.BOLD, 14));
		spinnerDuration.setBackground(new Color(255, 255, 255));
		tabAddSessions.add(spinnerDuration);
		
		textFieldNoOfStd = new JTextField();
		textFieldNoOfStd.setBounds(740, 431, 202, 29);
		textFieldNoOfStd.setFont(new Font("Arial", Font.BOLD, 14));
		tabAddSessions.add(textFieldNoOfStd);
		textFieldNoOfStd.setColumns(10);
		
		comboBoxSlctLec = new JComboBox();
		comboBoxSlctLec.setBounds(740, 68, 202, 29);
		comboBoxSlctLec.setBackground(new Color(255, 255, 255));
		comboBoxSlctLec.setFont(new Font("Arial", Font.BOLD, 14));
		tabAddSessions.add(comboBoxSlctLec);
		
		comboBoxSlctLec2 = new JComboBox();
		comboBoxSlctLec2.setBounds(740, 128, 202, 29);
		comboBoxSlctLec2.addItem("none");
		comboBoxSlctLec2.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxSlctLec2.setBackground(Color.WHITE);
		tabAddSessions.add(comboBoxSlctLec2);
		
		
		comboBoxSlctGrp = new JComboBox();
		comboBoxSlctGrp.setBounds(740, 258, 202, 29);
		comboBoxSlctGrp.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxSlctGrp.setBackground(Color.WHITE);
		tabAddSessions.add(comboBoxSlctGrp);
		
		comboBoxSlctTag = new JComboBox();
		comboBoxSlctTag.setBounds(740, 185, 202, 29);
		comboBoxSlctTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tagVal = (String) comboBoxSlctTag.getSelectedItem();
				
				try {
					Connection con=DB.getConnection();
					if(tagVal.equals("Lecture") || tagVal.equals("Tutorial")) {
					
						comboBoxSlctGrp.removeAllItems();
						PreparedStatement ps4 = con.prepareStatement("SELECT grpId  FROM students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs4 =ps4.executeQuery();
						int count=0;
						while(rs4.next()) {
							comboBoxSlctGrp.addItem(rs4.getString(1));
							count++;
						}
					}else if(tagVal.equals("Practical")) {
						comboBoxSlctGrp.removeAllItems();
						PreparedStatement ps5 = con.prepareStatement("SELECT subGrpId  FROM students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs5 =ps5.executeQuery();
						int count=0;
						while(rs5.next()) {
							comboBoxSlctGrp.addItem(rs5.getString(1));
							count++;
						}
					}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		comboBoxSlctTag.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxSlctTag.setBackground(Color.WHITE);
		tabAddSessions.add(comboBoxSlctTag);
		
		comboBoxSlctSbj = new JComboBox();
		comboBoxSlctSbj.setBounds(740, 341, 202, 29);
		comboBoxSlctSbj.setFont(new Font("Arial", Font.BOLD, 14));
		comboBoxSlctSbj.setBackground(Color.WHITE);
		tabAddSessions.add(comboBoxSlctSbj);
		DbData();
		DbValTable();
		
		JButton btnSessionsClear = new JButton("Clear");
		btnSessionsClear.setBounds(457, 623, 158, 47);
		btnSessionsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSlctSbj.setSelectedIndex(0);
				comboBoxSlctTag.setSelectedIndex(0);
				comboBoxSlctGrp.setSelectedIndex(0);
				spinnerDuration.setValue(0);
				textFieldNoOfStd.setText(null);
			}
		});
		btnSessionsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSessionsClear.setBackground(new Color(255, 140, 0));
		tabAddSessions.add(btnSessionsClear);
		
		JButton btnSessionssSave = new JButton("Save");
		btnSessionssSave.setBounds(784, 623, 158, 47);
		btnSessionssSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slctTag = (String) comboBoxSlctTag.getSelectedItem();
				slctSbj = (String) comboBoxSlctSbj.getSelectedItem();				
				slctGrp = (String) comboBoxSlctGrp.getSelectedItem();
				slctLec =(String) comboBoxSlctLec.getSelectedItem();
				slctLec2 =(String) comboBoxSlctLec2.getSelectedItem();
				try {
					spinnerDuration.commitEdit();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				slctDur = spinnerDuration.getValue().toString();
				slctNoStd = textFieldNoOfStd.getText();
				crud = 1;
				ssid = 0;
				ValidateInputs(slctLec,slctLec2,slctTag,slctSbj,slctGrp,slctDur,slctNoStd,crud,ssid);
			}

		});
		btnSessionssSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSessionssSave.setBackground(new Color(0, 191, 255));
		tabAddSessions.add(btnSessionssSave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(1121, 616, 167, 54);
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		tabAddSessions.add(btnNewButton);
		
		JLabel lblSlctLecurer2 = new JLabel("Select Lecturer(Opt)");
		lblSlctLecurer2.setBounds(457, 128, 208, 19);
		lblSlctLecurer2.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddSessions.add(lblSlctLecurer2);
		
		JPanel tabManageSessions = new JPanel();
		tabManageSessions.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Manage Sessions", null, tabManageSessions, null);
		
		JButton btnMngBack = new JButton("Back");
		btnMngBack.setBounds(1086, 567, 158, 57);
		btnMngBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngBack.setBackground(new Color(32, 178, 170));
		btnMngBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		tabManageSessions.setLayout(null);
		tabManageSessions.add(btnMngBack);
		
		JPanel panelMngSessions = new JPanel();
		panelMngSessions.setBounds(79, 86, 1165, 425);
		tabManageSessions.add(panelMngSessions);
		panelMngSessions.setLayout(null);
		
		JScrollPane paneScrlMngSessions = new JScrollPane(tabelMngSessions);
		paneScrlMngSessions.setBounds(0, 0, 1165, 425);
		panelMngSessions.add(paneScrlMngSessions);
		
		JButton btnMngSessionDelete = new JButton("Delete");
		btnMngSessionDelete.setBounds(515, 572, 158, 47);
		btnMngSessionDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ssid = Integer.parseInt(tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 0).toString());
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM sessions WHERE ssid = ?");
					ps.setInt(1,ssid);
					ps.execute();
					showMessageDialog(null, "Item deleted !");
					UpdateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMngSessionDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngSessionDelete.setBackground(new Color(255, 69, 0));
		tabManageSessions.add(btnMngSessionDelete);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(650, 45, 222, 30);
		tabManageSessions.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBoxSearch = new JComboBox(searchStrings);
		comboBoxSearch.setBounds(932, 45, 149, 30);
		tabManageSessions.add(comboBoxSearch);
		
		JButton btnNewButtonSearch = new JButton("Search");
		btnNewButtonSearch.setBounds(1131, 45, 113, 33);
		btnNewButtonSearch.setBackground(new Color(60, 179, 113));
		btnNewButtonSearch.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchVal = textFieldSearch.getText();
				searchBox = (String) comboBoxSearch.getSelectedItem();
				try {
					searchTable(searchVal,searchBox);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		tabManageSessions.add(btnNewButtonSearch);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(79, 572, 158, 47);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRefresh.setBackground(new Color(46, 139, 87));
		tabManageSessions.add(btnRefresh);
		
		tabelMngSessions.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {

				slctLec = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 1).toString();
				slctLec2 = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 2).toString();
				slctSbj = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 3).toString();
				slctSbjCode  = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 4).toString();
				slctGrp =  tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 5).toString();
				slctTag = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 6).toString();
				slctNoStd =  tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 7).toString();
				slctDur = tabelMngSessions.getModel().getValueAt(tabelMngSessions.getSelectedRow(), 8).toString();
	        	
	        	
	        }
	    });
		JButton btnView = new JButton("View");
		btnView.setBounds(289, 572, 158, 47);
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sview(slctLec,slctSbj,slctSbjCode,slctGrp,slctTag,slctNoStd,slctDur);
			}
		});
		btnView.setFont(new Font("Arial", Font.PLAIN, 20));
		btnView.setBackground(new Color(46, 139, 87));
		tabManageSessions.add(btnView);
	}
	public void Sview(String slctLec,  String slctSbj, String slctSbjCode, String slctGrp, String slctTag, String slctNoStd,String slctDur) {
		showMessageDialog(null, slctLec+"-"+slctSbj+"-"+slctSbjCode+"-"+slctGrp+"-"+slctTag+"-"+slctNoStd+"-"+slctDur);
	}
	public void ValidateInputs(String slctLec, String slctLec2, String slctTag, String slctSbj, String slctGrp, String slctDur, String slctNoStd, int crud, int ssid) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.slctLec = slctLec;
			this.slctLec2 = slctLec2;
			this.slctTag =  slctTag;
			this.slctSbj =  slctSbj;
			this.slctGrp =  slctGrp;
			this.slctDur =  slctDur;
			this.slctNoStd = slctNoStd;
			this.ssid = ssid;
			
			if(slctLec.isEmpty() || slctTag.isEmpty() || slctSbj.isEmpty() || slctGrp.isEmpty() || slctDur.isEmpty() || slctNoStd.isEmpty() ) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else if(crud == 0) {
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
				}else if(crud == 1){
					PreparedStatement ps7 =con.prepareStatement("SELECT subjectCode FROM subjects WHERE subjectName = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps7.setString(1,slctSbj);
					ResultSet rs7 =ps7.executeQuery();
					if(rs7.next()) {
						slctSbjCode = rs7.getString(1);
						sessionName = slctLec +"-"+slctLec2+"-"+slctSbjCode+"-"+slctSbj+"-"+slctGrp+"-"+slctNoStd+"-"+slctDur;
					}
					
					query.execute("insert into sessions (lecturer, lecturer2, subjectName, subjectCode, grpId, tag, noOfStudents, duration,sessionName) values('"+slctLec+"' , '"+slctLec2+"','"+slctSbj+"','"+slctSbjCode+"','"+slctGrp+"','"+slctTag+"','"+slctNoStd+"','"+slctDur+"','"+sessionName+"')");
					showMessageDialog(null, "Successfully added!");
					UpdateTable();
					
					
				}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void DbData() {
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps1 =con.prepareStatement("SELECT lecturerName  FROM lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs1 =ps1.executeQuery();
			int count=0;
			while(rs1.next()) {
				comboBoxSlctLec.addItem(rs1.getString(1));
				count++;
			}
			
			PreparedStatement ps2 =con.prepareStatement("SELECT tagRelated  FROM tags",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 =ps2.executeQuery();
			count=0;
			while(rs2.next()) {
				comboBoxSlctTag.addItem(rs2.getString(1));
				count++;
			}
			PreparedStatement ps3 =con.prepareStatement("SELECT subjectName  FROM subjects",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs3 =ps3.executeQuery();
			count=0;
			while(rs3.next()) {
				comboBoxSlctSbj.addItem(rs3.getString(1));
				count++;
			}
			PreparedStatement ps4 =con.prepareStatement("SELECT lecturerName  FROM lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs4 =ps4.executeQuery();
			count=0;
			while(rs4.next()) {
				comboBoxSlctLec2.addItem(rs4.getString(1));
				count++;
			}
			
		}catch(Exception e){System.out.println(e);}
		
	}
	public void DbValTable() {
		String data[][]=null;
		String column[]=null;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			tabelMngSessions = new JTable(model);
			tabelMngSessions.setBounds(161, 11, 1165, 372);
			tabelMngSessions.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		tabelMngSessions.setModel(model);
		tabelMngSessions.repaint();
	}
	public void searchTable(String searchVal, String searchBox) throws SQLException {
		this.searchVal = searchVal;
		this.searchBox = searchBox;
		Connection con=DB.getConnection();
		PreparedStatement ps = null;
		if(searchBox.equals("lecturer")) {
			ps=con.prepareStatement("select * from sessions WHERE lecturer = ? OR lecturer = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, searchVal);
			ps.setString(2, searchVal);
		}else if(searchBox.equals("Year")) {
			if(searchVal.equals("1")) {
				ps=con.prepareStatement("select * from sessions WHERE grpId LIKE \"%$Y1%\" ESCAPE \"$\"",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else if(searchVal.equals("2")) {
				ps=con.prepareStatement("select * from sessions WHERE grpId LIKE \"%$Y2%\" ESCAPE \"$\"",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else if(searchVal.equals("3")) {
				ps=con.prepareStatement("select * from sessions WHERE grpId LIKE \"%$Y3%\" ESCAPE \"$\"",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else if(searchVal.equals("4")) {
				ps=con.prepareStatement("select * from sessions WHERE grpId LIKE \"%$Y4%\" ESCAPE \"$\"",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else {
				ps=con.prepareStatement("select * from sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			
		}else if(searchBox.equals("Subject Code")) {
			ps=con.prepareStatement("select * from sessions WHERE subjectCode = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, searchVal);
		}else {
			ps=con.prepareStatement("select * from sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
		
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
		tabelMngSessions.setModel(model);
		tabelMngSessions.repaint();
	}

}
