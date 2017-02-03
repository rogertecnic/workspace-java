package humanoid;

import javax.swing.JFrame;
import javax.swing.UIManager;
public class Main {
	
    private static void createAndShowGUI() {
    	
        //Create and set up the window.
        JFrame frame = new JFrame("HUMANOID");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Janela janela = new Janela();
        
        //Set up the content pane.
        janela.addComponentsToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	 public static void main(String[] args) {
		 
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	UIManager.put("swing.boldMetal", Boolean.FALSE);
	            	createAndShowGUI();
	                 
	            }
	        });
	 }
}