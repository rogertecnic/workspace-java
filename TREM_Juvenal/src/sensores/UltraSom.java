package sensores;

import classes_suporte.Const;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class UltraSom {
	private EV3UltrasonicSensor ultrassom;
	private Thread listenner;
	protected boolean bonecoDetectado = false;
	private float [] distSample = new float[1];
	
	public UltraSom(EV3UltrasonicSensor ultrassom){
		this.ultrassom = ultrassom;
		listenner = new Thread(new USRunnable(this));
		listenner.start();
	}

	public void testaUS(){
		while(true){
			ultrassom.getDistanceMode().fetchSample(distSample, 0);
			System.out.println(distSample[0]);
		}
	}
	
	protected void detectaBoneco(){
		ultrassom.getDistanceMode().fetchSample(distSample, 0);
		if(distSample[0] > Const.US_MIN && distSample[0] < Const.US_MAX)
			bonecoDetectado = true;
		else bonecoDetectado = false;
	}
	
	public boolean getBonecoDetectado(){
		return bonecoDetectado;
	}
}


class USRunnable implements Runnable{
	private UltraSom ultrassom;
	
	USRunnable(UltraSom ultrassom){
		this.ultrassom = ultrassom;
	}
	@Override
	public void run() {
		while(true){
			Delay.msDelay(10);
			ultrassom.detectaBoneco();
			System.out.println(ultrassom.getBonecoDetectado());
		}
	}
}
