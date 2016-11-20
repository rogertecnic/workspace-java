import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class USSensor
{
	private static EV3UltrasonicSensor sensorUS = new EV3UltrasonicSensor(SensorPort.S1);

	public void main(String[] args)
	{
		SampleProvider sample = sensorUS.getDistanceMode();
		float[] amostraDistancia = new float[sensorUS.sampleSize()];
		amostraDistancia[0] = 1;
		
		while(amostraDistancia[0] >=0.1)
			sample.fetchSample(amostraDistancia, 0);
		
		System.out.println("atingiu 10 cm");
		Delay.msDelay(3000);
	}

}
