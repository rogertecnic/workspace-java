package humanoid;

import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Label {
	public void addLabel(Container pane, String description, int x, int y)
    {
		JLabel label = new JLabel(description);
	    GridBagConstraints c = new GridBagConstraints();
	    c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = x;
	    c.gridy = y;
	    pane.add(label, c);
    }
	
	public JPanel addLabel(JPanel pane, String description, int x, int y)
    {
		JLabel label = new JLabel(description);
	    GridBagConstraints c = new GridBagConstraints();
	    c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = x;
	    c.gridy = y;
	    pane.add(label, c);
	    
	    return pane;
    }
}
