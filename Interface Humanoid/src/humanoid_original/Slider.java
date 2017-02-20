package humanoid_original;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Slider {
	
    public GroupSlider addSlider(Container pane, String description, int x, int y,ID id,Arduino conn)
    {
    	JPanel panel = new JPanel();
    	TextField textField = new TextField();
    	Label label = new Label();
    	
    	panel = label.addLabel(panel, description, 0, 0);
        
        JSlider slider = new JSlider();
        slider.setOrientation(JSlider.HORIZONTAL);
        slider.setMinimum(0);
        slider.setMaximum(180);
        slider.setValue(90);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(45);
        slider.setMinorTickSpacing(5);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10,10,0,0);
        panel.add(slider, c);
        
        
        JTextField text =  textField.addTextField(panel, "90", 2, 0);
        textField.addListenerTextField(text,slider);
        text.setColumns(3);
   
        slider.addChangeListener(addListenerSlider(text,id,conn));
        
        //adicionando o panel o container
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = x;
        c.gridy = y;
        pane.add(panel, c);
        
        GroupSlider arg0 = new GroupSlider();
        arg0.setSlider(slider);
        arg0.setID(id);
        return arg0;
    }
    

    public ChangeListener addListenerSlider(JTextField textField, ID id, Arduino conn)
    {
    Protocolo protocolo = new Protocolo();
    ChangeListener listener = new ChangeListener()
    {
       public void stateChanged(ChangeEvent event)
       {
          // update text field when the slider value changes
          JSlider source = (JSlider) event.getSource();
          textField.setText("" + source.getValue());
          //enviar valor via serial se checkbox estiver selecionado
          if(Flags.CHECKBOX_SERIAL.isSelected()){
        	  conn.comunicacaoArduino(protocolo.addProtocolo((source.getValue()+""), id.getID()));
          }
       }
       
    };
    
    return listener;
    }
    
    public void atualizarSlider(Estado estado,List<GroupSlider> groupSlider){
		for(int i=0;i<groupSlider.size();i++){
			groupSlider.get(i).getSlider().setValue(estado.getEstadoValores()[i]);
		}
	}
}