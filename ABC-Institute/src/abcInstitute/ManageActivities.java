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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ManageActivities {

	private JFrame frame;
	private JPanel contentPane;
	private JPanel tabConsecutive;
	private JTable tabelMngActivities;
	private JTable tabelMngOverlap;
	private JTable tabelMngPararrel;
	private JLabel lblSlctSe1;
	private JLabel lblHdnVal;
	private int ssids;
	private JLabel lblSlctSe2;
	private String x1,x2,x3;
//	private String x1,x2,x3,x11,x22;
	private String[] columns = {"ID","Lecturer","Lecturer(2)","Subject Name","Subject Code","Group ID","Tag","No Of Students","Duration"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageActivities window = new ManageActivities();
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
	public ManageActivities() {
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
		
		tabConsecutive = new JPanel();
		tabConsecutive.setBounds(161, 11, 1165, 372);
		tabbedPane.addTab("Consecutive", null, tabConsecutive, null);
		
		DbValTable();
		tabelMngActivities.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				//lblSlctSe1.setText(tabelMngActivities.getModel().getValueAt(tabelMngActivities.getSelectedRow(), 1).toString());
				int[] selectedrows = tabelMngActivities.getSelectedRows();
				lblHdnVal.setText(tabelMngActivities.getModel().getValueAt(selectedrows[0], 0).toString());
				x1 = tabelMngActivities.getModel().getValueAt(tabelMngActivities.getSelectedRow(), 3).toString();
				System.out.println(selectedrows[0]);
		    }
		});
		
		tabConsecutive.setLayout(null);
		
		JScrollPane paneScrlConsecutive  = new JScrollPane(tabelMngActivities);
		paneScrlConsecutive.setBounds(67, 29, 1198, 360);
		tabConsecutive.setLayout(null);
		tabConsecutive .add(paneScrlConsecutive);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x1 = lblSlctSe1.getText();
				x2 = lblSlctSe2.getText();
				ConsectiveSessions(x1,x2);
		          
			}
			public void ConsectiveSessions(String x1,String x2){
				Connection con=DB.getConnection();
				try {
					if(x1.equals(x2)) {
						showMessageDialog(null, "Same Sessions cannot be Consecutive Sessions", "Error", ERROR_MESSAGE);
					}else{
						PreparedStatement ps1 =con.prepareStatement("SELECT   COUNT(mid)  FROM manageactivities WHERE sessionId1 = ? OR sessionId2 = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ps1.setString(1, x1);
						ps1.setString(2, x2);
						ResultSet rs1 =ps1.executeQuery();
						if(rs1.next()) {
							ssids = rs1.getInt(1);
							System.out.println(ssids);
							
						}
						if(ssids == 0) {
							Statement query = con.createStatement();
							query.execute("insert into manageactivities (sessionId1,sessionId2,type) values('"+x1+"' , '"+x2+"' , 'Consecutive')");
							showMessageDialog(null, "Successfully added!");
						}else {
							showMessageDialog(null, "This Sessions Already Added", "Error", ERROR_MESSAGE);
						}
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			
		});
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdd.setBackground(new Color(46, 139, 87));
		btnAdd.setBounds(1115, 597, 150, 57);
		tabConsecutive.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("Consecutive Sessions");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBounds(67, 425, 204, 35);
		tabConsecutive.add(lblNewLabel);
		
		lblSlctSe1 = new JLabel("0");
		lblSlctSe1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe1.setBounds(305, 425, 37, 27);
		tabConsecutive.add(lblSlctSe1);
		
		JLabel lblNewLabel_2 = new JLabel("&");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(338, 425, 22, 27);
		tabConsecutive.add(lblNewLabel_2);
		
		lblSlctSe2 = new JLabel("0");
		lblSlctSe2.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe2.setBounds(370, 425, 37, 27);
		tabConsecutive.add(lblSlctSe2);
		
		JButton btnSelectSession2 = new JButton("Select Session 2");
		btnSelectSession2.setBackground(new Color(70, 130, 180));
		btnSelectSession2.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe2.setText(lblHdnVal.getText());
			}
		});
		btnSelectSession2.setBounds(1115, 425, 150, 35);
		tabConsecutive.add(btnSelectSession2);
		
		lblHdnVal = new JLabel("5");
		lblHdnVal.setFont(new Font("Arial", Font.BOLD, 25));
		lblHdnVal.setBounds(736, 425, 46, 35);
		tabConsecutive.add(lblHdnVal);
		
		JButton btnSelectSession1 = new JButton("Select Session 1");
		btnSelectSession1.setBackground(new Color(70, 130, 180));
		btnSelectSession1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe1.setText(lblHdnVal.getText());
			}
		});
		btnSelectSession1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession1.setBounds(888, 425, 150, 35);
		tabConsecutive.add(btnSelectSession1);
		
		JLabel lblNewLabel_1 = new JLabel("Selected Session  ID");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(508, 425, 192, 30);
		tabConsecutive.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setBounds(67, 597, 158, 57);
		tabConsecutive.add(btnNewButton);
		
		JPanel tabParallel = new JPanel();
		tabbedPane.addTab("Parallel", null, tabParallel, null);
		tabParallel.setBounds(96, 53, 1198, 490);
		
		tabParallel.setLayout(null);
		JScrollPane paneScrlParallel = new JScrollPane(tabelMngPararrel);
		paneScrlParallel.setBounds(67, 29, 1198, 360);
		tabParallel.add(paneScrlParallel);
		
		JLabel lblParallelSessions = new JLabel("Parallel Sessions");
		lblParallelSessions.setFont(new Font("Arial", Font.PLAIN, 20));
		lblParallelSessions.setBounds(67, 425, 204, 35);
		tabParallel.add(lblParallelSessions);
		
		JLabel lblSlctSe1_1 = new JLabel("0");
		lblSlctSe1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe1_1.setBounds(305, 425, 27, 27);
		tabParallel.add(lblSlctSe1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("&");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(332, 425, 22, 27);
		tabParallel.add(lblNewLabel_2_1);
		
		JLabel lblSlctSe2_1 = new JLabel("0");
		lblSlctSe2_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe2_1.setBounds(360, 425, 37, 27);
		tabParallel.add(lblSlctSe2_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Selected Session  ID");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(508, 425, 192, 30);
		tabParallel.add(lblNewLabel_1_1);
		
		JLabel lblHdnVal_1 = new JLabel("5");
		lblHdnVal_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblHdnVal_1.setBounds(745, 425, 46, 35);
		tabParallel.add(lblHdnVal_1);
		
		tabelMngPararrel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				//lblSlctSe1.setText(tabelMngActivities.getModel().getValueAt(tabelMngActivities.getSelectedRow(), 1).toString());
				int[] selectedrows = tabelMngPararrel.getSelectedRows();
				lblHdnVal_1.setText(tabelMngPararrel.getModel().getValueAt(selectedrows[0], 0).toString());
				x1 = tabelMngPararrel.getModel().getValueAt(tabelMngPararrel.getSelectedRow(), 3).toString();
				System.out.println(selectedrows[0]);
		    }
		});
		
		JButton btnSelectSession1_1 = new JButton("Select Session 1");
		btnSelectSession1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe1_1.setText(lblHdnVal_1.getText());
			}
		});
		btnSelectSession1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession1_1.setBackground(new Color(70, 130, 180));
		btnSelectSession1_1.setBounds(888, 425, 150, 35);
		tabParallel.add(btnSelectSession1_1);
		
		JButton btnSelectSession2_1 = new JButton("Select Session 2");
		btnSelectSession2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe2_1.setText(lblHdnVal_1.getText());
			}
		});
		btnSelectSession2_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession2_1.setBackground(new Color(70, 130, 180));
		btnSelectSession2_1.setBounds(1115, 425, 150, 35);
		tabParallel.add(btnSelectSession2_1);
		
		JButton btnAdd_1 = new JButton("ADD");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x1 = lblSlctSe1_1.getText();
				x2 = lblSlctSe2_1.getText();
				ParallelSessions(x1,x2);
		          
			}
			public void ParallelSessions(String x1,String x2){
				Connection con=DB.getConnection();
				try {
					if(x1.equals(x2)) {
						showMessageDialog(null, "Same Sessions cannot be Parallel Sessions", "Error", ERROR_MESSAGE);
					}else{
						PreparedStatement ps1 =con.prepareStatement("SELECT   COUNT(mid)  FROM manageactivities WHERE sessionId1 = ? OR sessionId2 = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ps1.setString(1, x1);
						ps1.setString(2, x2);
						ResultSet rs1 =ps1.executeQuery();
						if(rs1.next()) {
							ssids = rs1.getInt(1);
							System.out.println(ssids);
							
						}
						if(ssids == 0) {
							Statement query = con.createStatement();
							query.execute("insert into manageactivities (sessionId1,sessionId2,type) values('"+x1+"' , '"+x2+"' , 'Parallel')");
							showMessageDialog(null, "Successfully added!");
						}else {
							showMessageDialog(null, "This Sessions Already Added", "Error", ERROR_MESSAGE);
						}
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdd_1.setBackground(new Color(46, 139, 87));
		btnAdd_1.setBounds(1107, 568, 158, 47);
		tabParallel.add(btnAdd_1);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(32, 178, 170));
		btnNewButton_1.setBounds(67, 583, 158, 57);
		tabParallel.add(btnNewButton_1);
		
		
		
		JPanel tabOverLapping = new JPanel();
		tabbedPane.addTab("Non OverLapping", null, tabOverLapping, null);
		tabOverLapping.setBounds(161, 11, 1165, 372);
		tabOverLapping.setLayout(null);
		
		JScrollPane paneScrlParallel_1 = new JScrollPane(tabelMngOverlap);
		paneScrlParallel_1.setBounds(67, 29, 1198, 360);
		tabOverLapping.add(paneScrlParallel_1);
		
		JLabel lblNotOverlapSessions = new JLabel("Not overlap Sessions");
		lblNotOverlapSessions.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNotOverlapSessions.setBounds(67, 425, 204, 35);
		tabOverLapping.add(lblNotOverlapSessions);
		
		JLabel lblSlctSe1_1_1 = new JLabel("0");
		lblSlctSe1_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe1_1_1.setBounds(291, 425, 37, 27);
		tabOverLapping.add(lblSlctSe1_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("&");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2_1_1.setBounds(320, 425, 22, 27);
		tabOverLapping.add(lblNewLabel_2_1_1);
		
		JLabel lblSlctSe2_1_1 = new JLabel("0");
		lblSlctSe2_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe2_1_1.setBounds(352, 425, 37, 27);
		tabOverLapping.add(lblSlctSe2_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Selected Session  ID");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(67, 525, 192, 30);
		tabOverLapping.add(lblNewLabel_1_1_1);
		
		JLabel lblHdnVal_1_1 = new JLabel("5");
		lblHdnVal_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblHdnVal_1_1.setBounds(291, 522, 46, 35);
		tabOverLapping.add(lblHdnVal_1_1);
		
		JLabel lblSlctSe3_1_1 = new JLabel("0");
		lblSlctSe3_1_1.setFont(new Font("Arial", Font.BOLD, 25));
		lblSlctSe3_1_1.setBounds(407, 425, 37, 27);
		tabOverLapping.add(lblSlctSe3_1_1);
		
		tabelMngOverlap.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				//lblSlctSe1.setText(tabelMngActivities.getModel().getValueAt(tabelMngActivities.getSelectedRow(), 1).toString());
				int[] selectedrows = tabelMngOverlap.getSelectedRows();
				lblHdnVal_1_1.setText(tabelMngOverlap.getModel().getValueAt(selectedrows[0], 0).toString());
				x1 = tabelMngOverlap.getModel().getValueAt(tabelMngOverlap.getSelectedRow(), 3).toString();
				System.out.println(selectedrows[0]);
		    }
		});
		
		JButton btnSelectSession1_1_1 = new JButton("Select Session 1");
		btnSelectSession1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe1_1_1.setText(lblHdnVal_1_1.getText());
			}
		});
		btnSelectSession1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession1_1_1.setBackground(new Color(70, 130, 180));
		btnSelectSession1_1_1.setBounds(600, 425, 150, 35);
		tabOverLapping.add(btnSelectSession1_1_1);
		
		JButton btnSelectSession2_1_1 = new JButton("Select Session 2");
		btnSelectSession2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe2_1_1.setText(lblHdnVal_1_1.getText());
			}
		});
		btnSelectSession2_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession2_1_1.setBackground(new Color(70, 130, 180));
		btnSelectSession2_1_1.setBounds(869, 425, 150, 35);
		tabOverLapping.add(btnSelectSession2_1_1);
		
		JButton btnSelectSession3_1_1 = new JButton("Select Session 3");
		btnSelectSession3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSlctSe3_1_1.setText(lblHdnVal_1_1.getText());
			}
		});
		btnSelectSession3_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSelectSession3_1_1.setBackground(new Color(70, 130, 180));
		btnSelectSession3_1_1.setBounds(1115, 425, 150, 35);
		tabOverLapping.add(btnSelectSession3_1_1);
		
		JButton btnAdd_1_1 = new JButton("ADD");
		btnAdd_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x1 = lblSlctSe1_1_1.getText();
				x2 = lblSlctSe2_1_1.getText();
				x3 = lblSlctSe3_1_1.getText();
				ConsectiveSessions(x1,x2,x3);
		          
			}
			public void ConsectiveSessions(String x1,String x2,String x3){
				Connection con=DB.getConnection();
				try {
					if(x1.equals(x2)) {
						showMessageDialog(null, "Same Sessions cannot be Consecutive Sessions", "Error", ERROR_MESSAGE);
					}else{
						PreparedStatement ps1 =con.prepareStatement("SELECT   COUNT(mid)  FROM manageactivities WHERE sessionId1 = ? OR sessionId2 = ? ",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ps1.setString(1, x1);
						ps1.setString(2, x2);
						ResultSet rs1 =ps1.executeQuery();
						if(rs1.next()) {
							ssids = rs1.getInt(1);
							System.out.println(ssids);
							
						}
						if(ssids == 0) {
							Statement query = con.createStatement();
							query.execute("insert into manageactivities (sessionId1,sessionId2,sessionId3,type) values('"+x1+"' , '"+x2+"','"+x3+"' , 'Not OverLap')");
							showMessageDialog(null, "Successfully added!");
						}else {
							showMessageDialog(null, "This Sessions Already Added", "Error", ERROR_MESSAGE);
						}
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdd_1_1.setBackground(new Color(46, 139, 87));
		btnAdd_1_1.setBounds(1107, 568, 158, 47);
		tabOverLapping.add(btnAdd_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("&");
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_2_1_1_1.setBounds(381, 425, 22, 27);
		tabOverLapping.add(lblNewLabel_2_1_1_1);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_2.setBackground(new Color(32, 178, 170));
		btnNewButton_2.setBounds(67, 611, 158, 57);
		tabOverLapping.add(btnNewButton_2);
		
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
		
			tabelMngActivities = new JTable(model);
			tabelMngActivities.setBounds(161, 11, 1165, 372);
			tabelMngActivities.setRowHeight(30);
			
			tabelMngPararrel = new JTable(model);
			tabelMngPararrel.setBounds(161, 11, 1165, 372);
			tabelMngPararrel.setRowHeight(30);
			
			tabelMngOverlap = new JTable(model);
			tabelMngOverlap.setBounds(161, 11, 1165, 372);
			tabelMngOverlap.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
	}

}
