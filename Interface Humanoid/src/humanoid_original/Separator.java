package humanoid_original;

import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JSeparator;

public class Separator {
	public void addSeparator(Container pane,int x, int y)
    {
		JSeparator sep = new JSeparator(JSeparator.VERTICAL);
		GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.VERTICAL;
	    c.weighty = 0;
	    c.gridx = x;
	    c.gridy = y;
	    c.fill = GridBagConstraints.VERTICAL;
	    c.gridheight = 8;
	    pane.add(sep,c);
    }
}
