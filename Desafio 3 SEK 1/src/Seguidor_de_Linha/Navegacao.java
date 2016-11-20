package Seguidor_de_Linha;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;


public class Navegacao {
	
	private static EV3LargeRegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
	private static EV3LargeRegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.B);
	private float speedA;
	private float speedB;
	
	public Navegacao(float speedA, float speedB){
		this.speedA = speedA;
		this.speedB = speedB;
	}
	
	public void velocidade(float speedA, float speedB){
		motorA.setSpeed(speedA);
		motorB.setSpeed(speedB);
	}
	
	public void frente(){
		motorA.forward();
		motorB.forward();
	}
	public void direita(){
		motorA.forward();
		motorB.backward();
	}
	public void esquerda(){
		motorA.backward();
		motorB.forward();
	}
	public void tras(){
		motorA.backward();
		motorB.backward();
	}
	public void gira90D(){
		motorA.rotate(380,true);
		motorB.stop();
	}
	public void gira90E(){
		motorB.rotate(380,true);
		motorA.stop();
		
	}
	public void GiraCampo (){
		motorA.stop(true);
		motorB.rotate(100);
	}
		
	public void FrenteCaixa(){
		motorA.rotate(200, true);
		motorB.rotate(200);
	}
	
	
}
