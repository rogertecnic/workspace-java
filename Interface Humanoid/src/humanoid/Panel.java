package humanoid;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Panel {
	 public JPanel addPanel(Container pane, JPanel panel, int x, int y)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
			//c.gridwidth = 2;
			c.gridheight = 4;
			c.insets = new Insets(10,10,0,0);
		    pane.add(panel, c);
		    
		    return panel;
	    }
	 
	 public JPanel addPanel(Container pane, JPanel panel, int x, int y,int w)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.gridwidth = w;
			c.insets = new Insets(10,10,0,0);
		    pane.add(panel, c);
		    
		    return panel;
	    }
	 
	 
	 public JPanel addComponnetPanel(JPanel panel,JComboBox obj,String nome)
	    {
		 JPanel middlePanel = new JPanel ();
		 middlePanel.setBorder( new TitledBorder ( new EtchedBorder (), nome ) );
		 // Define a altura e largura
		 obj.setPreferredSize(new Dimension(200, 30));
		 middlePanel.add(obj);
		 return middlePanel;
	 }
}
