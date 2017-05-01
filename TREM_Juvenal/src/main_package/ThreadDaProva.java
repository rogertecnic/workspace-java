package main_package;

import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Thread gerenciadora de todo o codigo, controla a navegacao em geral, procurar, verificar boneco
 * resgatar ou tirar do caminho;
 * @author Rogerio
 *
 */
public class ThreadDaProva implements Runnable{
	private int boss, ladoDeProcura;
	
	
	public ThreadDaProva(int boss, int ladoDeProcura){
		this.boss = boss;
		this.ladoDeProcura = ladoDeProcura;
		
		
	}
	
	@Override
	public void run() {
		// TODO fazer o codigo da thread principal
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

}
