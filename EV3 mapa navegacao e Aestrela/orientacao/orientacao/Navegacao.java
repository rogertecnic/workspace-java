// classe com os metodos do robo andar entre as celulas
// possui metodos para alinhamento dentro da celula utilizando os sensores de cor
// metodos para verificar os sensores diretamente
// um metodo que retorna quantos movimentos o robo fez apos o ultimo alinhamento
package orientacao;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.Delay;

public class Navegacao {
	// parametros para o piloto do robo
	static final float diameter = 5.419f;
	static final double offset = 7.518f;

	// parametro da celula
	static final float celTamanho = 21.8f;

	// parametro para o robo nao dar cavalo de pau e escorregar a roda
	static final float linearAcc = 15;
	static final float angularAcc = 120;
	static final float linearVelo = 15;
	static final float angularVelo = 90;

	// posicao em que o robo esta
	static final Posicao posicaoAtual = new Posicao(0f, 0f, celTamanho);

	// cria os sensores
	static final EV3ColorSensor sensorE = new EV3ColorSensor(SensorPort.S1);
	static final EV3ColorSensor sensorD = new EV3ColorSensor(SensorPort.S2);

	// -----------------------------------------------------criando o piloto
	// Pilot é uma ferramenta da API leJOS
	// motores individuais de cada lado
	static EV3LargeRegulatedMotor motorE = new EV3LargeRegulatedMotor(MotorPort.A);
	static EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.B);
	// cria as rodas
	static Wheel rodaE = WheeledChassis.modelWheel(motorE, diameter);
	static Wheel rodaD = WheeledChassis.modelWheel(motorD, diameter);
	// cria o chassis do robo
	static WheeledChassis chassis = new WheeledChassis(new Wheel[] { rodaE, rodaD }, WheeledChassis.TYPE_DIFFERENTIAL);
	// cria o piloto
	static MovePilot piloto = new MovePilot(chassis);
	// -----------------------------------------------------piloto criado

	/**
	 * coordenadas do robo, usa os encoders dos motores
	 */
	static OdometryPoseProvider pose = new OdometryPoseProvider(piloto);

	// cria os samples dos sensores de cor individuais
	static SampleProvider sampleCorE = sensorE.getRGBMode();
	static SampleProvider sampleCorD = sensorD.getRGBMode();

	/**
	 * retorna se o sensor esquerdo esta sobre a linha ou nao
	 */
	public boolean getSensorE() {
		float[] amostra = new float[3];
		sampleCorE.fetchSample(amostra, 0);
		if (amostra[0] <= 0.3)
			return true;
		else
			return false;
	}

	/**
	 * retorna se o sensor direito esta sobre a linha ou nao
	 */
	public boolean getSensorD() {
		float[] amostra = new float[3];
		sampleCorD.fetchSample(amostra, 0);
		if (amostra[0] <= 0.3)
			return true;
		else
			return false;
	}

	/**
	 * Atualiza a posicao atual<br>
	 * Retorna um obj posicao que é a posicao atual do robo
	 */
	public Posicao getPosicaoAtual() {
		float[] posicao = new float[3];
		pose.fetchSample(posicao, 0);
		posicaoAtual.setX(posicao[0]);
		posicaoAtual.setY(posicao[1]);
		LCD.clear();
		LCD.drawString("getPosicaoAtual:\n[" + posicaoAtual.getX() + " , " + posicaoAtual.getY() + "]", 0, 0);
		return posicaoAtual;
	}

	/**
	 * Anda com o robo para frente uma celula
	 */
	public void andar() {
		piloto.travel(celTamanho);
		getPosicaoAtual();
	}

	/**
	 * faz o robo girar e ficar de frente para a direcao
	 * 
	 * @param direcao
	 *            utilizar as constantes de posicao Posicao.NORTE
	 */
	public void girar(int direcao) {
		double angRotacao = 0;
		if (direcao == posicaoAtual.orientacao) {
			LCD.clear();
			LCD.drawString("giro incosist", 0, 0);
		} else {
			// a direcao é atraz dele
			if (Math.abs(direcao - posicaoAtual.orientacao) == 2)
				angRotacao = -180;
			// a direcao é a esquerda dele
			if ((posicaoAtual.orientacao - direcao == -1) || (posicaoAtual.orientacao == 3 && direcao == 0))
				angRotacao = 90;
			// a direao é a direita dele
			if ((posicaoAtual.orientacao - direcao == 1) || (posicaoAtual.orientacao == 0 && direcao == 3))
				angRotacao = -90;

			if (angRotacao != 0) {
				piloto.rotate(angRotacao);
				posicaoAtual.orientacao = direcao;
			}
			// para depurar o metodo girar
			else {
				LCD.clear();
				LCD.drawString("zica no girar", 0, 0);
			}
		}
	}

	/**
	 * alinha o carro no meio da celula
	 */
	public void alinhar() {
		piloto.setAngularAcceleration(60);
		piloto.setLinearAcceleration(8);
		piloto.setAngularSpeed(90);
		piloto.setLinearSpeed(8);

		piloto.forward();
		boolean alinhou = false;
		while (!alinhou) {
			if (getSensorD())
				motorD.stop(true);
			if (getSensorE())
				motorE.stop(true);
			if (!motorD.isMoving() && !motorE.isMoving())
				alinhou = true;
			Delay.msDelay(5);
		}

		piloto.travel(-celTamanho / 2);
		piloto.rotate(-90);
		piloto.forward();
		alinhou = false;

		while (!alinhou) {
			if (getSensorD())
				motorD.stop(true);
			if (getSensorE())
				motorE.stop(true);
			if (!motorD.isMoving() && !motorE.isMoving())
				alinhou = true;
			Delay.msDelay(5);
		}

		piloto.travel(-celTamanho / 2);
		piloto.rotate(90);
		pose.setPose(new Pose(posicaoAtual.getX() * celTamanho, posicaoAtual.getY() * celTamanho, 0));
		piloto.setAngularAcceleration(angularAcc);
		piloto.setLinearAcceleration(linearAcc);
		piloto.setAngularSpeed(angularVelo);
		piloto.setLinearSpeed(linearVelo);
		LCD.clear();
		LCD.drawString("alinhar:\n[" + posicaoAtual.getX() + " , " + posicaoAtual.getY() + "]", 0, 0);
	}

	// ------------------------------------------------------------------- main
	public static void mian(String[] args) {
		piloto.forward();
		Button.waitForAnyPress();
	}

}
