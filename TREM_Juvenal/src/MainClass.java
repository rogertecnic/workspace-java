import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
/**
 * Classe para testar o controle PD e a distancia, no giro e na linha reta
 * @author Rogerio
 *
 */
public class MainClass {
	EV3LargeRegulatedMotor rodaE;
	EV3LargeRegulatedMotor rodaD;
	EV3MediumRegulatedMotor motorG;
	double acc = 0.3; //m/s^2
	double velo = 0.2; //m/s
	double raioRoda = 0.05494/2; //m radio = diametro da roda div por 2
	double larguraRobo = 0.139; //m largura ta certa
	double espacoAcc = velo*velo/(2*acc); // espaco que a aceleracao dura
	double posicaoDesaceleracao = 0;

	int Kp = 1;
	int Kd = 1;
	double PD = 0;
	double erro = 0;
	double erroAnt = 0;
	int dt = 8; // tempo do PD em ms


	public void instance(){
		rodaE = new EV3LargeRegulatedMotor(MotorPort.A);
		rodaD = new EV3LargeRegulatedMotor(MotorPort.B);
		//motorG = new EV3MediumRegulatedMotor(MotorPort.C);
		resetMotors();
	}
	public void resetMotors(){
		PD = 0;
		erro = 0;
		erroAnt = 0;
		rodaE.setAcceleration((int)(acc/raioRoda*180/3.1415));
		rodaD.setAcceleration((int)(acc/raioRoda*180/3.1415));
		rodaE.setSpeed((float)(velo/raioRoda*180/3.1415));
		rodaD.setSpeed((float)(velo/raioRoda*180/3.1415));
	}

	/**
	 * Metodo teste para o controle dos motores para um movimento de giro e linha reta
	 * 
	 * @param dist distancia que o robo deve andar para frente
	 * @param graus giro, positivo anti horario, negativo horario
	 */
	public void andar(double dist, double graus){
		instance();
		double SD = 0;
		double SE = 0;

		{//giro
			double thetaDinicial = rodaD.getTachoCount();
			double thetaEinicial = rodaE.getTachoCount();

			graus = graus*3.1415/180;
			double Sgiro = Math.abs(graus*larguraRobo/2);
			if(espacoAcc < Sgiro)
				espacoAcc = Sgiro-espacoAcc;
			else
				espacoAcc = Sgiro/2;

			if(graus > 0){ // anti-horario
				rodaE.backward();
				rodaD.forward();
			}else{ // horario
				rodaD.backward();
				rodaE.forward();
			}

			while(SD<espacoAcc || SE <espacoAcc){
				Delay.msDelay(dt);
				SD = Math.abs(rodaD.getTachoCount() - thetaDinicial)*3.1415/180*larguraRobo/2;
				SE = Math.abs(rodaE.getTachoCount() - thetaEinicial)*3.1415/180*larguraRobo/2;
				erroAnt = erro;
				erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
				PD = Kp*erro + Kd*(erro - erroAnt)/dt;
				rodaD.setSpeed((float) ((velo - PD)/raioRoda*180/3.1415) );
				rodaE.setSpeed((float) ((velo + PD)/raioRoda*180/3.1415) );
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
			resetMotors();
		}

		{//reta
			double thetaDinicial = rodaD.getTachoCount();
			double thetaEinicial = rodaE.getTachoCount();
			if(espacoAcc < dist)
				espacoAcc = dist-espacoAcc;
			else
				espacoAcc = dist/2;

			rodaE.forward();
			rodaD.forward();

			while(SD<espacoAcc || SE<espacoAcc){
				Delay.msDelay(dt);
				SD = (rodaD.getTachoCount() - thetaDinicial)*3.1415/180*larguraRobo/2;
				SE = (rodaE.getTachoCount() - thetaEinicial)*3.1415/180*larguraRobo/2;
				erroAnt = erro;
				erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
				PD = Kp*erro + Kd*(erro - erroAnt)/dt;
				rodaD.setSpeed((float) ((velo - PD)/raioRoda*180/3.1415) );
				rodaE.setSpeed((float) ((velo + PD)/raioRoda*180/3.1415) );
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
			resetMotors();
		}
	}
	
	public void linhaReta(double dist){
		instance();
		
		if(espacoAcc < dist/2)
			posicaoDesaceleracao = dist-espacoAcc;
		else
			posicaoDesaceleracao = dist/2;
		
		double SD = 0;
		double SE = 0;
		double thetaDinicial = rodaD.getTachoCount();
		double thetaEinicial = rodaE.getTachoCount();
		

		rodaE.forward();
		rodaD.forward();

		while(SD<posicaoDesaceleracao || SE<posicaoDesaceleracao){
			Delay.msDelay(dt);
			SD = (rodaD.getTachoCount() - thetaDinicial)*3.1415/180*raioRoda;
			SE = (rodaE.getTachoCount() - thetaEinicial)*3.1415/180*raioRoda;
			erroAnt = erro;
			erro = SD - SE; // erro equivale a diferenca do comprimento dos arcos que rada roda esta fazendo
			PD = Kp*erro + Kd*(erro - erroAnt)/dt;
			rodaD.setSpeed((float) ((velo - PD)/raioRoda*180/3.1415) );
			rodaE.setSpeed((float) ((velo + PD)/raioRoda*180/3.1415) );
		}
		
		SD = rodaD.getTachoCount();
		SE = rodaE.getTachoCount();
		double SDant = 0;
		double SEant = 0;
		while(SD != SDant || SE != SEant){
			rodaE.stop(true);
			rodaD.stop(true);
			Delay.msDelay(20);
			SDant = SD;
			SEant = SE;
			SD = rodaD.getTachoCount();
			SE = rodaE.getTachoCount();
			System.out.println("(" + SE + ", " + SD + ")");
		}
		Button.waitForAnyPress();
		resetMotors();
		
	}
	
	public void testarRaioDaRoda(){
		instance();
		rodaE.rotate((int)(360*5), true);
		rodaD.rotate((int)(360*5), false);
	}
	
	
	public static void main(String[] args){
		MainClass minhaclasse = new MainClass();
		//minhaclasse.testarRaioDaRoda();
		minhaclasse.linhaReta(1);
	}
}
