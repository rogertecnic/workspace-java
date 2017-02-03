package humanoid;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JanelaC {
    
	public static void addComponentsToPane(Container pane,Arduino conn) {   	
    	
		
	List<Config> listConfig = new ArrayList<Config>();
	FileChooserConfig fc = new FileChooserConfig();
	
    //Janela
		
    pane.setLayout(new GridBagLayout());
    JanelaC janelaC = new JanelaC();
    janelaC.addToButtonPane(pane, fc.getSalvar(), 0, 0, 0, 0);
    janelaC.addToButtonPane(pane, fc.getCarregar(), 1, 0, 0, 0);
    janelaC.addToButtonPane(pane, fc.getSaveHeaderFileConfig(), 2, 0, 0, 0);
    fc.salvarArquivo(fc.getSalvar(),listConfig);
    fc.openArquivo(fc.getCarregar(), listConfig);
    fc.saveHeaderFile(fc.getSaveHeaderFileConfig(), listConfig);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor0",listConfig,0),0, 1, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor1",listConfig,1),0, 2, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor2",listConfig,2),0, 3, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor3",listConfig,3),0, 4, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor4",listConfig,4),0, 5, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor5",listConfig,5),0, 6, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor6",listConfig,6),0, 7, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor7",listConfig,7),0, 8, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor8",listConfig,8),0, 9, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor9",listConfig,9),0, 10, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor10",listConfig,10),0, 11, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor11",listConfig,11),0, 12, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor12",listConfig,12),0, 13, 3, 0);
    janelaC.addToPanelPane(pane, janelaC.addPanelTextField("Motor13",listConfig,13),0, 14, 3, 0);
     
    }
	
	public JPanel addPanelTextField(String description,List<Config> listConfig,int id){
		
		Config config = new Config(id);
		TextField t = new TextField();
		
		
		JPanel middlePanel = new JPanel ();
		middlePanel.setBorder( new TitledBorder ( new EtchedBorder (), description) );
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
	    
		JTextField text = new JTextField();
		JLabel label = new JLabel("MAX_S");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMAX_S(text);
		t.addListenerTextField(text);
		text.setText("0");
		
	    text = new JTextField();
		label = new JLabel("MIN_S");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMIN_S(text);
		t.addListenerTextField(text);
		text.setText("0");
		
		text = new JTextField();
		label = new JLabel("MAX_T");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMAX_T(text);
		t.addListenerTextField(text);
		text.setText("0");
		
		text = new JTextField();
		label = new JLabel("MIN_T");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMIN_T(text);
		t.addListenerTextField(text);
		text.setText("0");
		
		text = new JTextField();
		label = new JLabel("MAX_A");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMAX_A(text);
		t.addListenerTextField(text);
		text.setText("0");
		
		text = new JTextField();
		label = new JLabel("MIN_A");
		middlePanel.add (label);
		middlePanel.add (text);
		text.setColumns(3);
		config.setMIN_A(text);
		t.addListenerTextField(text);
		text.setText("0");
		
		listConfig.add(config);
		
		return middlePanel;
	}
	
	public void addToPanelPane(Container pane,JPanel panel,int x,int y,int w,int h){
		
		GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = x;
	    c.gridy = y;
		c.gridwidth = w;
		//c.gridheight = 4;
		c.insets = new Insets(10,10,0,0);
	    pane.add(panel, c);
	    
	}
	
	public void addToButtonPane(Container pane,JButton button,int x,int y,int w,int h){
		
		GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = x;
	    c.gridy = y;
		//c.gridwidth = w;
		//c.gridheight = 4;
		c.insets = new Insets(10,10,0,0);
	    pane.add(button, c);
	    
	}
	
	
public void createAndShowGUI(Arduino conn) {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("HUMANOID-CONFIG");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JanelaC janela = new JanelaC();
        
        //Set up the content pane.
        janela.addComponentsToPane(frame.getContentPane(),conn);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
