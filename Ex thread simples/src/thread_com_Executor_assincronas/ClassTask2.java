package thread_com_Executor_assincronas;

public class ClassTask2 implements Runnable{
	private String str = null;
	
	public ClassTask2(String str)
	{
		this.str = str;
	}

	@Override
	public void run() {
		System.out.println(str);
		try {
			Thread.sleep(2000);
			System.out.println("task2 terminou depois de 2s de speep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
