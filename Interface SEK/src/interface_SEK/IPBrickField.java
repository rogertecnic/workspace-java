package interface_SEK;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * Campo onde se deve digitar o ip do brick por padrao 10.0.1.1.
 * @author Rogerio
 *
 */
public class IPBrickField extends JTextField{
	private static String ip = "10.0.1.1";
	
	public IPBrickField(){
		super(ip);
		this.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					ip = getText();
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
	}
	
	/**
	 * SingleTone design pattern
	 */
//	public static IPBrickField newinstance(String ip){
//		if(textfieldip == null){
//			textfieldip = new IPBrickField(ip);
//		return new IPBrickField(ip);
//		}else return textfieldip;
//	}
	
	public static String getip(){
		return ip;
	}
}
