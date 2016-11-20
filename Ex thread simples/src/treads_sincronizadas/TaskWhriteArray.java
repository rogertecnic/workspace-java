package treads_sincronizadas;

public class TaskWhriteArray implements Runnable{
	ArrayInt array;
	private int valor;
	public TaskWhriteArray(ArrayInt arrayInt, int valor) {
		array = arrayInt;
		this.valor = valor;
	}

	@Override
	public void run() {
		for(int i = valor; i<valor+5;i++)
			array.add(i);
		
	}	
}
