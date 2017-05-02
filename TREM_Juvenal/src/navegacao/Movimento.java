package navegacao;

import classes_suporte.Const;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Movimento {
	private EV3LargeRegulatedMotor rodaE;
	private EV3LargeRegulatedMotor rodaD;

	private double espacoAcc = Const.VELOCIDADE_E*Const.VELOCIDADE_E/(2*Const.ACC); // espaco que a aceleracao dura
	private double posicaoDesaceleracao = 0;
	private double PD = 0;
	private double erro = 0;
	private double erroAnt = 0;


	public Movimento(){
		rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		rodaE = new EV3LargeRegulatedMotor(MotorPort.B);
		resetMotors();
	}

	public void resetMotors(){
		rodaE.resetTachoCount();
		rodaD.resetTachoCount();
		rodaE.setAcceleration((int)(Const.ACC/Const.RAIO_RODA*180/3.1415));
		rodaD.setAcceleration((int)(Const.ACC/Const.RAIO_RODA*180/3.1415));
		rodaE.setSpeed((float)(Const.VELOCIDADE_E/Const.RAIO_RODA*180/3.1415));
		rodaD.setSpeed((float)(Const.VELOCIDADE_D/Const.RAIO_RODA*180/3.1415));
	}

	/**
	 * Robo anda em linha reta.
	 * @param distancia distancia que o robo vai andar, em metros por que o raio da roda e o raio
	 * do robo estao em metro
	 * @param condicaoDeParada padrao TRUE, uma variavel boolean que, se caso por algum motivo externo ela
	 * seja alterada para FALSE o robo para;
	 */
	public void linhaReta(int distancia,boolean condicaoDeParada){
		if(Const.ESPACO_DE_ACC  < distancia/2)
			posicaoDesaceleracao = distancia-Const.ESPACO_DE_ACC;
		else
			posicaoDesaceleracao = distancia/2;

		double SD = 0;
		double SE = 0;
		double thetaDinicial = rodaD.getTachoCount();
		double thetaEinicial = rodaE.getTachoCount();

		rodaE.forward();
		rodaD.forward();

		while((SD<posicaoDesaceleracao || SE<posicaoDesaceleracao) && (condicaoDeParada)){// controle
			Delay.msDelay(Const.dt);
			SD = (rodaD.getTachoCount() - thetaDinicial)*3.1415/180*Const.RAIO_RODA;
			SE = (rodaE.getTachoCount() - thetaEinicial)*3.1415/180*Const.RAIO_RODA;

			erroAnt = erro;
			erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
			PD = Const.Kp*erro + Const.Kd*(erro - erroAnt)/Const.dt;
			rodaD.setSpeed((float) ((Const.VELOCIDADE_D - PD)/Const.RAIO_RODA*180/3.1415) );
			rodaE.setSpeed((float) ((Const.VELOCIDADE_E + PD)/Const.RAIO_RODA*180/3.1415) );
		}

		SD = rodaD.getTachoCount();
		SE = rodaE.getTachoCount();
		double SDant = 0;
		double SEant = 0;
		rodaE.stop(true);
		rodaD.stop(true);
		while((SD != SDant || SE != SEant) && (condicaoDeParada)){// parando o robo
			Delay.msDelay(20);
			SDant = SD;
			SEant = SE;
			SD = rodaD.getTachoCount();
			SE = rodaE.getTachoCount();
		}		
	}

	/**
	 * o robo gira em torno do seu proprio eixo
	 * @param graus positivo anti-horario, negativo horario
	 */
	public void girar(double graus, boolean condicaoDeParada){
		double SD = 0;
		double SE = 0;

		double thetaDinicial = rodaD.getTachoCount();
		double thetaEinicial = rodaE.getTachoCount();

		graus = graus*3.1415/180;
		double Sgiro = Math.abs(graus*Const.RAIO_ROBO);
		if(espacoAcc < Sgiro)
			espacoAcc = Sgiro-espacoAcc;
		else
			espacoAcc = Sgiro/2;

		if(graus > 0){		 	// anti-horario
			rodaE.backward();
			rodaD.forward();
		}else{ 					// horario
			rodaD.backward();
			rodaE.forward();
		}

		while(SD<espacoAcc || SE <espacoAcc){
			Delay.msDelay(Const.dt);
			SD = Math.abs(rodaD.getTachoCount() - thetaDinicial)*3.1415/180*Const.RAIO_ROBO;
			SE = Math.abs(rodaE.getTachoCount() - thetaEinicial)*3.1415/180*Const.RAIO_ROBO;

			erroAnt = erro;
			erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
			PD = Const.Kp*erro + Const.Kd*(erro - erroAnt)/Const.dt;
			rodaD.setSpeed((float) ((Const.VELOCIDADE_D - PD)/Const.RAIO_RODA*180/3.1415) );
			rodaE.setSpeed((float) ((Const.VELOCIDADE_E + PD)/Const.RAIO_RODA*180/3.1415) );
		}

		SD = rodaD.getTachoCount();
		SE = rodaE.getTachoCount();	
		double SDant = 0;
		double SEant = 0;
		while(SD != SDant || SE != SEant){
			rodaE.stop(true);
			rodaD.stop(true);
			Delay.msDelay(30);
			SDant = SD;
			SEant = SE;
			SD = rodaD.getTachoCount();
			SE = rodaE.getTachoCount();
		}
	}
}
