package main_package;

import classes_suporte.Const;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.MindsensorsDistanceSensorV2;
import lejos.utility.Delay;
import navegacao.Movimento;
import sensores.SensorCorBoneco;
import sensores.SensorCorChao;
import sensores.UltraSom;

/**
 * Thread gerenciadora de todo o codigo, controla a navegacao em geral, procurar, verificar boneco
 * resgatar ou tirar do caminho;
 * @author Rogerio
 *
 */
public class ThreadDaProva implements Runnable{
	private int boss, ladoDeProcura;
	private Movimento movimento;
	private Garra garra;
	private UltraSom sensorUS;
	private SensorCorBoneco corBoneco;
	private SensorCorChao corChao;
	private int direcaoDoRobo = Const.FRENTE;

	/**
	 * Instancia o Runnable para a thread principal da prova
	 * @param componentes motorE, motorD, ultrassom, corBoneco, corChao;
	 * @param boss constante que define qual boss vamos procurar
	 * @param ladoDeProcura constante que define por onde vamos comecar
	 */
	public ThreadDaProva(Object[] componentes, int boss, int ladoDeProcura, boolean calibrado){
		this.boss = boss;
		this.ladoDeProcura = ladoDeProcura;
		movimento = new Movimento(componentes);
		garra = new Garra((EV3MediumRegulatedMotor) componentes[2]);
		sensorUS = new UltraSom((EV3UltrasonicSensor)componentes[3]);
		corBoneco = (SensorCorBoneco)componentes[4];
		corChao =(SensorCorChao)componentes[5];
	}

	@Override
	public void run() {
		try{
			//movimento.linhaReta(0.3, 0, null, null);
			//Delay.msDelay(1000);
			//movimento.girar(90, null);
			//Delay.msDelay(1000);
			//movimento.andarRe(0.7);
			//movimento.linhaReta(0.3, 0, null, null);
			//Const.RAIO_ROBO = 0.0646; // sem boneco
			//garra.abreGarra();
			//movimento.testaStall();
			
//			movimento.linhaReta(0.1, 0, null, null);
//			movimento.girar(-90, null);
//
			movimento.andarRePID(Const.DIST_MODULO_RESGATE+0.1);
			movimento.linhaReta(0.1, 0, null, null);
//			movimento.girar(90, null);
			movimento.andarRePID(0.35);
			
			//procurarEsquerda();

			//System.out.println(movimento.girar(-90, sensorUS));
			//procurarEsquerda();
			//garra.abreGarra();
			//sensorUS.testaUS();
			//corBoneco.testaSensorBoneco();
			//corChao.testaSensorChao();
			//movimento.girar(90, null);
			//movimento.linhaReta(1.7, true);




		}catch(ThreadDeath e){
			e.getStackTrace();
		}
	}




	/**
	 * samba na cara dos inimigos
	 */
	public static void victorySong(){
		Sound.setVolume(50);
		Sound.playTone(3000, 100);
		Sound.playTone(4000, 100);
		Sound.playTone(4500, 100);
		Sound.playTone(5000, 100);
		Delay.msDelay(80);
		Sound.playTone(3000, 200);
		Sound.playTone(5000, 500);
	}

	private void procurarEsquerda(){
		double jaAndou = 0;
		if(direcaoDoRobo == Const.FRENTE){
			movimento.linhaReta(0.1, 0, null, null);
			movimento.girar(-90, null);
		}
		movimento.andarRe(Const.DIST_MODULO_RESGATE+0.1);
		movimento.linhaReta(0.1, 0, null, null);
		movimento.girar(90, null);
		movimento.andarRe(0.35);


		while(jaAndou != 9999){
			//Primeiro movimento pela esquerda, anda 1 metro
			double jaAndouAnt = jaAndou;
			jaAndou += movimento.linhaReta((2.65/3),Const.SENSORES, corChao, sensorUS);
			if(jaAndou < (jaAndouAnt+2.65/3 - 0.01)){ // boneco foi detectado
				double distBoneco = sensorUS.getDistBoneco();
				movimento.linhaReta(distBoneco, 0, null, null);
				movimento.andarRe(0.03);
				garra.fechaGarra();
				int bonecoNaGarra = corBoneco.verificaCorBoneco();
				if(boss == Const.DARTH_VADER){
					if(bonecoNaGarra == Const.VERMELHO){
						resgataBonecoEsquerda(jaAndou);
						return;
					}else{
						tiraBonecoEsquerda();
					}
				}else{
					if(bonecoNaGarra == Const.VERDE){
						resgataBonecoEsquerda(jaAndou);
						return;
					}else{
						tiraBonecoEsquerda();
					}
				}
			}

			//alinha para o proximo movimento
			if(jaAndou != 9999){
				if( direcaoDoRobo == Const.FRENTE)
					movimento.girar(-90, null);
				movimento.andarRe(0.35);
				movimento.linhaReta(0.1,0, null, null);
				movimento.girar(90, null);
				direcaoDoRobo = Const.FRENTE;
			}
		}

		// preparar pra pegar o boss
		movimento.girar(-180, null);
		movimento.andarRe(0.35);
		movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
		movimento.girar(90, null);
		movimento.andarRe(0.3);
		movimento.linhaReta(0.75-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, 0, null, null);

		// procurar boneco no circulo preto
		int anguloDoBoneco = procuraCirculoPreto();

		// pega o primeiro boss
		double distBoneco = sensorUS.getDistBoneco();
		movimento.linhaReta(distBoneco, 0, null, null);
		garra.fechaGarra();
		int bonecoNaGarra = corBoneco.verificaCorBoneco();
		// RESGATE DO LADO DO WADER
		if(boss == Const.DARTH_VADER){
			// PEGUEI O PRETO
			if(bonecoNaGarra == Const.PRETO){
				System.out.println("vader amigo");
				movimento.andarRePID(distBoneco+0.1);
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.75-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				// vai buscar a leia
				movimento.andarRe(0.15);
				movimento.girar(90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				anguloDoBoneco = procuraCirculoPreto();
				// pega o segundo boss
				distBoneco = sensorUS.getDistBoneco();
				movimento.linhaReta(distBoneco, 0, null, null);
				garra.fechaGarra();
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.3);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				movimento.andarRe(0.25);
				// PEGUEI O BRANCO
			}else{
				System.out.println("leia inimigo");
				movimento.andarRePID(distBoneco+0.1);
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO*2+0.05, 0, null, null);
				movimento.girar(-90, null); // virar de bunda
				movimento.andarRePID(1.2);
				movimento.linhaReta(0.1, 0, null, null);
				movimento.girar(90, null);
				movimento.andarRePID(0.5);
				movimento.linhaReta(0.1, 0, null, null);
				movimento.girar(90, null);
				// vai buscar o segundo boss
				garra.abreGarra();
				movimento.andarRe(0.15);
				movimento.girar(-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				anguloDoBoneco = procuraCirculoPreto();
				// pega o segundo boss
				distBoneco = sensorUS.getDistBoneco();
				movimento.linhaReta(distBoneco, 0, null, null);
				garra.fechaGarra();
				movimento.girar(-anguloDoBoneco+90, null);
				movimento.andarRe(0.3);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				movimento.andarRe(0.25);
			}
			
		// RESGATE DO LADO DA LEIA
		}else{
			if(bonecoNaGarra == Const.BRANCO){
				System.out.println("leia amigo");
				movimento.andarRePID(distBoneco+0.1);
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO*2+0.05, 0, null, null);
				movimento.girar(-90, null); // virar de bunda
				movimento.andarRePID(1.2);
				movimento.linhaReta(0.1, 0, null, null);
				movimento.girar(90, null);
				movimento.andarRePID(0.5);
				movimento.linhaReta(0.1, 0, null, null);
				movimento.girar(90, null);
				garra.abreGarra();
				// vai buscar o segundo boss
				movimento.andarRe(0.15);
				movimento.girar(-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				anguloDoBoneco = procuraCirculoPreto();
				// pega o segundo boss
				distBoneco = sensorUS.getDistBoneco();
				movimento.linhaReta(distBoneco, 0, null, null);
				garra.fechaGarra();
				movimento.girar(-anguloDoBoneco+90, null);
				movimento.andarRe(0.3);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				movimento.andarRe(0.25);
			}else{
				System.out.println("vader inimigo");
				movimento.andarRePID(distBoneco+0.1);
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(-90, null);
				movimento.linhaReta(0.75-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				// vai buscar a leia
				movimento.andarRe(0.15);
				movimento.girar(90, null);
				movimento.andarRe(0.25);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				anguloDoBoneco = procuraCirculoPreto();
				// pega o segundo boss
				distBoneco = sensorUS.getDistBoneco();
				movimento.linhaReta(distBoneco, 0, null, null);
				garra.fechaGarra();
				movimento.girar(-anguloDoBoneco-90, null);
				movimento.andarRe(0.3);
				movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
				movimento.girar(90, null);
				movimento.linhaReta(0.7-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, Const.SENSORES, corChao, sensorUS);
				garra.abreGarra();
				movimento.andarRe(0.25);
			}
		}
		victorySong();
	}


	private void resgataBonecoEsquerda(double distDeVolta){
		Const.RAIO_ROBO = 0.065; // pegou boneco pra resgatar
		movimento.andarRePID(distDeVolta+0.4);
		movimento.linhaReta(0.1, 0, null, null);
		movimento.girar(-90, null);
		movimento.andarRePID(0.25);
		movimento.linhaReta(Const.DIST_MODULO_RESGATE-Const.BUNDA_ROBO, 0, null, null);
		garra.abreGarra();
		direcaoDoRobo = Const.DIREITA;
		Const.RAIO_ROBO = 0.0646; // ja soltou o boneco
	}

	private void tiraBonecoEsquerda(){
		Const.RAIO_ROBO = 0.065; // pegou boneco pra resgatar
		movimento.girar(-90, null);
		movimento.linhaReta(0.15, 0, null, null);
		garra.abreGarra();
		Const.RAIO_ROBO = 0.0646; // ja soltou o boneco
		direcaoDoRobo = Const.DIREITA;
	}

	private int procuraCirculoPreto(){
		int anguloDoBoneco = 0;
		int tmp = 0;
		while(tmp < 3){
			anguloDoBoneco = 0;
			switch(tmp){
			case 0:
				anguloDoBoneco = movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
				if(anguloDoBoneco > Const.ANGULO_DE_PROCURA-6){ //TODO testar esse angulo de busca
					tmp++;
				}else{
					tmp = 4;
				}
				break;
			case 1:
				anguloDoBoneco = anguloDoBoneco - movimento.girar(-Const.ANGULO_DE_PROCURA*2, sensorUS);
				if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
					tmp++;
				}else{
					tmp =4;
				}
				break;
			case 2:
				anguloDoBoneco = anguloDoBoneco + movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
				if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
					tmp++;
				}else{
					tmp =4;
				}
				break;
			}
		}
		return anguloDoBoneco;
	}
}
