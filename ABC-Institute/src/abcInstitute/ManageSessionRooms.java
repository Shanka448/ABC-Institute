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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ManageSessionRooms {

	private JFrame frame;
	private JPanel contentPane;
	private JComboBox comboBoxSlctRoom ;
	private JComboBox comboBoxSlctSess;
	private JLabel lblSlctedSession;
	private String ssid,roomNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSessionRooms window = new ManageSessionRooms();
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
	public ManageSessionRooms() {
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
		
		JLabel lblSelectSessions = new JLabel("Select Sessions");
		lblSelectSessions.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSelectSessions.setBounds(376, 113, 151, 35);
		contentPane.add(lblSelectSessions);
		
		
		JLabel lblSelectRoom = new JLabel("Select Room");
		lblSelectRoom.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSelectRoom.setBounds(376, 333, 151, 35);
		contentPane.add(lblSelectRoom);
		
		JLabel lblSelectedSession = new JLabel("Selected Session");
		lblSelectedSession.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSelectedSession.setBounds(376, 202, 204, 35);
		contentPane.add(lblSelectedSession);
		
		lblSlctedSession = new JLabel("");
		lblSlctedSession.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSlctedSession.setBounds(569, 202, 390, 35);
		contentPane.add(lblSlctedSession);
		
		comboBoxSlctSess = new JComboBox();
		comboBoxSlctSess.setBounds(694, 115, 204, 36);
		contentPane.add(comboBoxSlctSess);
		comboBoxSlctSess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SesVal = (String) comboBoxSlctSess.getSelectedItem();
				String tag ="";
				
				try {
					Connection con=DB.getConnection();
						lblSlctedSession.setText(null);
						PreparedStatement ps4 = con.prepareStatement("SELECT sessionName  FROM sessions WHERE ssid = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ps4.setString(1, SesVal);
						ResultSet rs4 =ps4.executeQuery();
						if(rs4.next()) {
							lblSlctedSession.setText(rs4.getString(1));
						}
						PreparedStatement ps5 = con.prepareStatement("SELECT tag  FROM sessions WHERE ssid = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ps5.setString(1, SesVal);
						ResultSet rs5 =ps5.executeQuery();
						if(rs5.next()) {
							tag = rs5.getString(1);
						}
						if(tag.equals("Lecture") || tag.equals("Tutorial")) {
							
							comboBoxSlctRoom.removeAllItems();
							PreparedStatement ps6 = con.prepareStatement("SELECT roomName  FROM locations WHERE roomType = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
							ps6.setString(1, "Lecture Hall");
							ResultSet rs6 =ps6.executeQuery();
							int count=0;
							while(rs6.next()) {
								comboBoxSlctRoom.addItem(rs6.getString(1));
								count++;
							}
						}else if(tag.equals("Practical")) {
							comboBoxSlctRoom.removeAllItems();
							PreparedStatement ps7 = con.prepareStatement("SELECT roomName  FROM locations WHERE roomType = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
							ps7.setString(1, "Laboratary");
							ResultSet rs7 =ps7.executeQuery();
							int count=0;
							while(rs7.next()) {
								comboBoxSlctRoom.addItem(rs7.getString(1));
								count++;
							}
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		comboBoxSlctRoom = new JComboBox();
		comboBoxSlctRoom.setBounds(701, 335, 197, 36);
		contentPane.add(comboBoxSlctRoom);
		
		DbData();
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.setBackground(new Color(218, 165, 32));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxSlctSess.setSelectedIndex(0);
				comboBoxSlctRoom.setSelectedIndex(0);
				lblSlctedSession.setText(null);
			}
		});
		btnNewButton.setBounds(569, 560, 131, 48);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ssid = (String) comboBoxSlctSess.getSelectedItem();
				roomNo = (String) comboBoxSlctRoom.getSelectedItem();
				ValidateInputs(ssid, roomNo);
			}
		});
		btnSave.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSave.setBackground(new Color(65, 105, 225));
		btnSave.setBounds(767, 560, 131, 48);
		contentPane.add(btnSave);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DashBoard.main(new String[]{});
				frame.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(32, 178, 170));
		btnNewButton_1.setBounds(1113, 551, 158, 57);
		contentPane.add(btnNewButton_1);
	}
	public void ValidateInputs(String ssid, String roomNo) {
		String type = "";
		String sVal1 = "";
		String sVal2 = "";
		try {
			Connection con=DB.getConnection();
			Statement query = con.createStatement();
			this.ssid = ssid;
			this.roomNo = roomNo;
			
			if(ssid.isEmpty() || roomNo.isEmpty()  ) {
				showMessageDialog(null, "All fields shoud be filled", "Error", ERROR_MESSAGE);
			}else{
				PreparedStatement ps0 = con.prepareStatement("SELECT type  FROM manageactivities WHERE sessionId1 = ? OR sessionId2 = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ps0.setString(1, ssid);
				ps0.setString(2, ssid);
				ResultSet rs0 =ps0.executeQuery();
				if(rs0.next()) {
					type = rs0.getString(1);
				}
				if(type.equals("Consecutive")) {
					PreparedStatement pss = con.prepareStatement("SELECT sessionId1, sessionId2  FROM manageactivities WHERE sessionId1 = ? OR sessionId2 = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					pss.setString(1, ssid);
					pss.setString(2, ssid);
					ResultSet rss =pss.executeQuery();
					if(rss.next()) {
						sVal1 = rss.getString(1);
						sVal2 = rss.getString(2);
						System.out.println(sVal1);
						System.out.println(sVal2);
					}
					
					PreparedStatement ps =con.prepareStatement("UPDATE sessions SET rooms = ? WHERE ssid = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setString(1,roomNo);
					ps.setString(2,sVal1);
					ps.executeUpdate();
					
					PreparedStatement ps1 =con.prepareStatement("UPDATE sessions SET rooms = ? WHERE ssid = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps1.setString(1,roomNo);
					ps1.setString(2,sVal2);
					ps1.executeUpdate();
					
					showMessageDialog(null, "Successfully update Consecutive Sessions!");
					
				}else {

					PreparedStatement ps =con.prepareStatement("UPDATE sessions SET rooms = ? WHERE ssid = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setString(1,roomNo);
					ps.setString(2,ssid);
					ps.executeUpdate();
					showMessageDialog(null, "Successfully update!");
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void DbData() {
		String x,y;
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps1 =con.prepareStatement("SELECT ssid  FROM sessions",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs1 =ps1.executeQuery();
			int count=0;
			int count1=0;
			while(rs1.next()) {
				x = rs1.getString(1);
				comboBoxSlctSess.addItem(rs1.getString(1));
				count++;
			}
			
			PreparedStatement ps2 =con.prepareStatement("SELECT roomName  FROM locations",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 =ps2.executeQuery();
			count=0;
			while(rs2.next()) {
				comboBoxSlctRoom.addItem(rs2.getString(1));
				count++;
			}
			
		}catch(Exception e){System.out.println(e);}
	}

}
