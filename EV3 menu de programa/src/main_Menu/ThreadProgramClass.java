package main_Menu;

public class ThreadProgramClass implements Runnable {
	private int i = 0;

	@Override
	/**
	 * aqui seria o programa principal do robo
	 * dentro do bloco try
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

			System.out.println(i);
			i++;
			//if (i % 10 == 0)
				//System.out.println("segundo");

			Thread.sleep(300);
		}
		//System.out.println(i);
	}
}
