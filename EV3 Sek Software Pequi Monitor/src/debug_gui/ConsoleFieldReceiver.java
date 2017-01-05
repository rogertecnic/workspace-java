package debug_gui;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Campo onde exibira as mensagens recebidas do brick.
 * @author Rogerio
 *
 */
public class ConsoleFieldReceiver extends JTextArea implements Runnable{
	public static String receiver = "";
	private static String log = "";
	
	public ConsoleFieldReceiver(){
		super();
		setToolTipText("Mensagens enviadas pelo robo");
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		Thread updateFileld = new Thread(this);
		updateFileld.start();
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(!receiver.equals("")){
			log = this.getText();
			this.setText(log + receiver + "\n");
			receiver = "";
			}
		}
		
	}
	
	
}
