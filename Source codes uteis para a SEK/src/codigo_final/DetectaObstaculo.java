package codigo_final;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DetectaObstaculo {
	public static final NXTUltrasonicSensor sensor = new NXTUltrasonicSensor(SensorPort.S1);
	public static final SampleProvider sampleSensor = sensor.getDistanceMode();
	private static final float DIST_MAX = 0.25f;
	private static final float DIST_MIN = 0.05f;
	
	private float[] valor = new float[ sampleSensor.sampleSize()];
	
	public boolean verificaObstaculo(){
		sampleSensor.fetchSample(valor, 0);
		if((valor[0] >= DIST_MIN) && (valor[0] <= DIST_MAX))
			return true;
		else return false;
	}
}
