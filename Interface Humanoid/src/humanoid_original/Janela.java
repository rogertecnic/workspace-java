package humanoid_original;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Janela {
	
    //public static void addComponentsToPane(Container pane) {   	
    public Janela(Container pane){	
    //Janela
    pane.setLayout(new GridBagLayout());
    Slider slider = new Slider();
    ComboBox comboBox = new ComboBox();
    Label label = new Label(); 
    Separator separator = new Separator();
    Button button= new Button();
    CheckBox checkBox = new CheckBox();
    Arduino conn =  new Arduino();
    List<GroupSlider> listGroupSlider = new ArrayList<GroupSlider>();
    GroupSlider groupSlider = new GroupSlider();
    List<Estado> listEstado = new ArrayList<Estado>();
    TextArea textArea = new TextArea();
    Panel panel = new Panel();
    FileChooser fc = new FileChooser();
    
    
   checkBox.addCheckBox(pane,Flags.CHECKBOX_SERIAL, "SERIAL", 4, 8);
   checkBox.addCheckBox(pane,Flags.CHECKBOX_LOG, "LOG", 4, 7);
    //ComboBox
    JComboBox cb = new JComboBox();
	cb.addItem("default");
    comboBox.addListenerComboBox( cb, listGroupSlider, listEstado);
    JPanel p = new JPanel();
    p = panel.addComponnetPanel(p,cb, "Estados");
    panel.addPanel(pane, p, 4, 3,2);
    
    label.addLabel(pane, "PERNA ESQUERDA", 0, 0);
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 00", 0, 1,new ID(0),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 01", 0, 2,new ID(1),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 02", 0, 3,new ID(2),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 03", 0, 4,new ID(3),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 04", 0, 5,new ID(4),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 05", 0, 6,new ID(5),conn));
   
    label.addLabel(pane, "PERNA DIREITA", 2, 0);
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 06", 2, 1,new ID(6),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 07", 2, 2,new ID(7),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 08", 2, 3,new ID(8),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 09", 2, 4,new ID(9),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 10", 2, 5,new ID(10),conn));
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 11", 2, 6,new ID(11),conn));
    
    label.addLabel(pane, "BRAÇO ESQUERDO", 0, 7); 
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 12", 0, 8,new ID(12),conn));
    separator.addSeparator(pane, 1, 1);
    label.addLabel(pane, "BRAÇO DIREITO", 2, 7);
    groupSlider.addGroupSlider(listGroupSlider,slider.addSlider(pane, "MOTOR 13", 2, 8,new ID(13),conn));
    separator.addSeparator(pane, 3, 1);
    JButton b = button.addButton(pane, "Adicionar", 4, 1);
    button.addEstado(b,cb,listGroupSlider,listEstado);
    b =button.addButton(pane, "Deletar", 5,1);
    button.removeEstado(b,cb,listEstado);
    b = button.addButton(pane, "Editar", 4,2,2);
    button.editarEstado(b, cb,listGroupSlider, listEstado);
    
    b = button.addButton(pane, "Enviar Dados",5,7);
    button.EnviarDados(b,conn,listGroupSlider);
    
    b = button.addButton(pane, "Conectar",6,8);
    button.connectSerial(b,conn);
    
    b = button.addButton(pane, "Desconectar",5,8);
    button.desconnectSerial(b,conn);
        
    button.addButton(pane, fc.getSalvar(), 6,1);
    fc.salvarArquivo(fc.getSalvar(), listEstado);
    
    button.addButton(pane, fc.getCarregar(), 6,2);
    fc.openArquivo(fc.getCarregar(), listEstado,cb);
    
    panel.addPanel(pane, fc.getPanel(), 6, 3);
    
    p = fc.getPanel();
    
    b = button.addButton(pane, "Limpar Log", 6,7);
    button.limparLog(b,Flags.LOG);
    
    //
    b = button.addButton(pane, "Calibrar",5,6);
    button.calibrar(b, new JanelaC(),conn);
    
    //
    b = button.addButton(pane, "Save Header File Estados ...",4,6);
    fc.saveHeaderFile(b,listEstado);
    
    }
    
   /* public void ordenarId( TablePanel<Estado> listEstado){
    	 TablePanel<Estado> temp = new ArrayList<>();
    	for(int i = 0;i<13;i++){
    		for(int j = 0;i<13;j++){
    			if(listEstado.get(j). == i)
    			temp.add(listEstado.get(i));
    		}
    	}
    	
    }*/
}