package debug_gui;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * Campo onde sera digitado todo comando a ser enviado para o brick;
 * @author Rogerio
 *
 */
public class ConsoleFieldSender extends JTextField{
	public static String strsender;
	
	public ConsoleFieldSender(){
		super();
		strsender = "";
		setToolTipText("Mensagens para enviar para o robo");
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER ){
					strsender = getText();
					setText("");
					System.out.println(strsender);
					ConsoleFieldReceiver.receiver = strsender;
				}
			}
		});
	}
}
