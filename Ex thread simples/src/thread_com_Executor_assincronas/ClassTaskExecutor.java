package thread_com_Executor_assincronas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// criando multiplas threads não sincronizadas
public class ClassTaskExecutor {
	
	public static void main(String[] args) {
		
		//cria Runnables de cada classe tarefa
		ClassTask1 task1 = new ClassTask1("task 1 iniciou");
		ClassTask2 task2 = new ClassTask2("task 2 iniciou");
		ClassTask3 task3 = new ClassTask3("task 3 iniciou");
		
		// cria um pool de threads para gerenciar os runnables dinamicamente a medida que
		//os Runnables (task1, task2 e task3) são executados
		ExecutorService poolThreadExecutor = Executors.newCachedThreadPool();
		
		//adiciona as Runnables ao pool de thread e inicia a execução delas,lembrando que são processos paralelos
		//elas sao adicionadas ao pool nesta ordem mas não são iniciadas  exatamente na mesma ordem
		poolThreadExecutor.execute(task1);
		poolThreadExecutor.execute(task2);
		poolThreadExecutor.execute(task3);
		
		//é necessario o encerramento do pool de threads, para ele finalizar a execução de todas as threads
		//ele não interrompe as threads, ele só não inicia mais nenhuma thread apos esse comando
		//então ele espera o final da execução de todas as threads e finaliza o pool
		poolThreadExecutor.shutdown();
		System.out.println("pool de threads terminou");

	}

}
