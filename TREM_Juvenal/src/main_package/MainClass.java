package main_package;

import classes_suporte.Menus;
import lejos.hardware.Button;

public class MainClass {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Menus menus = new Menus();
		
		// TODO instanciar os motores e sensores quando ja estiverem prontos
		while(true){
			menus.mostraMenus();

			Thread threadBusca = new Thread(new ThreadDaProva(), "thread de execucao da prova");

			threadBusca.start();
			Button.ENTER.waitForPressAndRelease();
			threadBusca.stop();
		}	
	}
}
