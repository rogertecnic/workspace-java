package exemplo_swingPanel;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JP1 extends JPanel {
	private JTextField txtJtextfield;

	/**
	 * Create the panel.
	 */
	public JP1() {
		
		JLabel lblJlabel = new JLabel("JLabel");
		add(lblJlabel);
		
		txtJtextfield = new JTextField();
		txtJtextfield.setText("JTextField do JP1");
		add(txtJtextfield);
		txtJtextfield.setColumns(11);

	}

}
