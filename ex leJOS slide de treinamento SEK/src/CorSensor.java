import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class CorSensor {
	EV3ColorSensor sensorCor = new EV3ColorSensor(SensorPort.S1);
	
	public void main(String[] args)
	{
		SampleProvider sample = sensorCor.getColorIDMode();
		float[] amostraCor = new float[sensorCor.sampleSize()];
		amostraCor[0] = Color.NONE;
		while(Button.ESCAPE.isUp())
		{
			sample.fetchSample(amostraCor, 0);
			LCD.drawInt((int)amostraCor[0], 4, 0); //coerção explicita converte o float em um int
			Delay.msDelay(10);
		}
	}
}
