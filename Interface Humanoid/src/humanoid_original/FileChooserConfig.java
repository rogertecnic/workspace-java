package humanoid_original;

 
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FileChooserConfig extends JPanel  {
	
	static private final String newline = "\n";
	JButton openButton, saveButton,saveHeaderFileConfig;
    JTextArea log;
	JFileChooser fc;
	JPanel middlePanel;
	
    public FileChooserConfig() {
    	middlePanel = new JPanel ();
        middlePanel.setBorder( new TitledBorder ( new EtchedBorder (), "" ) );
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        
        //Create a file chooser
        fc = new JFileChooser();
 
        //Uncomment one of the following lines to try a different
        //file selection mode.  The first allows just directories
        //to be selected (and, at least in the Java look and feel,
        //shown).  The second allows both files and directories
        //to be selected.  If you leave these lines commented out,
        //then the default mode (FILES_ONLY) will be used.
        //
        //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
        //Create the open button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        openButton = new JButton("Open a File...",
                                 createImageIcon("images/Open16.gif"));
        //openButton.addActionListener(this);
 
        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("Save a File...",
                                 createImageIcon("images/Save16.gif"));
        //saveButton.addActionListener(this);
        saveHeaderFileConfig = new JButton("Save a Header File Config...");
        //For layout purposes, put the buttons in a separate panel

        middlePanel.add(openButton);
        middlePanel.add(saveButton);
        middlePanel.add(saveHeaderFileConfig);
        
        
    }
 
    
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileChooser.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    public JButton getSalvar(){
    	return saveButton;
    }
    
    public JButton getCarregar(){
    	return openButton;
    }
    
    public JButton getSaveHeaderFileConfig(){
    	return saveHeaderFileConfig;
    }
 
    public JPanel getPanel(){
    	return middlePanel;
    }
   
  //Handle save button action.
    public void salvarArquivo(JButton button, List<Config> listConfig){  
  	  button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
        	 if (e.getSource() == saveButton) {
                int returnVal = fc.showSaveDialog(FileChooserConfig.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    file = new File( file.toString() + ".txt" );
                    try {
                    	FileWriter fileW = new FileWriter (file,false);//arquivo para escrita
                    	BufferedWriter buffW = new BufferedWriter (fileW);
                    	for(int i =0;i < listConfig.size();i++){
                    		buffW.write(listConfig.get(i).getId()+";");
                    		buffW.write(listConfig.get(i).getMAX_S().getText()+";");
                    		buffW.write(listConfig.get(i).getMIN_S().getText()+";");
                    		buffW.write(listConfig.get(i).getMAX_T().getText()+";");
                    		buffW.write(listConfig.get(i).getMIN_T().getText()+";");
                    		buffW.write(listConfig.get(i).getMAX_A().getText()+";");
                    		buffW.write(listConfig.get(i).getMIN_A().getText()+";");
                    			
                    		if(i < listConfig.size() -1)
                    			buffW.newLine();
                    	}
                    	
                    	buffW.close ();
                    	
    					file.createNewFile();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                    //This is where a real application would save the file.
                    if(Flags.CHECKBOX_LOG.isSelected())
                    Flags.LOG.append("Saving: " + file.getName() + "." + newline);
                } else {
                	if(Flags.CHECKBOX_LOG.isSelected())
                	Flags.LOG.append("Save command cancelled by user." + newline);
                }}}
        	
    });
        }
    
    public void openArquivo(JButton button, List<Config> listConfig){  
    	  button.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
          	
        	  //Handle open button action.
              if (e.getSource() == openButton) {
                  int returnVal = fc.showOpenDialog(FileChooserConfig.this);
                  
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      /*LEITURA*/
                      FileReader fileR;
					try {
						fileR = new FileReader(file);
						 BufferedReader buffR = new BufferedReader (fileR);//arquivo buferizado
						
	                      for(int i =0;i<14;i++){
	                    	  listConfig.get(i);
	                    	  
	                    	  String str = buffR.readLine();
	                    	  String array[] = str.split(";");
	                    	  
	                    	  listConfig.get(i).setId(Integer.valueOf(array[0]).intValue());
	                    	  listConfig.get(i).getMAX_S().setText(array[1]);
	                    	  listConfig.get(i).getMIN_S().setText(array[2]);
	                    	  listConfig.get(i).getMAX_T().setText(array[3]);
	                    	  listConfig.get(i).getMIN_T().setText(array[4]);
	                    	  listConfig.get(i).getMAX_A().setText(array[5]);
	                    	  listConfig.get(i).getMIN_A().setText(array[6]);
	                    	  
	                    	  
	                      }
	                      
	                      buffR.close ();
	                      
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//arquivo para ser lido
					catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                     
                      
                      //This is where a real application would open the file.
                      if(Flags.CHECKBOX_LOG.isSelected())
                      Flags.LOG.append("Opening: " + file.getName() + "." + newline);
                  } else {
                  	if(Flags.CHECKBOX_LOG.isSelected())
                  	Flags.LOG.append("Open command cancelled by user." + newline);
                  }
                  //Flags.LOG.setCaretPosition(log.getDocument().getLength());
       
              
              }
          
          }
          
          	
      });
    }
    
    public void sendArquivo(JButton button, List<Config> listConfig,Arduino conn){  
  	  button.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
           	Protocolo protocolo = new Protocolo();
           	
           	
           	
        	  for( int i = 0; i<listConfig.size();i++){
        		  String maxS = listConfig.get(i).getMAX_S().getText();
        		  String minS = listConfig.get(i).getMIN_S().getText();
        		  String maxT = listConfig.get(i).getMAX_T().getText();
        		  String minT = listConfig.get(i).getMIN_T().getText();
        		  String maxA = listConfig.get(i).getMAX_A().getText();
        		  String minA = listConfig.get(i).getMIN_A().getText();
        		  byte[] b = protocolo.addProtocoloConfig(listConfig.get(i).getId(), maxS, minS, maxT, minT, maxA, minA);
        		  System.out.println(new String(b));
        		  try {
					Thread.sleep (100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
        		  conn.comunicacaoArduino(b);
        	  }
        }
  	});
    }
    
    public void saveHeaderFile(JButton button, List<Config> listConfig){  
    	  button.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
          	 
                  int returnVal = fc.showSaveDialog(FileChooserConfig.this);
                  if (returnVal == JFileChooser.APPROVE_OPTION) {
                      File file = fc.getSelectedFile();
                      file = new File( file.toString() + ".h" );
                      try {
                      	FileWriter fileW = new FileWriter (file,false);//arquivo para escrita
                      	BufferedWriter buffW = new BufferedWriter (fileW);
                      	
                      	buffW.write("#ifndef CONFIG_H");
                      	buffW.newLine();
                      	buffW.write("#define CONFIG_H");
                      	buffW.newLine();
                      	   // guardas de cabeçalho, impedem inclusões cíclicas
                      	
                      	for(int i =0;i < listConfig.size();i++){
                      		
                      		buffW.write("#define MAX_S"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMAX_S().getText()).intValue());
                      		buffW.newLine();
                      		buffW.write("#define MIN_S"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMIN_S().getText()).intValue());
                      		buffW.newLine();
                      		buffW.write("#define MAX_T"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMAX_T().getText()).intValue());
                      		buffW.newLine();
                      		buffW.write("#define MIN_T"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMIN_T().getText()).intValue());
                      		buffW.newLine();
                      		buffW.write("#define MAX_A"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMAX_A().getText()).intValue());
                      		buffW.newLine();
                      		buffW.write("#define MIN_A"+listConfig.get(i).getId()+" "+Integer.valueOf(listConfig.get(i).getMIN_A().getText()).intValue());
                      		buffW.newLine();
                      	}
                      	
                      	buffW.write("#endif");
                      	buffW.close ();
                      	
      					file.createNewFile();
      				} catch (IOException e1) {
      					// TODO Auto-generated catch block
      					e1.printStackTrace();
      				}
                      //This is where a real application would save the file.
                      if(Flags.CHECKBOX_LOG.isSelected())
                      Flags.LOG.append("Saving: " + file.getName() + "." + newline);
                  } else {
                  	if(Flags.CHECKBOX_LOG.isSelected())
                  	Flags.LOG.append("Save command cancelled by user." + newline);
                  }}
          	
      });
   }

}