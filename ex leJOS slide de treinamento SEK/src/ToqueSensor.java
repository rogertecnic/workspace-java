import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ToqueSensor
{
	private static EV3TouchSensor sensorToque = new EV3TouchSensor(SensorPort.S1);
	
	public void main(String[] args)
	{
		SampleProvider sample = sensorToque.getTouchMode();
		float amostraToque[] = new float[sensorToque.sampleSize()];
		amostraToque[0] = 0;
		
		while(amostraToque[0]==0)
			sample.fetchSample(amostraToque, 0);
		
		System.out.println("sensor pressionado");
		Delay.msDelay(3000);
	}
}
