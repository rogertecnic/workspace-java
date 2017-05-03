package main_package;

import lejos.hardware.sensor.EV3ColorSensor;

public class SensorCorBoneco {
	private EV3ColorSensor sensorCor;
	public SensorCorBoneco(EV3ColorSensor sensorCor){
		this.sensorCor = sensorCor;
	}
	
	public void testaSensorBoneco(){
		while(true){
			float[] corSample = new float[3];
			sensorCor.getRGBMode().fetchSample(corSample, 0);
			System.out.printf("R:%.3f G:%.3f B:%.3f",corSample[0], corSample[1], corSample[2]);
		}
	}
}
