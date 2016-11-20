package ev3_main_menu;

import java.lang.Thread.State;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import main_Menu.ThreadProgramClass;

public class EV3MainMenuClass {
	/**
	 * Thread principal do codigo
	 */
	private static Thread threadPrograma = new Thread(new ThreadProgramClass());
	/**
	 * @exit encerrar o programa todo
	 */
	private static boolean exit = false;
	
	//=====================MAIN===================================
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		threadPrograma.setDaemon(true);
		while (!exit) {
			controleMenu();
			if (!exit) {
				Button.waitForAnyPress();
				threadPrograma.suspend(); //corrigir para pausar os motores
			}
		}
	}
	//=============================================================
	
	/**
	 * Métpdo que imprime o menu <br>
	 * @param opcao inteiro que indica qual opção está realçada:<br>
	 * <b>1:</b> (Re)iniciar o codigo<br>
	 * <b>2:</b> sai do programa<br>
	 */
	public static void mostraMenu(int opcao) {
		LCD.clear();
		LCD.drawString("MENU DE CODIGO", 0, 0);
		LCD.drawString((opcao == 1 ? ">" : " ") + " (Re)iniciar codigo", 0, 1);
		//LCD.drawString((opcao == 2 ? ">" : " ") + " Continuar codigo", 0, 2);
		LCD.drawString((opcao == 2 ? ">" : " ") + " Sair", 0, 2);
	}

	/**
	 * Metodo que seleciona a opção realçada no menu
	 * @param opcao  inteiro que indica qual opção está realçada:<br>
	 * <b>1:</b> (Re)iniciar o codigo<br>
	 * <b>2:</b> sai do programa<br>
	 */
	@SuppressWarnings("deprecation")
	public static void selecionaOpcao(int opcao) {
		State s = threadPrograma.getState();
		switch (opcao) {
		case 1: {
			if (s == State.NEW)
				threadPrograma.start();
			else if (s == State.TIMED_WAITING) {
				threadPrograma.resume();
				threadPrograma.interrupt();
				threadPrograma = new Thread(new ThreadProgramClass());
				threadPrograma.setDaemon(true);
				threadPrograma.start();
			} else if(s == State.TERMINATED){
				threadPrograma = new Thread(new ThreadProgramClass());
				threadPrograma.setDaemon(true);
				threadPrograma.start();
			}
			break;
		}
/*		case 4: {
			if (s == State.NEW)
				threadPrograma.start();
			else if (s == State.TIMED_WAITING)
				threadPrograma.resume();
			else
				LCD.drawString("thread nao esta nova \nnem pausada, \ndeve estar interrompida \npor um erro", 0, 0);
			break;
		}
*/		case 2: {
			if (s == State.NEW) {
				threadPrograma.start();
				threadPrograma.interrupt();
			} else if (s == State.TIMED_WAITING) {
				threadPrograma.resume();
				threadPrograma.interrupt();
			} else if (s == State.RUNNABLE)
				threadPrograma.interrupt();
			else if (s == State.WAITING)
				threadPrograma.interrupt();

			exit = true;
			break;
		}
		}
	}

	/**
	 * Metodo que faz o controle do menu, a interação da tecla com o menu
	 */
	public static void controleMenu() {
		int opcao = 1;// indica qual opcao foi selecionada
		boolean noMenu = true;
		while (noMenu) {
			mostraMenu(opcao);
			switch (Button.waitForAnyPress()) {
			case Button.ID_DOWN: {
				if (opcao == 2)
					opcao = 1;
				else
					opcao++;
				break;
			}
			case Button.ID_UP: {
				if (opcao == 1)
					opcao = 2;
				else
					opcao--;
				break;
			}
			case Button.ID_ENTER: {
				LCD.clear();
				noMenu = false;
				break;
			}
			}
		}
		selecionaOpcao(opcao);
	}
}
