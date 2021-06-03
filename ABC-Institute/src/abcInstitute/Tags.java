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
import javax.swing.DefaultComboBoxModel;

public class Tags {

	private JFrame frame;
	private JPanel contentPane;
	private JTable tabelMngTags;
	String[] relatedTags = { "Lecture","Tutorial","Practicle"};
	String[] columns = {"ID","Tag Name","Tag Code","Related Tag"};
	private JTextField textFieldTagCode;
	private JTextField textFieldTagName;
	private String tagName =  "";
	private String tagCode =  "";
	private String tagRelated =  "";
	private int crud,tid;
	private JTextField textFieldMngTagCode;
	private JTextField textFieldMngTagName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tags window = new Tags();
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
	public Tags() {
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
		
		JPanel tabAddTags = new JPanel();
		tabAddTags.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Add Tags", null, tabAddTags, null);
		tabAddTags.setLayout(null);
		
		JLabel lblTagName = new JLabel("Tag Name");
		lblTagName.setBounds(457, 197, 208, 19);
		lblTagName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddTags.add(lblTagName);
		
		JLabel lblTagCode = new JLabel("Tag Code");
		lblTagCode.setBounds(457, 255, 174, 19);
		lblTagCode.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddTags.add(lblTagCode);
		
		JLabel lblTagRelated = new JLabel("Related Tag");
		lblTagRelated.setBounds(457, 318, 134, 19);
		lblTagRelated.setFont(new Font("Arial", Font.PLAIN, 16));
		tabAddTags.add(lblTagRelated);
		
		JComboBox comboBoxTagRelated = new JComboBox(relatedTags);
		comboBoxTagRelated.setBackground(new Color(255, 255, 255));
		comboBoxTagRelated.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxTagRelated.setModel(new DefaultComboBoxModel(new String[] {"Lecture", "Tutorial", "Practical"}));
		comboBoxTagRelated.setSelectedIndex(0);
		comboBoxTagRelated.setBounds(708, 312, 174, 34);
		tabAddTags.add(comboBoxTagRelated);
		
		textFieldTagCode = new JTextField();
		textFieldTagCode.setBackground(new Color(255, 255, 255));
		textFieldTagCode.setBounds(708, 240, 174, 34);
		tabAddTags.add(textFieldTagCode);
		textFieldTagCode.setColumns(10);
		
		textFieldTagName = new JTextField();
		textFieldTagName.setBackground(new Color(255, 255, 255));
		textFieldTagName.setColumns(10);
		textFieldTagName.setBounds(708, 182, 174, 34);
		tabAddTags.add(textFieldTagName);
		
		JButton btnTagsClear = new JButton("Clear");
		btnTagsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxTagRelated.setSelectedIndex(0);
				textFieldTagCode.setText(null);
				textFieldTagName.setText(null);
			}
		});
		btnTagsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTagsClear.setBackground(new Color(255, 140, 0));
		btnTagsClear.setBounds(457, 582, 158, 47);
		tabAddTags.add(btnTagsClear);
		
		JButton btnTagsSave = new JButton("Save");
		btnTagsSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tagName =  textFieldTagName.getText();
				tagCode =  textFieldTagCode.getText();
				tagRelated = (String)comboBoxTagRelated.getSelectedItem();
				crud = 1;
				tid = 0;
				ValidateInputs(tagName,tagCode,tagRelated,crud,tid);
			}

		});
		btnTagsSave.setFont(new Font("Arial", Font.PLAIN, 20));
		btnTagsSave.setBackground(new Color(0, 191, 255));
		btnTagsSave.setBounds(724, 582, 158, 47);
		tabAddTags.add(btnTagsSave);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton.setBounds(1076, 552, 167, 77);
		tabAddTags.add(btnNewButton);
		
		JPanel tabManageTags = new JPanel();
		tabManageTags.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Manage Tags", null, tabManageTags, null);
		tabManageTags.setLayout(null);
		
		
		
		JLabel lblMngTagName = new JLabel("Tag Name");
		lblMngTagName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMngTagName.setBounds(87, 409, 204, 19);
		lblMngTagName.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageTags.add(lblMngTagName);
		
		JLabel lblMngTagCode = new JLabel("Tag Code");
		lblMngTagCode.setBounds(537, 409, 89, 19);
		lblMngTagCode.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageTags.add(lblMngTagCode);
		
		JLabel lblMngTagRelated = new JLabel("Related Tags");
		lblMngTagRelated.setBounds(923, 409, 134, 19);
		lblMngTagRelated.setFont(new Font("Arial", Font.PLAIN, 16));
		tabManageTags.add(lblMngTagRelated);
		
		JComboBox comboBoxMngTagRelated = new JComboBox(relatedTags);
		comboBoxMngTagRelated.setBackground(new Color(255, 255, 255));
		comboBoxMngTagRelated.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxMngTagRelated.setModel(new DefaultComboBoxModel(new String[] {"Lecture", "Tutorial", "Practical"}));
		comboBoxMngTagRelated.setSelectedIndex(0);
		comboBoxMngTagRelated.setBounds(1061, 408, 174, 25);
		tabManageTags.add(comboBoxMngTagRelated);
		
		textFieldMngTagCode = new JTextField();
		textFieldMngTagCode.setBackground(new Color(255, 255, 255));
		textFieldMngTagCode.setBounds(653, 408, 174, 25);
		tabManageTags.add(textFieldMngTagCode);
		textFieldMngTagCode.setColumns(10);
		
		textFieldMngTagName = new JTextField();
		textFieldMngTagName.setBackground(new Color(255, 255, 255));
		textFieldMngTagName.setBounds(250, 405, 174, 30);
		tabManageTags.add(textFieldMngTagName);
		textFieldMngTagName.setColumns(10);
		
		JPanel panelMngTags = new JPanel();
		panelMngTags.setBounds(70, 11, 1165, 372);
		tabManageTags.add(panelMngTags);
		panelMngTags.setLayout(new BorderLayout(0, 0));
		
		DbValTable();
		tabelMngTags.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
				comboBoxMngTagRelated.setSelectedItem((Object)tabelMngTags.getModel().getValueAt(tabelMngTags.getSelectedRow(), 1).toString());
	        	textFieldMngTagName.setText(tabelMngTags.getModel().getValueAt(tabelMngTags.getSelectedRow(), 2).toString());
	        	textFieldMngTagCode.setText(tabelMngTags.getModel().getValueAt(tabelMngTags.getSelectedRow(), 3).toString());
	        	
	        }
	    });
		
		JScrollPane paneScrlMngTags = new JScrollPane(tabelMngTags);
		panelMngTags.add(paneScrlMngTags);
		
		JButton btnMngTagsClear = new JButton("Clear");
		btnMngTagsClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxMngTagRelated.setSelectedIndex(0);
				textFieldMngTagCode.setText(null);
				textFieldMngTagName.setText(null);
			}
		});
		btnMngTagsClear.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngTagsClear.setBackground(new Color(255, 140, 0));
		btnMngTagsClear.setBounds(288, 583, 158, 47);
		tabManageTags.add(btnMngTagsClear);
		
		JButton btnMngTagsBack = new JButton("Back");
		btnMngTagsBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMngTagsBack.setBackground(new Color(32, 178, 170));
		btnMngTagsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnMngTagsBack.setBounds(1068, 553, 167, 77);
		tabManageTags.add(btnMngTagsBack);
		
		JButton btnMngTagsDelete = new JButton("Delete");
		btnMngTagsDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tid = Integer.parseInt(tabelMngTags.getModel().getValueAt(tabelMngTags.getSelectedRow(), 0).toString());
				Connection con=DB.getConnection();
				try {
					PreparedStatement  ps = con.prepareStatement("DELETE FROM tags WHERE tid = ?");
					ps.setInt(1,tid);
					ps.execute();

					showMessageDialog(null, "Item deleted !");
					UpdateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnMngTagsDelete.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngTagsDelete.setBackground(new Color(255, 69, 0));
		btnMngTagsDelete.setBounds(778, 583, 158, 47);
		tabManageTags.add(btnMngTagsDelete);
		
		JButton btnMngTagsUpdate = new JButton("Update");
		btnMngTagsUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tagRelated = (String) comboBoxMngTagRelated.getSelectedItem();
				tagName =  textFieldMngTagName.getText();
				tagCode =  textFieldMngTagCode.getText();
				crud = 0;
				tid = Integer.parseInt(tabelMngTags.getModel().getValueAt(tabelMngTags.getSelectedRow(), 0).toString());
				ValidateInputs(tagName,tagCode,tagRelated,crud,tid);
				
			}
		});
		btnMngTagsUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMngTagsUpdate.setBackground(new Color(0, 191, 255));
		btnMngTagsUpdate.setBounds(536, 583, 158, 47);
		tabManageTags.add(btnMngTagsUpdate);
		
	}
	public void ValidateInputs(String tagName, String tagCode, String tagRelated, int crud, int tid) {
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.tagName = tagName;
			this.tagCode =  tagCode;
			this.tagRelated = tagRelated;
			this.tid = tid;
			
			if(tagName.isEmpty() || tagCode.isEmpty() ) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}
			else if(crud == 0) {
					PreparedStatement  ps = con.prepareStatement("UPDATE tags SET tagName = ?, tagCode = ?, tagRelated = ?   WHERE tid = ?");
					ps.setString(1,tagName);
					ps.setString(2,tagCode);
					ps.setString(3,tagRelated);
					ps.setInt(4,tid);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
					UpdateTable();
				}else if(crud == 1){
					query.execute("insert into tags (tagName, tagCode, tagRelated) values('"+tagName+"','"+tagCode+"','"+tagRelated+"')");
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
			PreparedStatement ps=con.prepareStatement("select * from tags",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			tabelMngTags = new JTable(model);
			tabelMngTags.setBounds(161, 11, 1165, 372);
			tabelMngTags.setRowHeight(30);
		}catch(Exception e){System.out.println(e);}
		
	}
	public void UpdateTable() throws SQLException {
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from tags",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		tabelMngTags.setModel(model);
		tabelMngTags.repaint();
	}

}
