import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CheckOUT extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
    JLabel jE;
	/**
	 * Create the frame.
	 */
	public CheckOUT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 266);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(80, 140, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnRoomNumber = new JTextPane();
		txtpnRoomNumber.setBackground(Color.GRAY);
		txtpnRoomNumber.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		txtpnRoomNumber.setText("Room Number:");
		txtpnRoomNumber.setBounds(80, 109, 114, 19);
		contentPane.add(txtpnRoomNumber);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComponent comp = (JComponent) e.getSource();
				 Window win = SwingUtilities.getWindowAncestor(comp);
				 win.dispose();
			}
		});
		btnCancel.setBounds(147, 187, 110, 25);
		contentPane.add(btnCancel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jE != null) contentPane.remove(jE);
				int i = 0;
				try {
				i = Integer.parseInt(textField.getText());
				}
				catch (NumberFormatException e3){
					errorLabel("Enter numbers");
			    	return;
				}
				PreparedStatement stmt;
				try {
					stmt = Login.getCon().prepareStatement("select status from rooms where roomnumber=?;");
				stmt.setInt(1, i);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				
				if (!rs.getString(1).equals("Full")) {
					errorLabel("This room is not full");
				}
				else
				    {
					//set room to empty
					stmt = Login.getCon().prepareStatement("UPDATE rooms SET status = 'Free' WHERE roomnumber=?;");
					stmt.setInt(1, i);
					stmt.executeUpdate();
					stmt.close();
					//get number of staying days
					stmt = Login.getCon().prepareStatement("select (current_date - checkin) from guests where roomno=?;");
					stmt.setInt(1, i);
					rs = stmt.executeQuery();
					rs.next();
					int days_delta = rs.getInt(1);
					rs.close();
					stmt.close();
					//determine price
					int price = 0;
					stmt = Login.getCon().prepareStatement("select type from rooms where roomnumber=?;");
					stmt.setInt(1, i);
					rs = stmt.executeQuery();
					rs.next();
					System.out.println(rs.getString(1));
					switch (rs.getString(1))
					    {
					    case "Single": price = 200;
					    break;
					    case "Double": price = 300;
					    break;
					    case "Triple": price = 400;
					    break;
					    case "Quad": price = 500;
					    break;
					    case "King": price = 1000;
					    break;
					    }
					System.out.println(price);
					rs.close();
					stmt.close();
					//Add to cashdesk
					stmt = Login.getCon().prepareStatement("INSERT INTO cashdesk"
							+ "(paymentid, roomno, daysnumber, roomprice, total) VALUES (DEFAULT, ?, ?, ?, ?)");
					stmt.setInt(1, i);
					stmt.setInt(2, days_delta);
					stmt.setInt(3, price);
					stmt.setInt(4, price * days_delta);
					stmt.executeUpdate();
					stmt.close();
					//Delete from guests
					stmt = Login.getCon().prepareStatement("DELETE FROM guests where roomno=?;");
					stmt.setInt(1, i);
					stmt.executeUpdate();
					stmt.close();
				    }
				}
				catch (SQLException e1) {
				e1.printStackTrace();
				}
			}

			private void errorLabel(String s) {
				jE = new JLabel(s);
		    	jE.setBounds(75, 120, 250, 20);
		    	jE.setForeground(Color.red);
		    	jE.setFont(new Font("Times New Roman", Font.BOLD, 12));
		    	contentPane.add(jE);
		    	contentPane.validate();
		    	contentPane.repaint();
			}
		});
		btnOk.setBounds(22, 187, 110, 25);
		contentPane.add(btnOk);
		
		JLabel lblCheckoutForm = new JLabel("CheckOUT FORM");
		lblCheckoutForm.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCheckoutForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckoutForm.setBounds(12, 12, 240, 61);
		contentPane.add(lblCheckoutForm);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
