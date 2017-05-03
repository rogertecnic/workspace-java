package main_package;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.utility.Delay;

public class Garra {
	private EV3MediumRegulatedMotor garra;
	
	public Garra(EV3MediumRegulatedMotor garra){
		this.garra = garra;
	}
	
	/**
	 * verifica se a garra esta aberta ou fechada
	 * @return TRUE se a garra estiver fechada, FALSE se aberta
	 */
	public void abreGarra(){
		garra.setStallThreshold(10, 200);
		garra.setSpeed(100);
		garra.rotate(-8000);
		System.out.println(garra.isStalled());
		garra.flt();
		Delay.msDelay(60);
		garra.rotate(-4);
		}
	
}
