package humanoid_original;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class TextField {
	
	public JTextField addTextField(JPanel pane,String value,int x, int y)
    {
		 JTextField textfield = new JTextField();
	     textfield.setText(value);
	     GridBagConstraints c = new GridBagConstraints();
	     c.fill = GridBagConstraints.HORIZONTAL;
	     c.gridx = x;
	     c.gridy = y;
	     c.insets = new Insets(10,10,0,0);
	     pane.add(textfield, c);
	     
		 return textfield;
    }
	
	public  JTextField addListenerTextField(JTextField text ,JSlider jslider)
    {
    	// Listen for changes in the text
    	
    	text.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent ev) {
    		// código do evento:
    	  if(text.getText().trim().equals("")){
    		  text.setText("0");
    	  }
    	
    		String caracteres="0987654321";
    		       if(!caracteres.contains(ev.getKeyChar()+"")){
    		              ev.consume();
    		       }
    		       
    		       if (text.getText().length() >= 3 ) // limit textfield to 3 characters
     		    	  ev.consume();
    		       
    		       if (Integer.parseInt(text.getText()) > 180 ){ // limit textfield to 3 characters
    		    	  text.setText("180");    		          
    		       }
    		       
    		       
    		       
    		}
    	});
    	
    	text.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent ev) {
    	
    			if (ev.getKeyCode()==KeyEvent.VK_ENTER)
    				jslider.setValue(Integer.parseInt(text.getText()));
	       
    		}
    	});
    	
    	return text;  
    } 
	
	
	public  JTextField addListenerTextField(JTextField text )
    {
    	// Listen for changes in the text
    	
    	text.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent ev) {
    		// código do evento:
    	  if(text.getText().trim().equals("")){
    		  text.setText("0");
    	  }
    	
    		String caracteres="0987654321";
    		       if(!caracteres.contains(ev.getKeyChar()+"")){
    		              ev.consume();
    		       }
    		       
    		       if (text.getText().length() >= 3 ) // limit textfield to 3 characters
     		    	  ev.consume();
    		       
    		}
    	});
    	
    	
    	return text;  
    }   
	
}
