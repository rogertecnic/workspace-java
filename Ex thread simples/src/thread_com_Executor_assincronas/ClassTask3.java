package thread_com_Executor_assincronas;

public class ClassTask3 implements Runnable{
	private String str = null;
	
	public ClassTask3(String str)
	{
		this.str = str;
	}

	@Override
	public void run() {
		System.out.println(str);
		try {
			Thread.sleep(3000);
			System.out.println("task3 terminou depois de 3s de speep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
