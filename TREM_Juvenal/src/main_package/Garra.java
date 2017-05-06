package main_package;

import classes_suporte.Const;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.utility.Delay;

public class Garra {
	private EV3MediumRegulatedMotor garra;
	
	public Garra(EV3MediumRegulatedMotor garra){
		this.garra = garra;
	}
	
	/**
	 * abre a garra totalmente
	 */
	public void abreGarra(){
		Const.RAIO_ROBO = 0.0646; // ja soltou o boneco
		garra.setStallThreshold(10, 200);
		garra.setSpeed(100);
		garra.rotate(-8000);
		//System.out.println(garra.isStalled());
		garra.flt();
		Delay.msDelay(60);
		garra.rotate(-4);
		}
	
	public void fechaGarra(){
		Const.RAIO_ROBO = 0.065; // pegou boneco pra resgatar
		garra.setStallThreshold(20, 200);
		garra.setSpeed(130);
		garra.rotate(8000);
		//System.out.println(garra.isStalled());
		//garra.flt();
		//Delay.msDelay(60);
		//garra.rotate(6);
	}
}
