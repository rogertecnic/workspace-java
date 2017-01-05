package windowRobotControlBeta;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class MainWindow {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = new MainWindow("Juvenal");
				window.createAndShowGUI();
			}
		});
	}
	
	/**
	 * Constructor
	 * @param robotName
	 */
	public MainWindow(String robotName){
		this.robotName = robotName;
	}
	
	String robotName;
	
	private void createAndShowGUI() {
		System.out.println("Created GUI on EDT? "+
				SwingUtilities.isEventDispatchThread());
		JFrame jFrame = new JFrame("Windows monitor robot " + robotName);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		jFrame.getContentPane().setLayout(springLayout);
		MyJPanel myJPanel = new MyJPanel();
		springLayout.putConstraint(SpringLayout.NORTH, myJPanel, 2, SpringLayout.NORTH, jFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, myJPanel, 1, SpringLayout.WEST, jFrame.getContentPane());
		jFrame.getContentPane().add(myJPanel);
		
		// adicionar o layout manager, usar o springlayout
		//jFrame.getContentPane().setLayout(mgr);
		//jFrame.add(new MyPanel());
		//jFrame.pack();
		jFrame.setVisible(true);
	} 
}
