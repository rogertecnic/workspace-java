package exemplo_swingPanel;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.DropMode;

public class JP2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public JP2() {
		
		JTextArea txtrTextArea = new JTextArea();
		txtrTextArea.setText("enviar comando:");
		add(txtrTextArea);

	}

}
