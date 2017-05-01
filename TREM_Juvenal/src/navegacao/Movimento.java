package navegacao;

import classes_suporte.Const;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Movimento {
	private EV3LargeRegulatedMotor rodaE;
	private EV3LargeRegulatedMotor rodaD;
	
	
	
	private double espacoAcc = Const.velo*Const.velo/(2*Const.acc); // espaco que a aceleracao dura
	private double posicaoDesaceleracao = 0;
	
	
	public Movimento(){
		rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		rodaE = new EV3LargeRegulatedMotor(MotorPort.B);
		resetMotors();
	}
	
	public void resetMotors(){
		rodaE.resetTachoCount();
		rodaD.resetTachoCount();
		rodaE.setAcceleration((int)(Const.acc/Const.raioRoda*180/3.1415));
		rodaD.setAcceleration((int)(Const.acc/Const.raioRoda*180/3.1415));
		rodaE.setSpeed((float)(Const.velo/Const.raioRoda*180/3.1415));
		rodaD.setSpeed((float)(Const.velo/Const.raioRoda*180/3.1415));
	}
	
	/**
	 * Robo anda em linha reta.
	 * @param distancia distancia que o robo vai andar, em metros por que o raio da roda e o raio
	 * do robo estao em metro
	 * @param condicaoDeParada padrao TRUE, uma variavel boolean que, se caso por algum motivo externo ela
	 * seja alterada para FALSE o robo para;
	 */
	public void linhaReta(int distancia,boolean condicaoDeParada){
		//TODO fazer metodo linhaReta, o robo anda em linha reta por uma "distancia"
	}
	
	/**
	 * o robo gira em torno do seu proprio eixo
	 * @param graus positivo anti-horario, negativo horario
	 */
	public void girar(int graus){
		// TODO girar sobre o proprio eixo
	}
}
