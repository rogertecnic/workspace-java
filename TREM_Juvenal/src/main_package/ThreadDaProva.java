package main_package;

import classes_suporte.Const;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
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

			//victorySong();
			Delay.msDelay(1000);
			
			int anguloDoBoneco = 0;
			int tmp = 0;
			while(tmp < 3)
				switch(tmp){
				case 0:{
					anguloDoBoneco = movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
					if(anguloDoBoneco > Const.ANGULO_DE_PROCURA-6){ //TODO testar esse angulo de busca
						tmp++;
						break;
					}else{
						tmp = 4;
						break;
					}

				}case 1:{
					anguloDoBoneco = anguloDoBoneco - movimento.girar(-Const.ANGULO_DE_PROCURA*2, sensorUS);
					if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
						tmp++;
						break;
					}else{
						tmp =4;
						break;
					}
				}case 2:{
					anguloDoBoneco = anguloDoBoneco + movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
					if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
						tmp++;
						break;
					}else{
						tmp =4;
						break;
					}
				}
				}
			System.out.println(anguloDoBoneco);
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

	public void procurarEsquerda(){
		double jaAndou = 0;
		garra.abreGarra();
		movimento.linhaReta(0.1, 0, null, null);
		movimento.girar(-90, null);
		movimento.andarRe(0.8);
		movimento.linhaReta(0.15, 0, null, null);
		movimento.girar(90, null);
		movimento.andarRe(0.2);

		//Primeiro movimento pela esquerda, anda 1 metro
		jaAndou += movimento.linhaReta(1,Const.SENSOR_US, null, sensorUS);
		if(jaAndou < (1 - 0.01)){ // boneco foi detectado
			double distBoneco = sensorUS.getDistBoneco();
			movimento.linhaReta(distBoneco, 0, null, null);
			garra.fechaGarra();
			int bonecoNaGarra = corBoneco.verificaCorBoneco();
			if(ladoDeProcura == Const.DARTH_VADER){
				if(bonecoNaGarra == Const.VERMELHO){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}else{
				if(bonecoNaGarra == Const.VERDE){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}
		}

		//alinha para o proximo movimento
		movimento.girar(-90, null);
		movimento.andarRe(0.25);
		movimento.linhaReta(0.15,0, null, null);
		movimento.girar(90, null);

		//segundo movimento pela esquerda 2 metros
		jaAndou += movimento.linhaReta(1,Const.SENSOR_US, null, sensorUS);
		if(jaAndou < (1 - 0.01)){ // boneco foi detectado
			double distBoneco = sensorUS.getDistBoneco();
			movimento.linhaReta(distBoneco, 0, null, null);
			garra.fechaGarra();
			int bonecoNaGarra = corBoneco.verificaCorBoneco();
			if(ladoDeProcura == Const.DARTH_VADER){
				if(bonecoNaGarra == Const.VERMELHO){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}else{
				if(bonecoNaGarra == Const.VERDE){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}
		}

		//alinha para o proximo movimento
		movimento.girar(-90, null);
		movimento.andarRe(0.25);
		movimento.linhaReta(0.15,0, null, null);
		movimento.girar(90, null);


		//terceiro movimento pela esquerda final
		jaAndou += movimento.linhaReta(0.65,Const.SENSOR_US, null, sensorUS); //TODO verificar essa ultima distancia
		if(jaAndou < (1 - 0.01)){ // boneco foi detectado
			double distBoneco = sensorUS.getDistBoneco();
			movimento.linhaReta(distBoneco, 0, null, null);
			garra.fechaGarra();
			int bonecoNaGarra = corBoneco.verificaCorBoneco();
			if(ladoDeProcura == Const.DARTH_VADER){
				if(bonecoNaGarra == Const.VERMELHO){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}else{
				if(bonecoNaGarra == Const.VERDE){
					//TODO resgata
				}else{
					//TODO tira do caminho
				}
			}
		}

		// preparar pra pegar o boss
		movimento.girar(-180, null);
		movimento.andarRe(0.25);
		movimento.linhaReta(Const.RAIO_CIRCULO+0.05-Const.BUNDA_ROBO, 0, null, null);
		movimento.girar(90, null);
		movimento.andarRe(0.25);
		movimento.linhaReta(0.75-Const.BUNDA_ROBO-Const.RAIO_CIRCULO-Const.FRENTE_ROBO, 0, null, null);

		// procurar boneco no circulo pretoa
		int anguloDoBoneco = 0;
		int tmp = 0;
		while(tmp < 3)
			switch(tmp){
			case 0:{
				anguloDoBoneco = movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
				if(anguloDoBoneco > Const.ANGULO_DE_PROCURA-6){ //TODO testar esse angulo de busca
					tmp++;
					break;
				}else{
					tmp = 4;
					break;
				}

			}case 1:{
				anguloDoBoneco = anguloDoBoneco - movimento.girar(-Const.ANGULO_DE_PROCURA*2, sensorUS);
				if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
					tmp++;
					break;
				}else{
					tmp =4;
					break;
				}
			}case 2:{
				anguloDoBoneco = anguloDoBoneco + movimento.girar(Const.ANGULO_DE_PROCURA, sensorUS);
				if(anguloDoBoneco < -Const.ANGULO_DE_PROCURA+6){ //TODO testar esse angulo de busca
					tmp++;
					break;
				}else{
					tmp =4;
					break;
				}
			}
			}

	}
}
