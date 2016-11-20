package Seguidor_de_Linha;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class Ultrassonic implements Runnable{
	private static EV3UltrasonicSensor ultra = new EV3UltrasonicSensor(SensorPort.S4);
	private static float distancia = 0;
	private boolean flag = false;

	public void run(){
		while(!flag)
			valorDistancia();
	}
	
	private static void valorDistancia(){
		SampleProvider distanciaUS = ultra.getDistanceMode();
		float valorDistacia[] = new float[ultra.sampleSize()];
		distanciaUS.fetchSample(valorDistacia, 0);
		distancia = valorDistacia[0];
	}
	
	public float getDistancia() {
		return distancia;
	}

}
