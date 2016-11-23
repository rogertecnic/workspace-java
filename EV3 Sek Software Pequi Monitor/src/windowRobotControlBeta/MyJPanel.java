package windowRobotControlBeta;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MyJPanel extends JPanel {

	public MyJPanel() {
		setBackground(Color.red); // branco
		Dimension dimensao = new Dimension(300, 500);
		//setPreferredSize(dimensao);
		//setMinimumSize(dimensao);
		setBorder(BorderFactory.createLineBorder(Color.black, 3));
		// TODO criar os JPanel
	}

}
