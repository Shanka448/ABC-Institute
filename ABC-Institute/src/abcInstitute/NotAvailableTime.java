package abcInstitute;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class NotAvailableTime extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotAvailableTime frame = new NotAvailableTime();
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
	public NotAvailableTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 1057, 448);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageNotAvailable = new JLabel("Manage Not Available Times");
		lblManageNotAvailable.setBounds(379, 13, 258, 24);
		lblManageNotAvailable.setForeground(Color.BLUE);
		lblManageNotAvailable.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblManageNotAvailable);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(642, 326, 170, 42);
		btnUpdate.setBackground(Color.BLUE);
		btnUpdate.setForeground(Color.WHITE);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(215, 326, 170, 42);
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.ORANGE);
		contentPane.add(btnBack);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(429, 326, 170, 42);
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.WHITE);
		contentPane.add(btnDelete);
		
		DbValTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 71, 1015, 231);
		contentPane.add(scrollPane);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TimeAllocation.main(new String[]{});
				dispose();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int i = table.getSelectedRow();
					String cell = table.getModel().getValueAt(i, 0).toString();
					Connection conn=DB.getConnection();
					String delete = "DELETE FROM std_not_available_times WHERE ID = " +cell;
					PreparedStatement statement = conn.prepareStatement(delete);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
//					conn.commit();
					UpdateTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
			table = new JTable(model);
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

}
