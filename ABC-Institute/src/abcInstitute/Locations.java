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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Locations {

	private JFrame frame;
	private JPanel contentPane;
	private JTable tabelMngLocations;
	private ButtonGroup rdgrpMngRoomType;
	private ButtonGroup rdgrpRoomType;
	private JTextField textFieldBuildingName;
	private JTextField textFieldRoomName;
	private JTextField textFieldRoomCapacity;
	private JTextField textFieldMngBuildingName;
	private JTextField textFieldMngRoomName;
	private JTextField textFieldMngRoomCapacity;
	private String RoomName =  "";
	private String BuildingName =  "";
	private String RoomType =  "";
	private String RoomCapacity =  "";
	private int crud,lid;
	String[] columns = {"ID","Building","Room","Room Type","Capacity"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locations window = new Locations();
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
	public Locations() {
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
		tabbedPane.setBounds(10, 24, 1330, 694);
		tabbedPane.setBackground(new Color(255, 255, 255));
		contentPane.add(tabbedPane);
		
		JPanel tabAddLocations = new JPanel();
		tabAddLocations.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Add Locations", null, tabAddLocations, null);
		tabAddLocations.setLayout(null);
		
		JLabel lblBuildingName = new JLabel("Building Name");
		lblBuildingName.setBounds(471, 115, 208, 19);
		lblBuildingName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLocations.add(lblBuildingName);
		
		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(471, 170, 174, 19);
		lblRoomName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLocations.add(lblRoomName);
		
		JLabel lblRoomType = new JLabel("Room Type");
		lblRoomType.setBounds(471, 250, 134, 19);
		lblRoomType.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLocations.add(lblRoomType);
		
		JLabel lblRoomCapacity = new JLabel("Room Capacity");
		lblRoomCapacity.setBounds(471, 332, 151, 24);
		lblRoomCapacity.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddLocations.add(lblRoomCapacity);
		
		textFieldBuildingName = new JTextField();
		textFieldBuildingName.setBounds(730, 110, 151, 32);
		textFieldBuildingName.setBackground(new Color(255, 255, 255));
		textFieldBuildingName.setFont(new Font("Arial", Font.BOLD, 14));
		tabAddLocations.add(textFieldBuildingName);
		textFieldBuildingName.setColumns(10);
		
		textFieldRoomName = new JTextField();
		textFieldRoomName.setBounds(730, 161, 151, 32);
		textFieldRoomName.setBackground(new Color(255, 255, 255));
		textFieldRoomName.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldRoomName.setColumns(10);
		tabAddLocations.add(textFieldRoomName);
		
		textFieldRoomCapacity = new JTextField();
		textFieldRoomCapacity.setBounds(730, 326, 151, 32);
		textFieldRoomCapacity.setBackground(new Color(255, 255, 255));
		textFieldRoomCapacity.setFont(new Font("Arial", Font.BOLD, 14));
		tabAddLocations.add(textFieldRoomCapacity);
		textFieldRoomCapacity.setColumns(10);
		
		JRadioButton rdbtnRoomtype1= new JRadioButton("Lecture Hall");
		rdbtnRoomtype1.setBounds(730, 224, 109, 23);
		rdbtnRoomtype1.setBackground(new Color(255, 255, 255));
		rdbtnRoomtype1.setFont(new Font("Arial", Font.BOLD, 13));
		tabAddLocations.add(rdbtnRoomtype1);
		
		JRadioButton rdbtnRoomtype2 = new JRadioButton("Laboratary");
		rdbtnRoomtype2.setBounds(730, 250, 109, 23);
		rdbtnRoomtype2.setBackground(new Color(255, 255, 255));
		rdbtnRoomtype2.setFont(new Font("Arial", Font.BOLD, 13));
		tabAddLocations.add(rdbtnRoomtype2);
		
		rdgrpRoomType =new ButtonGroup();    
		rdgrpRoomType.add(rdbtnRoomtype1);
		rdgrpRoomType.add(rdbtnRoomtype2); 
		
		JButton btnTagsClear = new JButton("Clear");
		btnTagsClear.setBounds(471, 579, 158, 47);
		btnTagsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnTagsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTagsClear.setBackground(new Color(255, 140, 0));
		tabAddLocations.add(btnTagsClear);
		
		JButton btnTagsSave = new JButton("Save");
		btnTagsSave.setBounds(723, 579, 158, 47);
		btnTagsSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomName =  textFieldRoomName.getText();
				BuildingName =  textFieldBuildingName.getText();
				RoomCapacity =  textFieldRoomCapacity.getText();
				if(rdbtnRoomtype1.isSelected()) {
					RoomType = "Lecture Hall";
				}else if(rdbtnRoomtype2.isSelected()) {
					RoomType = "Laboratary";
				}
				crud = 1;
				lid = 0;
				ValidateInputs(BuildingName,RoomName,RoomType,RoomCapacity,crud,lid);
			}

		});
		btnTagsSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTagsSave.setBackground(new Color(0, 191, 255));
		tabAddLocations.add(btnTagsSave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(1094, 569, 158, 57);
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		tabAddLocations.add(btnNewButton);
		
		JPanel tabManageLocations = new JPanel();
		tabManageLocations.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Manage Locations", null, tabManageLocations, null);
		tabManageLocations.setLayout(null);
		
		JLabel lblMngaBuilduinName = new JLabel("Building Name");
		lblMngaBuilduinName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngaBuilduinName.setBounds(161, 420, 204, 19);
		lblMngaBuilduinName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLocations.add(lblMngaBuilduinName);
		
		JLabel lblMngRoomName = new JLabel("Room Name");
		lblMngRoomName.setBounds(162, 490, 89, 19);
		lblMngRoomName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLocations.add(lblMngRoomName);
		
		JLabel lblMngRoomType = new JLabel("Room Type");
		lblMngRoomType.setBounds(617, 419, 134, 19);
		lblMngRoomType.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLocations.add(lblMngRoomType);
		
		JLabel lblMngRoomCapacity = new JLabel("Room Capacity");
		lblMngRoomCapacity.setBounds(617, 487, 151, 24);
		lblMngRoomCapacity.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageLocations.add(lblMngRoomCapacity);
		
		textFieldMngBuildingName = new JTextField();
		textFieldMngBuildingName.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldMngBuildingName.setBounds(323, 421, 151, 20);
		tabManageLocations.add(textFieldMngBuildingName);
		textFieldMngBuildingName.setColumns(10);
		
		textFieldMngRoomName = new JTextField();
		textFieldMngRoomName.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldMngRoomName.setColumns(10);
		textFieldMngRoomName.setBounds(323, 491, 151, 20);
		tabManageLocations.add(textFieldMngRoomName);
		
		textFieldMngRoomCapacity = new JTextField();
		textFieldMngRoomCapacity.setBackground(new Color(255, 255, 255));
		textFieldMngRoomCapacity.setFont(new Font("Arial", Font.BOLD, 13));
		textFieldMngRoomCapacity.setBounds(778, 491, 151, 20);
		tabManageLocations.add(textFieldMngRoomCapacity);
		textFieldMngRoomCapacity.setColumns(10);
		
		JRadioButton rdbtnMngRoomtype1= new JRadioButton("Lecture Hall");
		rdbtnMngRoomtype1.setBackground(new Color(255, 255, 255));
		rdbtnMngRoomtype1.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnMngRoomtype1.setBounds(757, 420, 109, 23);
		tabManageLocations.add(rdbtnMngRoomtype1);
		
		JRadioButton rdbtnMngRoomtype2 = new JRadioButton("Laboratary");
		rdbtnMngRoomtype2.setBackground(new Color(255, 255, 255));
		rdbtnMngRoomtype2.setFont(new Font("Arial", Font.BOLD, 13));
		rdbtnMngRoomtype2.setBounds(868, 420, 109, 23);
		tabManageLocations.add(rdbtnMngRoomtype2);
		
		rdgrpMngRoomType =new ButtonGroup();    
		rdgrpMngRoomType.add(rdbtnMngRoomtype1);
		rdgrpMngRoomType.add(rdbtnMngRoomtype2); 
		
		DbValTable();
		tabelMngLocations.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				textFieldMngBuildingName.setText(tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 1).toString());
				textFieldMngRoomName.setText(tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 2).toString());
				RoomType = tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 3).toString();
	        	if(RoomType.equals("Lecture Hall")) {
	        		rdbtnMngRoomtype1.setSelected(true);
	        	}else if(RoomType.equals("Laboratary")) {
	        		rdbtnMngRoomtype2.setSelected(true);
	        	}
				textFieldMngRoomCapacity.setText(tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 4).toString());
	        	
	        	
	        }
	    });
		
		JPanel panelMngLocations = new JPanel();
		panelMngLocations.setBounds(78, 11, 1165, 372);
		tabManageLocations .add(panelMngLocations);
		panelMngLocations.setLayout(new BorderLayout(0, 0));
		
		JScrollPane paneScrlMngLocations = new JScrollPane(tabelMngLocations);
		panelMngLocations.add(paneScrlMngLocations);
		
		JButton btnMngLocationsClear = new JButton("Clear");
		btnMngLocationsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdgrpMngRoomType.clearSelection();
				textFieldMngRoomName.setText(null);
				textFieldMngRoomCapacity.setText(null);
				textFieldMngBuildingName.setText(null);
			}
		});
		btnMngLocationsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLocationsClear.setBackground(new Color(255, 140, 0));
		btnMngLocationsClear.setBounds(268, 603, 158, 47);
		tabManageLocations.add(btnMngLocationsClear);
		
		JButton btnMngLocationsBack = new JButton("Back");
		btnMngLocationsBack.setBackground(new Color(32, 178, 170));
		btnMngLocationsBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLocationsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnMngLocationsBack.setBounds(1104, 597, 158, 57);
		tabManageLocations.add(btnMngLocationsBack);
		
		JButton btnMngLocationsDelete = new JButton("Delete");
		btnMngLocationsDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lid = Integer.parseInt(tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 0).toString());
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM locations WHERE lid = ?");
					ps.setInt(1,lid);
					ps.execute();

					showMessageDialog(null, "Item deleted !");
					UpdateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMngLocationsDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLocationsDelete.setBackground(new Color(255, 69, 0));
		btnMngLocationsDelete.setBounds(797, 603, 158, 47);
		tabManageLocations.add(btnMngLocationsDelete);
		
		JButton btnMngLocationsUpdate = new JButton("Update");
		btnMngLocationsUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomName =  textFieldMngRoomName.getText();
				BuildingName =  textFieldMngBuildingName.getText();
				RoomCapacity =  textFieldMngRoomCapacity.getText();
				if(rdbtnMngRoomtype1.isSelected()) {
					RoomType = "Lecture Hall";
				}else if(rdbtnMngRoomtype2.isSelected()) {
					RoomType = "Laboratary";
				}
				crud = 0;
				lid = Integer.parseInt(tabelMngLocations.getModel().getValueAt(tabelMngLocations.getSelectedRow(), 0).toString());
				ValidateInputs(BuildingName,RoomName,RoomType,RoomCapacity,crud,lid);
				
			}
		});
		btnMngLocationsUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngLocationsUpdate.setBackground(new Color(50, 205, 50));
		btnMngLocationsUpdate.setBounds(532, 603, 158, 47);
		tabManageLocations.add(btnMngLocationsUpdate);
		
	}
	public void MngClear() {
		rdgrpMngRoomType.clearSelection();
		textFieldMngRoomName.setText(null);
		textFieldMngRoomCapacity.setText(null);
		textFieldMngBuildingName.setText(null);
	}
	public void Clear() {
		rdgrpRoomType.clearSelection();
		textFieldRoomName.setText(null);
		textFieldRoomCapacity.setText(null);
		textFieldBuildingName.setText(null);
	}
	public void ValidateInputs(String BuildingName, String RoomName, String RoomType, String RoomCapacity, int crud, int tid) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.RoomName = RoomName;
			this.BuildingName =  BuildingName;
			this.RoomType = RoomType;
			this.RoomCapacity = RoomCapacity;
//			this.lid = lid;
			
			if(RoomName.isEmpty() || BuildingName.isEmpty() || RoomType.isEmpty() || RoomCapacity.isEmpty() ) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else if(crud == 0) {
					PreparedStatement  ps = con.prepareStatement("UPDATE locations SET  BuildingName = ?, RoomName = ?, RoomType = ? , RoomCapacity = ?  WHERE lid = ?");

					ps.setString(1,BuildingName);
					ps.setString(2,RoomName);
					ps.setString(3,RoomType);
					ps.setString(4,RoomCapacity);
					ps.setInt(5,lid);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
					MngClear();
				}else if(crud == 1){
					query.execute("insert into locations (BuildingName, RoomName, RoomType, RoomCapacity) values('"+BuildingName+"','"+RoomName+"','"+RoomType+"','"+RoomCapacity+"')");
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
			PreparedStatement ps=con.prepareStatement("select * from locations",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			tabelMngLocations = new JTable(model);
			tabelMngLocations.setBounds(161, 11, 1165, 372);
			tabelMngLocations.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from locations",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		tabelMngLocations.setModel(model);
		tabelMngLocations.repaint();
	}

}
