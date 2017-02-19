package humanoid_modificado.controles_dos_servos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import humanoid_modificado.obj_estaticos_publicos_variados.TodosOsCheckBox;


public class ServoMotorSliderPanel extends JPanel{
	private int id;
	private JSlider slider;
	private JTextField textFieldValor;
	private ServoMotorSliderPanel servoIncrementoCorrespondente;
	private TabbedPanel controleDosServos;

	public ServoMotorSliderPanel(int id, int min, int max, int value, ServoMotorSliderPanel servoIncrementoCorrespondente, TabbedPanel controleDosServos){
		this.servoIncrementoCorrespondente = servoIncrementoCorrespondente;
		this.id = id;
		this.controleDosServos = controleDosServos;
		slider = new JSlider(min, max, value);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(45);
		slider.setMinorTickSpacing(5);
		JLabel nome = new JLabel("Servo" + id);
		textFieldValor = new JTextField(Integer.toString(value), 3);
		addListenners(servoIncrementoCorrespondente);
		this.add(nome);
		this.add(slider);
		this.add(textFieldValor);
	}

	private void addListenners(ServoMotorSliderPanel servoIncrementoCorrespondente){
		textFieldValor.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER &&
						Integer.parseInt(textFieldValor.getText()) >= slider.getMinimum() &&
						Integer.parseInt(textFieldValor.getText()) <= slider.getMaximum()){
					setValue(Integer.parseInt(textFieldValor.getText()));
				}
			}
		});

		slider.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {
				setValue(slider.getValue());
				if(TodosOsCheckBox.enviarDadosArduino().isSelected())
				controleDosServos.enviaAngulosParaArduino();
			}
		});
	}
	
	
	public void atualizaControleServoIncremental(){
		if(servoIncrementoCorrespondente != null){
			if(servoIncrementoCorrespondente.getValue()< -(slider.getValue())){
				servoIncrementoCorrespondente.setValue(-(slider.getValue()));
			}
			if(servoIncrementoCorrespondente.getValue() > 180-(slider.getValue())){
				servoIncrementoCorrespondente.setValue(180-(slider.getValue()));

			}

			servoIncrementoCorrespondente.setMinValue(-(slider.getValue()));
			servoIncrementoCorrespondente.setMaxValue(180-(slider.getValue()));
		}
		controleDosServos.atualizaTabelaDeEstados();

	}
	
	public int getID(){
		return  id;
	}
	
	public int getMinValue(){
		return slider.getMinimum();
	}

	public void setMinValue(int min){
		slider.setMinimum(min);
	}

	public int getValue(){
		return slider.getValue();
	}

	public void setValue(int value){
		slider.setValue(value);
		textFieldValor.setText(Integer.toString(value));
		atualizaControleServoIncremental();
	}

	public int getMaxValue(){
		return slider.getMaximum();
	}

	public void setMaxValue(int max){
		slider.setMaximum(max);
	}
	
}