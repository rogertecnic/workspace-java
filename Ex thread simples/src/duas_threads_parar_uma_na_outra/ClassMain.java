package duas_threads_parar_uma_na_outra;

import java.lang.Thread.State;
import java.util.Scanner;

public class ClassMain {

	private static Scanner teclado = new Scanner(System.in);

	/**
	 * aqui é a thread principal que cria o menu de seleção para o codigo e
	 * gerencia se o codigo vai ser reiniciado ou continuar de onde parou
	 * detalhe, para o codigo continuar de onde parou, no robo de verdade
	 * deve-se guardar exatamente o estado dos motores antes de para-los e no
	 * case de continuar o codigo deve-se colocar os motores nos seus estados
	 * exatamente antes do programa ser pausado
	 */
	@SuppressWarnings("deprecation")
	
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new ThreadProgramClass());
		int opcao;

		while (true) {
			System.out.print("digite a opcao:\n");
			System.out.print("1 iniciar a thread:\n");
			System.out.print("2 continuar a thread:\n");
			System.out.print("3 encerrar a thread:\n");
			opcao = teclado.nextInt();
			switch (opcao) {
			case 1: {
				if (thread1.getState() == State.NEW)
					thread1.start();
				else if (thread1.getState() == State.TIMED_WAITING) {
					thread1.resume();
					thread1.interrupt();
					thread1 = new Thread(new ThreadProgramClass());
					delay(2);
					thread1.start();
				}
				break;
			}
			case 2: {
				if (thread1.getState() == State.NEW)
					thread1.start();
				else if (thread1.getState() == State.TIMED_WAITING)
					thread1.resume();
				else
					System.out.println("thread nao esta nova nem pausada, deve estar interrompida por um erro");
				break;
			}
			case 3: {
				System.exit(0);
				break;
			}
			}
			while (!teclado.hasNextInt()) {
			}
			teclado.next();
			if (thread1.isAlive())
				thread1.suspend();
		}
	}

	private static void delay(int tempo) {
		try {
			Thread.sleep(tempo * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
}
