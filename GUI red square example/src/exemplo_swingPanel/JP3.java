package exemplo_swingPanel;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JToolBar;
import javax.swing.JButton;

public class JP3 extends JPanel {

	/**
	 * Create the panel.
	 */
	public JP3() {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		add(toolBar);
		
		JButton btnButtonTollbar = new JButton("button tollbar");
		toolBar.add(btnButtonTollbar);
		
		JButton btnNewButton = new JButton("button tollbar");
		toolBar.add(btnNewButton);

	}

}
