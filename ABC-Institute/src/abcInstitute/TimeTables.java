package abcInstitute;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.ibm.icu.text.MessageFormat;

public class TimeTables {

	private JFrame frame;
	private JPanel contentPane;
	private JTable table;
	private String[] columns = {"Time Slots","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	String[] catg = { "Lecturer","Location","Group"};
	

	private String searchVal;
	private String searchBox;
	private JComboBox comboBoxSearch;
	private JComboBox comboBoxSearchVal;
	private JButton btnPrint;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeTables window = new TimeTables();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public TimeTables() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,1366, 725);
		frame.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new java.awt.Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 101, 1328, 584);
		contentPane.add(panel);
		
		UpdateTable();
		panel.setLayout(null);

		comboBoxSearch = new JComboBox();
		comboBoxSearch.setBackground(Color.WHITE);
		comboBoxSearch.setBounds(730, 43, 239, 34);
		contentPane.add(comboBoxSearch);
		
		JButton btnNewButtonSearch = new JButton("Generate");
		btnNewButtonSearch.setBounds(1035, 34, 128, 47);
		btnNewButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBox = (String) comboBoxSearch.getSelectedItem();
				searchVal = (String) comboBoxSearchVal.getSelectedItem();
				try {
					searchTable(searchVal,searchBox);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButtonSearch.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButtonSearch.setBackground(new Color(60, 179, 113));
		contentPane.add(btnNewButtonSearch);
		
		comboBoxSearchVal = new JComboBox(catg);
		comboBoxSearchVal.setBackground(Color.WHITE);
		comboBoxSearchVal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DbData();
			}
		});
		comboBoxSearchVal.setBounds(453, 43, 239, 34);
		contentPane.add(comboBoxSearchVal);
		
		JLabel lblNewLabel = new JLabel("Select Category to generate Time Table");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel.setBounds(126, 45, 289, 34);
		contentPane.add(lblNewLabel);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Print Time Table");
				MessageFormat footer = new MessageFormat("Thank you");
				try {
				    boolean complete = table.print();
				    if (complete) {
				    } else {
				    }
				} catch (PrinterException pe) {
				}
			}
		});
		btnPrint.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPrint.setBackground(new Color(60, 179, 113));
		btnPrint.setBounds(1204, 34, 128, 47);
		contentPane.add(btnPrint);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnHome.setFont(new Font("Arial", Font.PLAIN, 15));
		btnHome.setBackground(Color.CYAN);
		btnHome.setBounds(10, 34, 95, 47);
		contentPane.add(btnHome);
	}
	
	public void DbData() {
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps1 = null;
			String s = searchVal = (String) comboBoxSearchVal.getSelectedItem();
			if (s.equals("Lecturer")) {
				ps1 =con.prepareStatement("select lecturerName from lecturers",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else if(s.equals("Location")) {
				ps1 =con.prepareStatement("select roomName from locations",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}else if(s.equals("Group")) {
				ps1 =con.prepareStatement("select grpId from students",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			}
			ResultSet rs1 =ps1.executeQuery();
			int count=0;
			comboBoxSearch.removeAllItems();
			while(rs1.next()) {
				
				comboBoxSearch.addItem(rs1.getString(1));
				count++;
			}
			
		}catch(Exception e){System.out.println(e);}
		
	}

	public void searchTable(String searchVal, String searchBox) throws SQLException {

		String data[][]=null;
		String column[]=null;

		try{
		Connection con=DB.getConnection();
		PreparedStatement ps = null;
		if(searchVal.equals("Lecturer")) {
			ps=con.prepareStatement("select * from preffered_sessions where Lecturer = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, searchBox);
		}else if(searchVal.equals("Location")) {
				ps=con.prepareStatement("select * from preffered_sessions where Room = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ps.setString(1, searchBox);
				
		}else if(searchVal.equals("Group")) {
			ps=con.prepareStatement("select * from preffered_sessions where Main_Group = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, searchBox);
		}else {
			ps=con.prepareStatement("select * from preffered_sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}
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

			data=new String[11][8];
			int count=0;
			int y;
			for(int x=8; x < 10 ; x++) {
				y = x+1;
				data[x-8][0]= "0"+x+".30 - "+y+".30";
			}
			for(int x=10; x < 19 ; x++) {
				y = x+1;
				data[x-8][0]= x+".30 - "+y+".30";
			}
			while(rs.next()){
				String day = rs.getString(5);

            	String Btime = rs.getString(7).substring(0,2);
            	String Etime = rs.getString(8).substring(0,2);
				switch(day)
		        {
		            case "Monday":
		            		if(Btime.equals("08") && Etime.equals("09")) {
			            		data[0][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}else if(Btime.equals("08") && Etime.equals("10")) {
			            		data[0][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            		data[1][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}
			            	else if(Btime.equals("09") && Etime.equals("10")) {
			            		data[0][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}else if(Btime.equals("09") && Etime.equals("11")) {
			            		data[1][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            		data[2][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}
		            	for(int x = 2; x < 11; x++) {
		            		int bVal = x + 8;
		            		String bv = Integer.toString(bVal);
		            		
		            		int eVal = x + 9;
		            		String ev = Integer.toString(eVal);
		            		
		            		int eVal2 = x + 10;
		            		String ev2 =Integer.toString(eVal2);
		            		if(Btime.equals(bv) && Etime.equals(ev)) {
			            		data[x][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}else if(Btime.equals(bv) && Etime.equals(ev2)) {
			            		data[x][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            		data[x+1][1]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
			            	}
		            	}
		            	
		                break;
		            case "Tuesday":
		            	
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][2]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;
		            case "Wednesday":
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][3]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;
		            case "Thursday":
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][4]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;
		            case "Friday":
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][5]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;   
		            case "Saturday":
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][6]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;
		            case "Sunday":
		            	if(Btime.equals("08") && Etime.equals("09")) {
		            		data[0][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("08") && Etime.equals("10")) {
		            		data[0][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[1][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            	else if(Btime.equals("09") && Etime.equals("10")) {
		            		data[1][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}else if(Btime.equals("09") && Etime.equals("11")) {
		            		data[1][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            		data[2][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            	}
		            		for(int x = 2; x < 11; x++) {
		            			int bVal = x + 8;
		            			String bv = Integer.toString(bVal);
	            		
		            			int eVal = x + 9;
		            			String ev = Integer.toString(eVal);
	            		
		            			int eVal2 = x + 10;
		            			String ev2 =Integer.toString(eVal2);
		            			if(Btime.equals(bv) && Etime.equals(ev)) {
		            				data[x][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}else if(Btime.equals(bv) && Etime.equals(ev2)) {
		            				data[x][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            				data[x+1][7]=rs.getString(2) + "\r\n" + rs.getString(3) + "\r\n" + rs.getString(7);
		            			}
		            		}
		                break;
		            default:
		                System.out.println("no match");
		        }
					
				count++;
			}
			DefaultTableModel dm = new DefaultTableModel() {
		          public Class<String> getColumnClass(int columnIndex) {
		            return String.class;
		          }
		          public boolean isCellEditable(int row, int column) {
		            return false;
		          }
		        };
			dm.setDataVector(data,columns);

		        table = new JTable(dm);
		        table.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
		        TableRowSorter<? extends TableModel> sort = new TableRowSorter<DefaultTableModel>(dm);
		        table.setRowSorter(sort);
		        table.repaint();

				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(0, 0, 1328, 580);
				panel.add(scrollPane);
				
		        frame.setLocationByPlatform(true);
		        frame.setVisible(true);
		}catch(Exception e){System.out.println(e);}
	}
	
	public void UpdateTable() throws SQLException {
		searchTable("0", "0");
		
	}
	class MultiLineTableCellRenderer extends JTextArea 
	  implements TableCellRenderer {
	  private List<List<Integer>> rowColHeight = new ArrayList<List<Integer>>();

	  public MultiLineTableCellRenderer() {
	    setLineWrap(true);
	    setWrapStyleWord(true);
	    setOpaque(true);
	  }
	  public Component getTableCellRendererComponent(
		      JTable table, Object value, boolean isSelected, boolean hasFocus,
		      int row, int column) {
		    if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		    } else {
		      setForeground(table.getForeground());
		      setBackground(table.getBackground());
		    }
		    setFont(table.getFont());
		    if (hasFocus) {
		      setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		      if (table.isCellEditable(row, column)) {
		        setForeground(UIManager.getColor("Table.focusCellForeground"));
		        setBackground(UIManager.getColor("Table.focusCellBackground"));
		      }
		    } else {
		      setBorder(new EmptyBorder(1, 2, 1, 2));
		    }
		    if (value != null) {
		      setText(value.toString());
		    } else {
		      setText("");
		    }
		    adjustRowHeight(table, row, column);
		    return this;
	  }
		  
	  private void adjustRowHeight(JTable table, int row, int column) {
		    //The trick to get this to work properly is to set the width of the column to the 
		    //textarea. The reason for this is that getPreferredSize(), without a width tries 
		    //to place all the text in one line. By setting the size with the with of the column, 
		    //getPreferredSize() returnes the proper height which the row should have in
		    //order to make room for the text.
		    int cWidth = table.getTableHeader().getColumnModel().getColumn(column).getWidth();
		    setSize(new Dimension(cWidth, 1000));
		    int prefH = getPreferredSize().height;
		    while (rowColHeight.size() <= row) {
		      rowColHeight.add(new ArrayList<Integer>(column));
		    }
		    List<Integer> colHeights = rowColHeight.get(row);
		    while (colHeights.size() <= column) {
		      colHeights.add(0);
		    }
		    colHeights.set(column, prefH);
		    int maxH = prefH;
		    for (Integer colHeight : colHeights) {
		      if (colHeight > maxH) {
		        maxH = colHeight;
		      }
		    }
		    if (table.getRowHeight(row) != maxH) {
		      table.setRowHeight(row, maxH);
		    }
		  }
	}

}
