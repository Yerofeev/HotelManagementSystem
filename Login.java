import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.sql.*;

public class Login implements ActionListener {
	
	JFrame jfrm;
	JButton jbtn1, jbtn2;
	JLabel jlab, jlabelU, jlabelP, jlabelE;
	JList<String> jlst;
	JScrollPane jscr;
	int buttonClicked;
	JTextField jtextUser;
	JPasswordField jtextPassword;
	String[] rooms = {"Single", "Double", "Lux", "President", "Econom", "Family", "Studio", "Appartment"};
	volatile static Connection con; 
	
	public static Connection getCon() {
		return con;
	}
	
	Login() 
	{
		try {
			BufferedImage img = ImageIO.read(new File("/home/user/Downloads/h2.jpg"));
			jfrm = new JFrame("Welcome to Y-Hotel");
			//set image
			jfrm.setContentPane(new JLabel(new ImageIcon(img)));
            jfrm.pack();
			jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jfrm.setSize(500, 300);
			//Buttons ----------------------------------------------
			jbtn1 = new JButton("Login");
			try {
			    Image img_login = ImageIO.read(new File("/home/user/Downloads/green.png"));
			    Image img_login_small = img_login.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  //resize icon
			    jbtn1.setIcon(new ImageIcon(img_login_small));
			  } catch (IOException ex) {
			   ex.printStackTrace();
			  }
			jbtn1.addActionListener(this);
			jfrm.add(jbtn1);
			//Button #2
			jbtn2 = new JButton("Exit");
			try {
			    Image img_login = ImageIO.read(new File("/home/user/Downloads/red.png"));
			    Image img_login_small = img_login.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  //resize icon
			    jbtn2.setIcon(new ImageIcon(img_login_small));
			  } catch (IOException ex) {
			   ex.printStackTrace();
			  }
			jbtn2.addActionListener(this);
			jfrm.add(jbtn2);
			//Text fields  ---------------------------------------
			jlabelU = new JLabel("UserName");
			jtextUser = new JTextField(15);
			jtextUser.setActionCommand("user");
			jtextUser.addActionListener(this);
			jfrm.add(jlabelU);
			jfrm.add(jtextUser);
			jlabelP = new JLabel("Password");
			jtextPassword = new JPasswordField(15);
			jtextPassword.setActionCommand("password");
			jtextPassword.addActionListener(this);
			jfrm.add(jtextPassword);
			jfrm.add(jlabelP);
			//Set position  ---------------------------------------
			jlabelU.setBounds(60, 160, 70, 20);
			jlabelP.setBounds(60, 200, 70, 20);
			//jbtn4.setBounds(300, 120, 70, 20);
			jtextUser.setBounds(60, 180, 150, 20);
			jtextPassword.setBounds(60, 220, 150, 20);
			jbtn1.setBounds(380, 220, 100, 30);
			jbtn2.setBounds(380, 20, 100, 30);
		    // Set icon
			ImageIcon icon =  new ImageIcon("/home/user/Downloads/hotel.jpeg");
			jfrm.setIconImage(icon.getImage());
			//Set screen's position
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dim = tk.getScreenSize();
			int xpos = (dim.width / 2) - (jfrm.getWidth() / 2);
			int ypos = (dim.height / 2) - (jfrm.getHeight() / 2);
			jfrm.setLocation(xpos, ypos);
			//SetVisible
			jfrm.setResizable(false);
			jfrm.setVisible(true);
		}
		catch (IOException exp) {
            exp.printStackTrace();
		}
	}
	
	public void actionPerformed(java.awt.event.ActionEvent e) {
		if (e.getActionCommand().equals("Login")) {
			
			try
			{
				con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Hotel", jtextUser.getText(), new String((jtextPassword.getPassword())));
			}
			catch (SQLException ex) {}
		    if (con != null)
		    {	
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run(){
						new ControlPanel();
					}
				});
				jfrm.dispose();
		    }
		    else
		    {
		    	jlabelE = new JLabel("Wrong Login Or Password");
		        jlabelE.setBounds(60, 240, 250, 20);
		        jlabelE.setForeground(Color.red);
		        jlabelE.setFont(new Font("Times New Roman", Font.BOLD, 16));
		        jfrm.add(jlabelE);
		        jfrm.validate();
		        jfrm.repaint();
		    }
	    }
		else if (e.getActionCommand().equals("Exit"))
		{
            System.exit(0);
		};
	}
}

