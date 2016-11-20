package thread_producer_consumer;

import java.util.Random;

public class Producer implements Runnable {
	BufferInt buffer;
	
	public Producer(BufferInt buffer) {
		this.buffer = buffer;
	}

	private static Random gerador = new Random();

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				buffer.set(gerador.nextInt(100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
