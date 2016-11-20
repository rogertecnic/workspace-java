package thread_basico;

public class ClasseObjeto implements Runnable {

	@Override
	public void run()
	{
		try{
			System.out.println("Task de umas instancia de ClasseObjeto, wait 10s!!!");
			Thread.sleep(10000);
			System.out.println("final do metodo run dessa task!");
		}catch(InterruptedException e){
			e.printStackTrace();
			System.out.println("deu bosta na thread1");
		}
		
	}

}
