package thread_producer_consumer;

public class Consumer implements Runnable{
	BufferInt buffer;
	public Consumer(BufferInt buffer) {
		this.buffer = buffer;
	}
	
	
	@Override
	public void run() {
		for(int i = 0; i<100; i++)
			try {
				buffer.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
