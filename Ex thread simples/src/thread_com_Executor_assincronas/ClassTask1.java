package thread_com_Executor_assincronas;

public class ClassTask1 implements Runnable{
	private String str = null;
	
	public ClassTask1(String str)
	{
		this.str = str;
	}

	@Override
	public void run() {
		System.out.println(str);
		try {
			Thread.sleep(1000);
			System.out.println("task1 terminou depois de 1s de speep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
