package thread_producer_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		BufferInt buffer = new BufferInt();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		pool.execute(new Producer(buffer));
		pool.execute(new Consumer(buffer));
		pool.shutdown();

	}

}
