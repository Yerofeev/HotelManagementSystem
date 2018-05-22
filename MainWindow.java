import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainWindow {
	JFrame frame;
	MainWindow(){
	System.out.println("OK");
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
    frame.setSize(500, 300);
    frame.setVisible(true);
	}
}