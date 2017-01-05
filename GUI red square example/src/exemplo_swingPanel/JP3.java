package exemplo_swingPanel;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JToolBar;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SpringLayout;

public class JP3 extends JPanel {

	/**
	 * Create the panel.
	 */
	public JP3() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JMenuBar menubar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.WEST, menubar, 5, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, menubar, -5, SpringLayout.EAST, this);
		//add(menubar);
		
		JMenu menu1 = new JMenu("jmenu1 no JP3.");
		menubar.add(menu1);
		
		JMenu menu2 = new JMenu("jmenu2 no JP3.");
		menubar.add(menu2);
		
		JMenu menu3 = new JMenu("jmenu3 no JP3.");
		menubar.add(menu3);
		
	}

}
