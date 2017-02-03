package humanoid;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Button {
	 public JButton addButton(Container pane, String description, int x, int y)
	    {
		 	JButton button = new JButton(description);
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c);
		    
		    return button;
	    }
	 
	 public JButton addButton(Container pane, String description, int x, int y,int w)
	    {
		 	JButton button = new JButton(description);
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.gridwidth = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c);
		    
		    return button;
	    }
	 
	 public JButton addButton(Container pane, JButton button, int x, int y)
	    {
		 	GridBagConstraints c = new GridBagConstraints();
		    c.fill = GridBagConstraints.HORIZONTAL;
		    c.gridx = x;
		    c.gridy = y;
		    c.insets = new Insets(10,10,0,0);
		    pane.add(button, c); 
		    return button;
	    }
   
      
      public void connectSerial(JButton button, Arduino conn){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	  String porta = JOptionPane.showInputDialog("Porta");
            	  String taxa = JOptionPane.showInputDialog("Taxa de Transmissão");  	  
            	  conn.conectar(porta,Integer.valueOf(taxa).intValue());
             }
          });
    	  
    	  
    }
      
 public void desconnectSerial(JButton button, Arduino conn){
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	    conn.close();
             }
          });    	  
    	
    }
 
      public void EnviarDados(JButton button, Arduino conn,List<GroupSlider> listGroupSlider){
    	  Protocolo protocolo = new Protocolo();
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	  for( int i = 0; i<listGroupSlider.size();i++){
            		  
            		  String source = listGroupSlider.get(i).getSlider().getValue()+"";
            		  int id = listGroupSlider.get(i).getID().getID();
            		  
            		  try {
						Thread.sleep (100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  
            		  conn.comunicacaoArduino(protocolo.addProtocolo(source,id));
            	  }
              }
             
          });
    	  
    	  
    }
      
      public void addEstado(JButton button,  JComboBox comboBox,List<GroupSlider> listGroupSlider,List<Estado> listEstado){
    	  
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	  String nome = JOptionPane.showInputDialog("Digite o nome do estado");
            	  if(nome !=null){
            	  Slider slider = new Slider();
            	  Estado estado = new Estado();
            	  estado = estado.addEstado(listEstado,listGroupSlider,nome);
            	  comboBox.addItem(nome);
            	  comboBox.setSelectedItem(nome);
            	  slider.atualizarSlider(estado,listGroupSlider);
            	  }
              }
             
          });
    	  	  
    }  
      
public void removeEstado(JButton button,  JComboBox comboBox,List<Estado> listEstado){
    	  
    	  
    	  button.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(ActionEvent e) {
            	  if(!comboBox.getSelectedItem().toString().equals("default")){ 
            		  int size = comboBox.getItemCount();
            		  String nome = comboBox.getSelectedItem().toString();
            		  List<String> estados = new ArrayList<String>();
            		  
            		  for(int i = 0;i < size;i++){
            			   estados.add(comboBox.getItemAt(i).toString());
            		  }
            		  
            		  estados.remove(comboBox.getSelectedItem().toString());
            		  comboBox.removeAllItems();
            		  for(int i = 0;i < estados.size();i++){
            			  comboBox.addItem(estados.get(i));
            		  }
            		  comboBox.setSelectedItem("default");
            		  Estado estado = new Estado();
            		  estado.removeEstado(listEstado,nome);
            		  
            		  
            	  }
            	  else
            		  JOptionPane.showMessageDialog(null, "Não é possível remover esse estado");
              }
             
          });
    }    
	
public void editarEstado(JButton button,  JComboBox comboBox,List<GroupSlider> listGroupSlider,List<Estado> listEstado){
	  
	  
	  button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
      	  if(!comboBox.getSelectedItem().toString().equals("default")){ 
      		if(comboBox.getSelectedItem() !=null){ 
      				String nome = comboBox.getSelectedItem().toString();
      				Estado estado = new Estado(); 
      				estado = estado.getEstado(listEstado,nome);
      				estado.atualizareStado(estado, listGroupSlider);
      		}
      		
      		
      	  }
      	  else
      		  JOptionPane.showMessageDialog(null, "Não é possível editar esse estado");
        }
       
    });
}

public void limparLog(JButton button, JTextArea log){  
	  button.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
    	  log.setText("");
      }
     
  });
}

	



public void calibrar(JButton button,JanelaC janela,Arduino conn){
	  button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
    	//creating and showing this application's GUI.
    	  javax.swing.SwingUtilities.invokeLater(new Runnable() {
    	      public void run() {
    	      	UIManager.put("swing.boldMetal", Boolean.FALSE);
    	      	janela.createAndShowGUI(conn);
    	           
    	      }
    	  });
      }
	 });
	} 
}