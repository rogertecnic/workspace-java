package navegacao;

import classes_suporte.Const;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import sensores.UltraSom;

public class Movimento {
	private EV3LargeRegulatedMotor rodaE;
	private EV3LargeRegulatedMotor rodaD;

	private double espacoAcc = Const.VELOCIDADE_E*Const.VELOCIDADE_E/(2*Const.ACC); // espaco que a aceleracao dura
	private double posicaoDesaceleracao = 0;
	private double PD = 0;
	private double erro = 0;
	private double erroAnt = 0;


	public Movimento(Object[] motores){
		rodaE = (EV3LargeRegulatedMotor) motores[0];
		rodaD = (EV3LargeRegulatedMotor) motores[1];
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
	 * Anda em linha reta, procurando bonecos ou nao
	 * @param distancia distancia q ele vai andar
	 * @param detectaBoneco true para andar procurando
	 * @param sensorUS referencia para o sensor para ficar pesquisando se tem ou nao boneco
	 * @return retorna a distancia que ele andou ate parar
	 */
	public double linhaReta(double distancia,boolean detectaBoneco, UltraSom sensorUS){
		boolean condicaoDeParada = false;
		if(detectaBoneco && !condicaoDeParada)condicaoDeParada = sensorUS.getBonecoDetectado();
		
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

		while((SD<posicaoDesaceleracao || SE<posicaoDesaceleracao) && (!condicaoDeParada)){// controle
			if(detectaBoneco && !condicaoDeParada)condicaoDeParada = sensorUS.getBonecoDetectado();
			Delay.msDelay(Const.dt);
			SD = (rodaD.getTachoCount() - thetaDinicial)*3.1415/180*Const.RAIO_RODA;
			SE = (rodaE.getTachoCount() - thetaEinicial)*3.1415/180*Const.RAIO_RODA;
			System.out.println(SD);
			erroAnt = erro;
			erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
			PD = Const.Kp*erro + Const.Kd*(erro - erroAnt)/Const.dt;
			rodaD.setSpeed((float) ((Const.VELOCIDADE_D - PD)/Const.RAIO_RODA*180/3.1415) );
			rodaE.setSpeed((float) ((Const.VELOCIDADE_E + PD)/Const.RAIO_RODA*180/3.1415) );
		}

		double thetaD = rodaD.getTachoCount();
		double thetaE = rodaE.getTachoCount();
		double thetaDant = 0;
		double thetaEant = 0;
		rodaE.stop(true);
		rodaD.stop(true);
		while((thetaD != thetaDant || thetaE != thetaEant) && (!condicaoDeParada)){// parando o robo
			if(detectaBoneco && !condicaoDeParada)condicaoDeParada = sensorUS.getBonecoDetectado();
			Delay.msDelay(20);
			thetaDant = thetaD;
			thetaEant = thetaE;
			thetaD = rodaD.getTachoCount();
			thetaE = rodaE.getTachoCount();
		}	
		return SD+Const.ESPACO_DE_ACC;
	}

	/**
	 * o robo gira em torno do seu proprio eixo
	 * @param graus positivo anti-horario, negativo horario
	 * @param condicaoDeParada padrao TRUE, uma variavel boolean que, se caso por algum motivo externo ela
	 * seja alterada para FALSE o robo para;
	 */
	public void girar(double graus){
		double SD = 0;
		double SE = 0;

		double thetaDinicial = rodaD.getTachoCount();
		double thetaEinicial = rodaE.getTachoCount();

		graus = graus*3.1415/180;
		double Sgiro = Math.abs(graus*Const.RAIO_ROBO);
		if(espacoAcc < Sgiro/2)
			posicaoDesaceleracao = Sgiro-espacoAcc;
		else
			posicaoDesaceleracao = Sgiro/2;

		if(graus > 0){		 	// anti-horario
			rodaE.backward();
			rodaD.forward();
		}else{ 					// horario
			rodaD.backward();
			rodaE.forward();
		}

		while(SD<posicaoDesaceleracao || SE <posicaoDesaceleracao){
			Delay.msDelay(Const.dt);
			SD = Math.abs(rodaD.getTachoCount() - thetaDinicial)*3.1415/180*Const.RAIO_RODA;
			SE = Math.abs(rodaE.getTachoCount() - thetaEinicial)*3.1415/180*Const.RAIO_RODA;

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
		while(SD != SDant || SE != SEant){
			Delay.msDelay(20);
			SDant = SD;
			SEant = SE;
			SD = rodaD.getTachoCount();
			SE = rodaE.getTachoCount();
		}
	}
	
	public void andarRe(double distancia){
		double theta = distancia/Const.RAIO_RODA*(180/3.141592);
		rodaE.rotate(-(int)theta, true);
		rodaD.rotate(-(int)theta);	
	}
}
