package comunicacao_serial_arduino;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class MainClass {


	public static void main(String[] args){
		JFrame janela = new JFrame("testando comunicacao com o arduino");
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(new BorderLayout());
		JButton b = new JButton();
		ArduinoUsb arduino = new ArduinoUsb(b);
		janela.getContentPane().add(b, BorderLayout.CENTER);
		JTextField t = new JTextField("digite aqui e de enter, ao finao do loop do arduino ele ira ler");
		t.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					arduino.write(t.getText());
					t.setText("");
				}
			}
		});
		janela.getContentPane().add(t, BorderLayout.SOUTH);
		
		janela.pack();
		
		while(true){
				String a = "";
				try {
					a = arduino.read();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(!a.equals(""))
				System.out.println(a);
			
		}
		
	}
}
