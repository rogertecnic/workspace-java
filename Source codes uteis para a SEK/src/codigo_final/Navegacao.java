// classe com os metodos do robo andar entre as celulas
// possui metodos para alinhamento dentro da celula utilizando os sensores de cor
// metodos para verificar os sensores diretamente
// um metodo que retorna quantos movimentos o robo fez apos o ultimo alinhamento
package codigo_final;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class Navegacao {
	// parametros para o piloto do robo
	static final float diameter = 5.419f;
	// distancia do centro da roda ao centro do robo
	static final double offset = 7.59f;

	// parametro da celula
	static final float celTamanho = 21.8f;

	// parametro para o robo nao dar cavalo de pau e escorregar a roda
	static final float linearAcc = 15;
	static final float angularAcc = 120;
	static final float linearVelo = 15;
	static final float angularVelo = 90;

	// posicao em que o robo esta
	public static final Posicao posicaoAtual = new Posicao(0, 0);
	
	// qnt de movimentos e giros que o robo fez após o ultimo alinhamento
	public static int movAposAlinhar = 0;
	public static int giroAposAlinhar = 0;

	// cria os sensores
	static final EV3ColorSensor sensorE = new EV3ColorSensor(SensorPort.S2);
	static final EV3ColorSensor sensorD = new EV3ColorSensor(SensorPort.S3);

	// -----------------------------------------------------criando o piloto
	// Pilot é uma ferramenta da API leJOS
	// cria as rodas
	static Wheel rodaE = WheeledChassis.modelWheel(Motor.A, diameter).offset(-offset);
	static Wheel rodaD = WheeledChassis.modelWheel(Motor.B, diameter).offset(offset);
	// cria o chassis do robo
	static WheeledChassis chassis = new WheeledChassis(new Wheel[] { rodaE, rodaD }, WheeledChassis.TYPE_DIFFERENTIAL);
	// cria o piloto
	static MovePilot piloto = new MovePilot(chassis);
	// -----------------------------------------------------piloto criado


	// cria os samples dos sensores de cor individuais
	static SampleProvider sampleCorE = sensorE.getRGBMode();
	static SampleProvider sampleCorD = sensorD.getRGBMode();

	// Construtor que seta as velocidades angulares e lineares e acelerações
	// angulares e lineares
	public Navegacao() {
		piloto.setLinearSpeed(linearVelo);
		piloto.setLinearAcceleration(linearAcc);
		piloto.setAngularAcceleration(angularAcc);
		piloto.setAngularSpeed(angularVelo);
	}

	/**
	 * retorna se o sensor esquerdo esta sobre a linha ou nao
	 */
	public boolean getSensorE() {
		float[] amostra = new float[3];
		sampleCorE.fetchSample(amostra, 0);
		if (amostra[0] <= 0.08)
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
		if (amostra[0] <= 0.08)
			return true;
		else
			return false;
	}

	/**
	 * Anda com o robo para frente uma celula
	 */
	public void andar() {
		switch (posicaoAtual.orientacao) {
		case Posicao.NORTE: {
			posicaoAtual.y++;
			break;
		}
		case Posicao.LESTE: {
			posicaoAtual.x++;
			break;
		}
		case Posicao.SUL: {
			posicaoAtual.y--;
			break;
		}
		case Posicao.OESTE: {
			posicaoAtual.x--;
			break;
		}
		}
		
		piloto.travel(celTamanho);
		System.out.println(posicaoAtual.toString());
		movAposAlinhar++;
	}

	/**
	 * faz o robo girar e ficar de frente para a direcao
	 * 
	 * @param direcao
	 *            utilizar as constantes de posicao Posicao.NORTE
	 */
	public void girar(int direcao) {
		double angRotacao = 0;
		if (direcao != posicaoAtual.orientacao) {
			// a direcao é atraz dele
			if (Math.abs(direcao - posicaoAtual.orientacao) == 2)
				angRotacao = -180;
			// a direcao é a direita dele
			if ((posicaoAtual.orientacao - direcao == -1) || (posicaoAtual.orientacao == 3 && direcao == 0))
				angRotacao = 90;
			// a direao é a esquerda dele
			if ((posicaoAtual.orientacao - direcao == 1) || (posicaoAtual.orientacao == 0 && direcao == 3))
				angRotacao = -90;
			
				piloto.rotate(angRotacao);
				posicaoAtual.orientacao = direcao;
			// para depurar o metodo girar
		}
		giroAposAlinhar++;
	}

	/**
	 * alinha o carro no meio da celula
	 */
	public void alinhar() {
		movAposAlinhar = 0;
		giroAposAlinhar = 0;
		piloto.setAngularAcceleration(60);
		piloto.setLinearAcceleration(300);
		piloto.setAngularSpeed(90);
		piloto.setLinearSpeed(6);

		piloto.forward();
		boolean alinhou = false;
		while (!alinhou) {
			if (getSensorD())
				Motor.B.stop(true);
			if (getSensorE())
				Motor.A.stop(true);
			if (!Motor.B.isMoving() && !Motor.A.isMoving())
				alinhou = true;
			// Delay.msDelay(5);
		}
		
		Delay.msDelay(500);
		piloto.travel(-celTamanho / 2);
		piloto.rotate(-90);
		
		piloto.forward();
		alinhou = false;
		Delay.msDelay(300);
		while (!alinhou) {
			if (getSensorD())
				Motor.B.stop(true);
			if (getSensorE())
				Motor.A.stop(true);
			if (!Motor.B.isMoving() && !Motor.A.isMoving())
				alinhou = true;
			Delay.msDelay(5);
		}
		
		Delay.msDelay(500);
		piloto.travel(-celTamanho / 2);
		piloto.rotate(90);
		
		piloto.setAngularAcceleration(angularAcc);
		piloto.setLinearAcceleration(linearAcc);
		piloto.setAngularSpeed(angularVelo);
		piloto.setLinearSpeed(linearVelo);
	}
	
	public void andarCelula(Celula proximaCelula) {
		int direcao;
		if ((proximaCelula.posicao.x != this.posicaoAtual.x) || (proximaCelula.posicao.y != this.posicaoAtual.y)) {
			direcao = this.posicaoAtual.orientacao;
			// orientar o robo na direção certa da prox celula
			if (this.posicaoAtual.x < proximaCelula.posicao.x)
				direcao = Posicao.LESTE;
			else if (this.posicaoAtual.x > proximaCelula.posicao.x)
				direcao = Posicao.OESTE;
			else if (this.posicaoAtual.y < proximaCelula.posicao.y)
				direcao = Posicao.NORTE;
			else if (this.posicaoAtual.y > proximaCelula.posicao.y)
				direcao = Posicao.SUL;

			this.girar(direcao);
			this.andar();
			if ((this.giroAposAlinhar > 4) && (this.movAposAlinhar > 3))
				this.alinhar();
		}
	}
	
}
