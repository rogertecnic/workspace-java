package lib_comunicacao_serial_arduino;

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
		JButton button = new JButton();
		ArduinoUsb arduino = new ArduinoUsb(button);
		janela.getContentPane().add(button, BorderLayout.CENTER);
		JTextField t = new JTextField("");
		t.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

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
		int send = -256;
		while(!arduino.estadoComunicacao()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		while(true){
//			String b = Integer.toBinaryString(send);
//			char[] c = null;
//			if(send>=0)
//				c = Character.toChars(send);
//			String s = Integer.toString(send);
//			byte[] arrayByte = s.getBytes();
//			
//			
//			
//			
//			System.out.println("int:"+send);
//			System.out.println("ToBinary:"+b);
//			
//			System.out.print("ToChar[]:{");
//			if(c != null)
//			for(int tmp = 0; tmp < c.length; tmp++){
//				System.out.print(c[tmp]);
//				if(tmp<c.length-1)System.out.print(", ");
//			}
//			System.out.println("}");
//			
//			System.out.println("ToString:"+s + ", Length:"+s.length());
//			System.out.print("StringToByte[]:{");
//			for(int tmp = 0; tmp < arrayByte.length; tmp++){
//				System.out.print(arrayByte[tmp]);
//				if(tmp<arrayByte.length-1)System.out.print(", ");
//			}
//			System.out.println("}");
			
			
//			arduino.write(Integer.toString(send));
//			send ++;
			String s = "";
			try {
				s = arduino.read();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
//
			if(!s.equals("")){
//				char[] c = null;
//				int i = Integer.parseInt(s);
//				if(i>=0)
//				c = Character.toChars(i);
//				String b = Integer.toBinaryString(i);
//				
//
//				System.out.println("int:"+i);
				System.out.println("String:"+s + ", Length:"+s.length());
//				System.out.println("binary:"+b);
//				System.out.print("char[]:{");
//				if(c != null)
//				for(int tmp = 0; tmp < c.length; tmp++){
//					System.out.print(c[tmp]);
//					if(tmp<c.length-1)System.out.print(", ");
//				}
//				System.out.println("}");
			}
		}

	}
}
