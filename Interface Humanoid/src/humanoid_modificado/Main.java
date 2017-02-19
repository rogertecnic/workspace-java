package humanoid_modificado;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import humanoid_modificado.obj_estaticos_publicos_variados.ArduinoPanel;
public class Main {
	public static void main(String[] args) {

		JFrame frame = new JFrame("HUMANOID");
		//frame.setResizable(false);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new Janela(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
		
		String s = "";
		while(true){
			try {
				s = ArduinoPanel.arduino().read();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(!s.equals("")){
				System.out.print(s);
				s = "";
			}
		}
	}
}