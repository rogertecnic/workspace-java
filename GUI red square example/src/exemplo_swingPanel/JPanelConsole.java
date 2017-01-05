package exemplo_swingPanel;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class JPanelConsole extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelConsole() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JTextPane txtpnConsoleSerAqui = new JTextPane();
		springLayout.putConstraint(SpringLayout.NORTH, txtpnConsoleSerAqui, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtpnConsoleSerAqui, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnConsoleSerAqui, 300, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, txtpnConsoleSerAqui, 450, SpringLayout.WEST, this);
		txtpnConsoleSerAqui.setText("console ser\u00E1 aqui!!!");
		add(txtpnConsoleSerAqui);

	}

}
