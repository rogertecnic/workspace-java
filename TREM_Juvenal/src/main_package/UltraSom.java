package main_package;

import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltraSom {
	private EV3UltrasonicSensor ultrassom;

	public UltraSom(EV3UltrasonicSensor ultrassom){
		this.ultrassom = ultrassom;
	}

	public void testaUS(){
		while(true){
			float [] distSample = new float[1];
			ultrassom.getDistanceMode().fetchSample(distSample, 0);
			System.out.println(distSample[0]);
		}
	}
}
