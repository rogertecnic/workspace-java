package thread_basico;

public class MainClass
{
	public static void main(String[] args)
	{
		ClasseObjeto objeto = new ClasseObjeto();
		final Thread thread1 = new Thread(objeto);
		thread1.start();
		Runnable objRunnable = new Runnable()
		{
			@Override
			public void run() {
				System.out.println("Task de um objRunnable direto");
				try {
					Thread.sleep(1000);
					System.out.println("task do objRunnable finish, finalizando a thread1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread2 = new Thread(objRunnable);
		thread2.start();
		
	}
}
