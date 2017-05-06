package main_package;

import classes_suporte.Menus;
import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;
import sensores.SensorCorBoneco;
import sensores.SensorCorChao;

public class MainClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		EV3LargeRegulatedMotor rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		EV3LargeRegulatedMotor rodaD = new EV3LargeRegulatedMotor(MotorPort.B);
		EV3MediumRegulatedMotor garra = new EV3MediumRegulatedMotor(MotorPort.C);
		EV3UltrasonicSensor sensorUltrassom  = new EV3UltrasonicSensor(SensorPort.S1);
		SensorCorBoneco corBoneco = new SensorCorBoneco(new EV3ColorSensor(SensorPort.S2));
		SensorCorChao corChao = new SensorCorChao(new EV3ColorSensor(SensorPort.S3));
		
		boolean calibrado = false;
		//Object[] componentes = {rodaE, rodaD, garra,sensorUltrassom,null, null};
		Object[] componentes = {rodaE,rodaD, garra, sensorUltrassom, corBoneco, corChao};
		//corChao.calibraCor();
		//corChao.getModuloDetectado();
		//corBoneco.calibraCor();
		
		 //corBoneco.verificaCorBoneco();
		Menus menus = new Menus();

		
		while(true){
			menus.mostraMenus();
			ThreadDaProva runnable = new ThreadDaProva(componentes, menus.getBoss(), menus.getLadoDeProcura(), calibrado);
			Thread threadBusca = new Thread(runnable, "thread de execucao da prova");
			
			threadBusca.setDaemon(true);
			threadBusca.start();

			
			Button.ENTER.waitForPressAndRelease();
			rodaE.stop(true);
			rodaD.stop(true);
			threadBusca.stop();
		}	
	}
}
