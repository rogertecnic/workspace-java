package humanoid_original;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class TextArea {
	 public JTextArea addTextArea(Container pane, JTextArea textArea, int x, int y)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
			//c.gridwidth = 2;
			c.gridheight = 3;
			c.insets = new Insets(10,10,0,0);
		    pane.add(textArea, c);
		    
		    return textArea;
	    }
}
