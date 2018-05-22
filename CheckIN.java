import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class CheckIN extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtReservationid;
	private JTextField textFieldFN;
	private JTextField textFieldLN;
	private JTextPane txtpnFirstName;
	private JTextPane txtpnLastName;
	private JTextField textFieldRN;
	private JTextField textFieldCountry;
	private JTextPane txtpnCountry;
	private JTextPane txtpnTotalDays;
    private JDateChooser dateChooserIN, dateChooserOUT;
    private JLabel lblNewLabel;
    private JLabel lblCheckinForm;
   
	public CheckIN() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label to count days
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(219, 263, 66, 15);
		contentPane.add(lblNewLabel);

		
		txtReservationid = new JTextField();
		txtReservationid.setBackground(Color.WHITE);
		txtReservationid.setBounds(120, 70, 150, 20);
		contentPane.add(txtReservationid);
		txtReservationid.setColumns(10);
		
		textFieldFN = new JTextField();
		textFieldFN.setBackground(Color.WHITE);
		textFieldFN.setColumns(10);
		textFieldFN.setBounds(120, 100, 150, 20);
		contentPane.add(textFieldFN);
		
		textFieldLN = new JTextField();
		textFieldLN.setBackground(Color.WHITE);
		textFieldLN.setColumns(10);
		textFieldLN.setBounds(120, 130, 150, 20);
		contentPane.add(textFieldLN);
		
		JTextPane txtpnReservationid = new JTextPane();
		txtpnReservationid.setBackground(Color.ORANGE);
		txtpnReservationid.setText("ReservationID");
		txtpnReservationid.setBounds(10, 70, 100, 20);
		contentPane.add(txtpnReservationid);
		
		txtpnFirstName = new JTextPane();
		txtpnFirstName.setBackground(Color.ORANGE);
		txtpnFirstName.setText("First Name");
		txtpnFirstName.setBounds(10, 100, 100, 20);
		contentPane.add(txtpnFirstName);
		
		txtpnLastName = new JTextPane();
		txtpnLastName.setBackground(Color.ORANGE);
		txtpnLastName.setText("Last Name");
		txtpnLastName.setBounds(10, 130, 100, 20);
		contentPane.add(txtpnLastName);
		
		textFieldRN = new JTextField();
		textFieldRN.setBackground(Color.WHITE);
		textFieldRN.setColumns(10);
		textFieldRN.setBounds(120, 160, 150, 20);
		contentPane.add(textFieldRN);
		
		textFieldCountry = new JTextField();
		textFieldCountry.setColumns(10);
		textFieldCountry.setBounds(120, 250, 150, 20);
		contentPane.add(textFieldCountry);
		
		JTextPane txtpnRoomNumber = new JTextPane();
		txtpnRoomNumber.setBackground(Color.ORANGE);
		txtpnRoomNumber.setText("Room Number");
		txtpnRoomNumber.setBounds(10, 160, 100, 20);
		contentPane.add(txtpnRoomNumber);
		
		JTextPane txtpnCheckinDate = new JTextPane();
		txtpnCheckinDate.setBackground(Color.ORANGE);
		txtpnCheckinDate.setText("CheckIN Date");
		txtpnCheckinDate.setBounds(10, 190, 100, 20);
		contentPane.add(txtpnCheckinDate);
		
		JTextPane txtpnCheckoutDate = new JTextPane();
		txtpnCheckoutDate.setBackground(Color.ORANGE);
		txtpnCheckoutDate.setText("CheckOut Date");
		txtpnCheckoutDate.setBounds(10, 220, 100, 20);
		contentPane.add(txtpnCheckoutDate);
		
		txtpnCountry = new JTextPane();
		txtpnCountry.setBackground(Color.ORANGE);
		txtpnCountry.setText("Country");
		txtpnCountry.setBounds(10, 250, 100, 20);
		contentPane.add(txtpnCountry);
		
		txtpnTotalDays = new JTextPane();
		txtpnTotalDays.setBackground(Color.YELLOW);
		txtpnTotalDays.setText("Total Days: ");
		txtpnTotalDays.setBounds(12, 314, 100, 19);
		contentPane.add(txtpnTotalDays);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setBounds(185, 68, 1, 15);
		contentPane.add(textArea);
		
		dateChooserIN = new JDateChooser();
		dateChooserIN.setBounds(120, 190, 100, 20);
		contentPane.add(dateChooserIN);
		dateChooserOUT = new JDateChooser();
		dateChooserOUT.setBounds(120, 220, 100, 20);
		contentPane.add(dateChooserOUT);
		
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComponent comp = (JComponent) e.getSource();
				  Window win = SwingUtilities.getWindowAncestor(comp);
				  win.dispose();
			}
		});
		button.setBounds(201, 410, 110, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtReservationid.getText() + " "+ textFieldFN.getText() + " " + textFieldFN.getText() + " " 
						+ textFieldRN.getText());
				java.util.Date in = dateChooserIN.getDate();
				java.util.Date out = dateChooserOUT.getDate();
				int t =  (int) (out.getTime() - in.getTime()) / (1000 * 60 * 60 * 24);
				lblNewLabel.setText(String.valueOf(t));
				//java.sql.Date sd = new java.sql.Date(d.getTime());
				//System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(in));
				try {
				PreparedStatement st = Login.getCon().prepareStatement("INSERT INTO guests"
						+ "(guest_id, firstname, lastname, roomtype, checkin, checkout, country) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
				int i = Integer.parseInt(textFieldRN.getText());
				st.setString(1, textFieldFN.getText());
				st.setString(2, textFieldFN.getText());
				st.setInt(3, i);
				st.setDate(4,  new java.sql.Date(in.getTime()));
				st.setDate(5, new java.sql.Date(out.getTime()));
				st.setString(6, textFieldCountry.getText());
				st.executeUpdate();
				st.close();
				st = Login.getCon().prepareStatement("UPDATE rooms SET status = 'Full' WHERE roomnumber=?;");
				st.setInt(1, i);
				st.executeUpdate();
				st.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(37, 410, 110, 25);
		contentPane.add(button_1);
		lblCheckinForm = new JLabel("CheckIn FORM");
		lblCheckinForm.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblCheckinForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckinForm.setBounds(22, 12, 286, 40);
		contentPane.add(lblCheckinForm);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
