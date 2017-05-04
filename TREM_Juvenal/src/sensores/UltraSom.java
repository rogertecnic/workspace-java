package sensores;

import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltraSom {
	private EV3UltrasonicSensor ultrassom;
	private Thread listenner;
	protected boolean bonecoDetectado = false;
	
	public UltraSom(EV3UltrasonicSensor ultrassom){
		this.ultrassom = ultrassom;
		listenner = new Thread(new USRunnable(this));
		listenner.start();
	}

	protected void testaUS(){
		while(true){
			float [] distSample = new float[1];
			ultrassom.getDistanceMode().fetchSample(distSample, 0);
			System.out.println(distSample[0]);
		}
	}
	
	//protected void 
	
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
			ultrassom.testaUS();
		}
	}
}
