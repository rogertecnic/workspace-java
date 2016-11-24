package exemplo_swingPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import java.awt.Dimension;

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
		frmJanelaTeste.setBounds(100, 100, 450, 411);
		frmJanelaTeste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmJanelaTeste.getContentPane().setLayout(springLayout);
		
		BarraMenuPrincipal menuprincipal = new BarraMenuPrincipal();
		frmJanelaTeste.setJMenuBar(menuprincipal);
		
		JPanelConsole jpConsole = new JPanelConsole();
		springLayout.putConstraint(SpringLayout.NORTH, jpConsole, 5, SpringLayout.NORTH, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, jpConsole, 5, SpringLayout.WEST, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, jpConsole, 300, SpringLayout.NORTH, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, jpConsole, 200, SpringLayout.WEST, frmJanelaTeste.getContentPane());
		frmJanelaTeste.getContentPane().add(jpConsole);
		
		JP2 jp2 = new JP2();
		springLayout.putConstraint(SpringLayout.NORTH, jp2, 6, SpringLayout.SOUTH, jpConsole);
		springLayout.putConstraint(SpringLayout.WEST, jp2, 0, SpringLayout.WEST, jpConsole);
		frmJanelaTeste.getContentPane().add(jp2);
		
		JP3 jp3 = new JP3();
		springLayout.putConstraint(SpringLayout.NORTH, jp3, 59, SpringLayout.NORTH, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, jp3, 65, SpringLayout.EAST, jpConsole);
		springLayout.putConstraint(SpringLayout.SOUTH, jp3, -96, SpringLayout.SOUTH, frmJanelaTeste.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, jp3, 0, SpringLayout.EAST, frmJanelaTeste.getContentPane());
		frmJanelaTeste.getContentPane().add(jp3);
		
	}

}
