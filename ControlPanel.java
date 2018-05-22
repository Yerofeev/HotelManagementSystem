import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.Vector;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;


public class ControlPanel extends JFrame {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	public BufferedImage img;
	PreparedStatement stmt;
	ResultSet rs;
	int freeRooms = 0;
	JLabel lblNumberOfFree;
	
	public ControlPanel()  {
		setFont(new Font("Symbola", Font.BOLD, 12));
		setForeground(Color.GREEN);
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/user/Downloads/h2.jpg"));
    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 495);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(e ->{System.exit(0);});
		
		JMenu mnControlPanel = new JMenu("Control Panel");
		menuBar.add(mnControlPanel);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(135, 44, 600, 324);
        scrollPane.setBackground(Color.YELLOW);
        scrollPane.setForeground(Color.YELLOW);
		contentPane.add(scrollPane);
		JButton button = new JButton("Rooms");
		button.setBackground(Color.GRAY);
		button.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                query("SELECT * FROM rooms");
			}
		});
		button.setBounds(10, 100, 120, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Guests");
		button_1.setBackground(Color.GRAY);
		button_1.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                query("SELECT * FROM guests");					
			}
		});
		
		button_1.setBounds(10, 135, 120, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Reservations");
		button_2.setBackground(Color.GRAY);
		button_2.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query("SELECT * FROM reservations");
			}
		});
		button_2.setBounds(10, 170, 120, 25);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("CheckIn");
		button_3.setBackground(Color.GREEN);
		button_3.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run(){
						new CheckIN();
					}
				});
			}
		});
		button_3.setBounds(20, 270, 100, 25);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("CheckOut");
		button_4.setBackground(Color.RED);
		button_4.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run(){
						new CheckOUT();
			}
		});
			}
		});
		button_4.setBounds(20, 300, 100, 25);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("CashDesk");
		button_5.setBackground(Color.GRAY);
		button_5.setFont(new Font("DejaVu Sans", Font.BOLD, 11));
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query("SELECT * FROM cashdesk");
			}
		});
		button_5.setBounds(10, 205, 120, 25);
		contentPane.add(button_5);
		lblNumberOfFree = new JLabel("Number of free rooms: " + String.valueOf(freeRooms()));
		lblNumberOfFree.setFont(new Font("DejaVu Sans", Font.ITALIC, 15));
		lblNumberOfFree.setBounds(145, 389, 205, 31);
		contentPane.add(lblNumberOfFree);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/home/user/Downloads/Haunted-Hotel-icon.png"));
		lblNewLabel.setBounds(6, 324, 124, 114);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("/home/user/Downloads/Old-House-icon.png"));
		lblNewLabel_1.setBounds(747, 318, 74, 102);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("/home/user/Downloads/Little-House-icon.png"));
		lblNewLabel_2.setBounds(732, 6, 89, 102);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("/home/user/Downloads/home-icon.png"));
		lblNewLabel_3.setBounds(26, 6, 74, 81);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNumberOfGuests = new JLabel("Number of guests: 0");
		lblNumberOfGuests.setFont(new Font("DejaVu Sans", Font.ITALIC, 15));
		lblNumberOfGuests.setBounds(362, 389, 205, 31);
		contentPane.add(lblNumberOfGuests);
		
		JLabel lblNumberOfReservations = new JLabel("Number of reservations: 0");
		lblNumberOfReservations.setFont(new Font("DejaVu Sans", Font.ITALIC, 15));
		lblNumberOfReservations.setBounds(538, 389, 205, 31);
		contentPane.add(lblNumberOfReservations);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				freeRooms();
			}
		});
		btnNewButton.setIcon(new ImageIcon("/home/user/Downloads/Button-Refresh-icon.png"));
		btnNewButton.setBounds(760, 130, 45, 45);
		contentPane.add(btnNewButton);
		
		JButton button_6 = new JButton("");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_6.setIcon(new ImageIcon("/home/user/Downloads/Actions-window-close-icon.png"));
		button_6.setBounds(760, 270, 45, 45);
		contentPane.add(button_6);
		
		JButton button_7 = new JButton("");
		button_7.setIcon(new ImageIcon("/home/user/Downloads/Help-icon (1).png"));
		button_7.setBounds(760, 200, 45, 45);
		contentPane.add(button_7);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 250, 103, 2);
		contentPane.add(separator);
		
		query("SELECT * FROM rooms ORDER BY roomnumber");
		setLocationRelativeTo(null);
		setVisible(true);
	}
	    public int freeRooms() {
	    	int r = 0;
	    	PreparedStatement stmt;
			try {
				stmt = Login.getCon().prepareStatement("SELECT COUNT(*) FROM rooms WHERE status = 'Free';");
				rs = stmt.executeQuery();
				rs.next();
				r = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}

	    	return r;
	    }
	    public void query(String s) {
	    //populate table from SQL query
			try
			{
				PreparedStatement stmt = Login.getCon().prepareStatement(s);
				rs = stmt.executeQuery();
				table = new JTable(buildTableModel(rs));
				table.setBackground(Color.PINK);
				//scrollPane.setViewportView(table);
				scrollPane.setViewportView(table);
				rs.close();
				stmt.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
	    }
	    
		public static DefaultTableModel buildTableModel(ResultSet rs)
		        throws SQLException {

		    ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		    return new DefaultTableModel(data, columnNames);

		}
}
