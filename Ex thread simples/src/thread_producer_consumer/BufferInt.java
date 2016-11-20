package thread_producer_consumer;

public class BufferInt {
	private int buffer;
	private boolean temValor = false;
	
	public synchronized void  set(int numero) throws InterruptedException
	{
		while(temValor)
		{
			System.out.println("Aguardando leitura");
			wait();

		}
		System.out.println("valor escrito no buffer" + numero);
		buffer = numero;
		notify();
		temValor = true;
	}
	
	public  synchronized void  get() throws InterruptedException
	{
		while(!temValor)
		{
			System.out.println("não tem valor, aguardando escrita");
			wait();
		}
		System.out.println("valor lido do buffer" + buffer);
		notify();
		temValor = false;
	}
}
