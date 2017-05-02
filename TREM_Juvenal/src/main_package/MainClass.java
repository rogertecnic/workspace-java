package main_package;

import classes_suporte.Menus;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class MainClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		EV3LargeRegulatedMotor rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor rodaD = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3LargeRegulatedMotor[] motores = {rodaE,rodaD};
		Menus menus = new Menus();
		
		// TODO instanciar os motores e sensores quando ja estiverem prontos
		while(true){
			menus.mostraMenus();

			Thread threadBusca = new Thread(new ThreadDaProva(motores, menus.getBoss(), menus.getLadoDeProcura()), "thread de execucao da prova");
			
			threadBusca.setDaemon(true);
			threadBusca.start();
			Button.ENTER.waitForPressAndRelease();
			motores[0].stop(true);
			motores[1].stop(true);
			threadBusca.stop();
		}	
	}
}
