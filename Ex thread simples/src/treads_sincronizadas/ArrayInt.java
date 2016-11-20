package treads_sincronizadas;

import java.util.Random;
public class ArrayInt{
	
	public int[] array;
	private int whriteIndex, tempoDeEspera;
	private static Random gerador = new Random();
	
	
	public ArrayInt()
	{
		array = new int[10];
		whriteIndex = 0;
	}
	//se uma thread obter o bloqueio de monitor desse metodo
	// as outras que tentar executar esse metodo entram em espera
	//ate que a thread que possui o bloqueio termine o metodo e libere o bloqueio
	//do objeto que chamar esse metodo, no caso o array
	public synchronized void add(int valor)
	{	
		int index = whriteIndex;
		tempoDeEspera = gerador.nextInt(300);
		try {
			Thread.sleep(tempoDeEspera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(Thread.currentThread().getName() + "   terminou o sleep por: " + tempoDeEspera + "segundos");
		
		array[index] = valor;
		
		System.out.println(" valor: " + valor + " adicionado na posição: " + index);
		whriteIndex++;
	}
}
