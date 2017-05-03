package main_package;

import lejos.hardware.sensor.EV3ColorSensor;

public class SensorCorChao {
	
	private EV3ColorSensor sensorCor;
	public SensorCorChao(EV3ColorSensor sensorCor){
		this.sensorCor = sensorCor;
	}
	
	public void testaSensorChao(){
		while(true){
			float[] corSample = new float[3];
			sensorCor.getRGBMode().fetchSample(corSample, 0);
			System.out.printf("%.1f;%.1f;%.1f\n",corSample[0]*10, corSample[1]*10, corSample[2]*10);
		}
	}
}
