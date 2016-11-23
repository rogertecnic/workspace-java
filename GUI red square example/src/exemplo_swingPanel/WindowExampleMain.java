package exemplo_swingPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class WindowExampleMain {

	private JFrame frmJanelaTeste;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowExampleMain window = new WindowExampleMain();
					window.frmJanelaTeste.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WindowExampleMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJanelaTeste = new JFrame();
		frmJanelaTeste.setTitle("Janela teste");
		frmJanelaTeste.setBounds(100, 100, 450, 300);
		frmJanelaTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmJanelaTeste.getContentPane().setLayout(springLayout);
		
		JP1 jp1 = new JP1();
		springLayout.putConstraint(SpringLayout.NORTH, jp1, 5, SpringLayout.NORTH, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, jp1, 5, SpringLayout.WEST, frmJanelaTeste.getContentPane());
		JP2 jp2 = new JP2();
		springLayout.putConstraint(SpringLayout.NORTH, jp2, 6, SpringLayout.SOUTH, jp1);
		springLayout.putConstraint(SpringLayout.WEST, jp2, 0, SpringLayout.WEST, jp1);
		JP3 jp3 = new JP3();
		springLayout.putConstraint(SpringLayout.NORTH, jp3, 6, SpringLayout.SOUTH, jp2);
		springLayout.putConstraint(SpringLayout.WEST, jp3, 10, SpringLayout.WEST, frmJanelaTeste.getContentPane());
		frmJanelaTeste.getContentPane().add(jp1);
		frmJanelaTeste.getContentPane().add(jp2);
		frmJanelaTeste.getContentPane().add(jp3);
		
	}

}
