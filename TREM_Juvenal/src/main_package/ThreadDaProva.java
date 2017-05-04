package main_package;

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
			Delay.msDelay(100000);
			//procurarEsquerda();
		//garra.abreGarra();
		//sensorUS.testaUS();
			//corBoneco.testaSensorBoneco();
		//corChao.testaSensorChao();
		//movimento.girar(90, true);
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
		garra.abreGarra();
		movimento.linhaReta(0.2, true);
		//garra.fechaGarra();
		movimento.girar(-90, true);
		movimento.andarRe(0.8);
		movimento.linhaReta(0.2, true);
		movimento.girar(90, true);
		movimento.linhaReta(0.8, true);
	}

}
