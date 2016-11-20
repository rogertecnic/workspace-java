package ev3_main_menu;

public class ThreadProgramClass implements Runnable {
	private int i = 0;

	@Override
	/**
	 * aqui seria o programa principal do robo
	 * no metodo programa
	 */
	public void run() {
		try {
			programa();
		}

		catch (InterruptedException e) {
			//e.printStackTrace();
			//return;
		}
	}
	
	private void programa() throws InterruptedException
	{
		while (true) {

			System.out.print(", " + i);
			i++;
			if (i % 10 == 0)
				System.out.println("");

			Thread.sleep(500);
		}
	}
}
