package treads_sincronizadas;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		ArrayInt array = new ArrayInt();
		//Runnables,  o Runnable 1 começa com valores 10
		//o Runnable 2 começa com valores 20
		//assim é facil ver qual Runable gravou o seu valor no array por ultimo
		TaskWhriteArray runnable1 = new TaskWhriteArray(array,10);
		TaskWhriteArray runnable2 = new TaskWhriteArray(array,20);

		ExecutorService pool = Executors.newCachedThreadPool();
		pool.execute(runnable1);
		pool.execute(runnable2);
		pool.shutdown();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + Arrays.toString(array.array));
	}

}
