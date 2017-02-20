package humanoid_original;

import javax.swing.JFrame;
public class Main {
	public static void main(String[] args) {

		JFrame frame = new JFrame("HUMANOID");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new Janela(frame.getContentPane());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}