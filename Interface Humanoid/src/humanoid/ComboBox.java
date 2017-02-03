package humanoid;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ComboBox {
	
	 public JComboBox addComboBox(Container pane,int x, int y)
	    {
		  // JComboBox
		  
		  JComboBox comboBox = new JComboBox();
		  comboBox.addItem("default");
		   
		  GridBagConstraints c = new GridBagConstraints();
		  c.fill = GridBagConstraints.HORIZONTAL;
		  c.gridx = x;
		  c.gridy = y;
		  c.weightx = 0;
		  c.gridwidth = 2;
		  c.insets = new Insets(10,10,0,0);
		  pane.add(comboBox, c);
		   
		  return comboBox;
	    }
	 
	 
	 
	 
	 public void addListenerComboBox( JComboBox comboBox,List<GroupSlider> listGroupSlider,List<Estado> listEstado){
   	  
		 comboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent event) {
	        	 if(comboBox.getSelectedItem() !=null){
	        		 if(!comboBox.getSelectedItem().toString().equals("default")){
	        			 Estado estado = new Estado();
	        			 String nome = comboBox.getSelectedItem().toString();
	        			 Slider slider = new Slider();
	        			 slider.atualizarSlider(estado.getEstado(listEstado, nome), listGroupSlider);
	        		 }
	        	 }
	         }
	     });
   }      
 
}
