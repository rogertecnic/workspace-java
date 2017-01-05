package exemplo_swingPanel;

import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class BarraMenuPrincipal extends JMenuBar {
	public BarraMenuPrincipal(){
		
		JMenu menu1 = new JMenu("jmenu1 no JP3.");
		add(menu1);
		
		JMenu menu2 = new JMenu("jmenu2 no JP3.");
		add(menu2);
		
		JMenu menu3 = new JMenu("jmenu3 no JP3.");
		add(menu3);
		setMinimumSize(new Dimension(25, 15));
	}
}
