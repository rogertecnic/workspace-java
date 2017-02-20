package humanoid_modificado.obj_estaticos_publicos_variados;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import humanoid_modificado.comunicacao_serial_arduino.ArduinoUsb;

public class ArduinoPanel{
	private static JButton botaoArduino = new JButton();
	private static ArduinoUsb arduino = null;
	private static JPanel panel = null;

	public static JPanel getArduinoPanel(){
		if(panel == null){
			panel = new JPanel(new FlowLayout());
			panel.setBorder(new TitledBorder("Comunicacao arduino:"));
			arduino = new ArduinoUsb(botaoArduino);
			panel.add(botaoArduino);
			panel.add(TodosOsCheckBox.enviarDadosArduino());
		}
		return panel;
	}

	public static ArduinoUsb arduino(){
		return arduino;
	}
}
