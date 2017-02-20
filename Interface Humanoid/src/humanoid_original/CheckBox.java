package humanoid_original;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;

public class CheckBox {
	public JCheckBox addCheckBox(Container pane,JCheckBox checkBox, String description, int x, int y)
    {
		 
		 GridBagConstraints c = new GridBagConstraints();
		 c.fill = GridBagConstraints.HORIZONTAL;
		 c.gridx = x;
		 c.gridy = y;
		 c.insets = new Insets(10,10,0,0);
		 pane.add(checkBox, c);
		 
		 return checkBox;
    }
}
