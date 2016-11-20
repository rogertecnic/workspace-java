package duas_threads_parar_uma_na_outra;

public class ThreadProgramClass implements Runnable {
	private int i = 0;

	@Override
	/**
	 * aqui seria o programa principal do robo
	 * dentro do bloco try
	 */
	public void run() {
		try {
			while (true) {

				System.out.print(", " + i);
				i++;
				if (i % 10 == 0)
					System.out.println("");

				Thread.sleep(300);
			}
		}

		catch (InterruptedException e) {
			//e.printStackTrace();
			return;
		}
	}
}
